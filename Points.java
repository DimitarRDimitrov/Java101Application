package hackbg.application;

import java.util.Scanner;

public class Points {

	public static boolean alternativePathing = false;
	public static int X, Y;

	public static void toggle(char c) {
		if (c == '~') {
			alternativePathing = true;
		}
	}

	public static void incrementPositions(char arrow) {
		if (arrow == '>') {
			X++;
		} else if (arrow == '<') {
			X--;
		} else if (arrow == '^') {
			Y++;
		} else if (arrow == 'v') {
			Y--;
		}
	}

	public static void incrementAlternativePositions(char arrow) {
		if (arrow == '>') {
			X--;
		} else if (arrow == '<') {
			X++;
		} else if (arrow == '^') {
			Y--;
		} else if (arrow == 'v') {
			Y++;
		}
	}

	public static void relocation(char[] input) {
		for (int i = 0; i < input.length; i++){
			toggle(input[i]);
			if (!alternativePathing){
				incrementPositions(input[i]);
			} else {
				incrementAlternativePositions(input[i]);
			}
		}
	}
	
	static char[] userInput(){
		Scanner in = new Scanner (System.in);
		System.out.println("Enter X coordinate: ");
		X = in.nextInt();
		System.out.println("Enter Y coordinate: ");
		Y = in.nextInt();
		System.out.println("Enter list of directions: ");
		String input = in.next();
		char[] result = input.toCharArray();
		return result;
	}

	public static void main(String[] args) {
		relocation(userInput());
		System.out.printf("(%d , %d)", X, Y);
	}
}
