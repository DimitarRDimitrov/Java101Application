package hackbg.application;

import java.util.Scanner;

public class WordGame {

	static int totalOccurances = 0;
	static char[] word;
	static char[][] board = {   { 'i', 'v', 'a', 'n' },
								{ 'e', 'v', 'n', 'h' },
								{ 'i', 'n', 'a', 'v' },
								{ 'm', 'v', 'v', 'n' },
								{ 'q', 'r', 'i', 't' } };
	static int edgeX = board.length;
	static int edgeY = board[0].length;

	static void userInput() {
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the \"word\": ");
		String input = in.next();
		word = input.toCharArray();
	}

	static void countOccurances(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == word[0]) {
					totalOccurances += occurances(i, j);
				}
			}
		}
	}

	static int occurances(int coordinateX, int coordinateY) {
		int occurances = 0;
		boolean shouldIncrement = true;
		// left to right Y increases
		if (coordinateY + word.length <= edgeY) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX][coordinateY + i] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		// right to left Y decreases
		if (coordinateY - word.length >= 0) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX][coordinateY - i] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		// towards top X decreases
		if (coordinateX - word.length >= 0) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX - i][coordinateY] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		// towards bottom X decreases
		if (coordinateX + word.length <= edgeX) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX + i][coordinateY] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		// right diagonal up X-- Y++
		if (coordinateY + word.length <= edgeY && coordinateX - word.length >= 0) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX - i][coordinateY + i] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		// right diagonal down X++ Y++
		if (coordinateY + word.length <= edgeY
				&& coordinateX + word.length <= edgeX) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX + i][coordinateY + i] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		// left diagonal up X-- Y--
		if (coordinateY - word.length >= 0 && coordinateX - word.length >= 0) {
			for (int i = 1; i < word.length; i++) {
				if (board[coordinateX - i][coordinateY - i] != word[i]) {
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement)
				occurances++;
		}
		shouldIncrement = true;
		//left diagonal down X++ Y--
		if ( coordinateY - word.length >= 0 && coordinateX + word.length <= edgeX ){
			for (int i = 1; i < word.length; i++ ){
				if (board[coordinateX+i][coordinateY-i] != word[i]){
					shouldIncrement = false;
					break;
				}
			}
			if (shouldIncrement) occurances++;
		}
		return occurances;
	}
	
	static int finalResult(){
		userInput();
		countOccurances(board);
		return totalOccurances;
		
	}

	public static void main(String[] args) {
		System.out.println("Number of occurances: " + finalResult());
	}

}
