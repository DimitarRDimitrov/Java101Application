package hackbg.application;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DependenciesResolving {

	static String path;
	
	static void getDependency(String toInstall, JSONObject packages){
		if(createDirectoryIfNeeded(toInstall)){
			//requirements for installation
			//an array with needed folders
			JSONArray tempJsonRequirements = (JSONArray) packages.get(toInstall);
			if ( tempJsonRequirements.size() > 0){
				System.out.print("In order to install " + toInstall + ", we need ");
				for (int i = 0; i < tempJsonRequirements.size(); i++){
					if ( i < 1 ) System.out.print(tempJsonRequirements.get(i));
					else {
						System.out.print(" and " + tempJsonRequirements.get(i));
					}
				}
				System.out.print(".\n");
				for ( Object o : tempJsonRequirements){
					getDependency(o.toString(), packages);
				}
			}
		}
	}
	
	static boolean createDirectoryIfNeeded(String directory){
		File dir = new File (path + directory);
		if ( dir.exists() ){
			System.out.println(directory + " is already installed.");
			return false;
		}
		else {
			System.out.println("Installing " + directory);
			dir.mkdir();
			return true;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter path to installation folder: ");
		path = (new Scanner(System.in)).next();
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(path + "/dependencies.json"));
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONArray dependency =  (JSONArray) jsonObject.get("dependencies");
			System.out.println(dependency);
			
			Object req = parser.parse(new FileReader(path + "/all_packages.json"));
			JSONObject packages = (JSONObject) req;
			for (Object dep : dependency){
				getDependency(dep.toString(), packages);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
