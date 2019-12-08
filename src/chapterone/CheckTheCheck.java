package chapterone;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.ArrayList;

/*
Sample input:
..k.....
ppp.pppp
........
.R...B..
........
........
PPPPPPPP
K.......

rnbqkbnr
pppppppp
........
........
........
........
PPPPPPPP
RNBQKBNR

rnbqk.nr
ppp..ppp
....p...
...p....
.bPP....
.....N..
PP..PPPP
RNBQKB.R

........
..p.....
........
........
k.R....q
........
..p.....
........

........
........
........
........
........
........
........
........

Sample output:
Game #1: black king is in check.
Game #2: no king is in check.
Game #3: white king is in check.
 */

public class CheckTheCheck {

	public static void main(String[] args) {
		Scanner in;
		ArrayList<String> results;
		int gameNo;
		String[][] board = null;
		boolean wKingInCheck = false, bKingInCheck = false;

		in = new Scanner(new BufferedInputStream(System.in));
		results = new ArrayList<String>();
		gameNo = 0;

		while (in.hasNextLine()) {
			int checkIfEmpty = 0;
			board = new String[12][12];
			gameNo++;

			for (int i = 0; i < board.length; i++) {
				String line = (i > 1 && i < 10) ? in.nextLine() : "........";
				// If it is the first empty line between boards, skip it.
				if (line.length() < 8) {
					line = in.nextLine();
				}
				checkIfEmpty += line.equals("........") ? 1 : 0;
				String[] temp = line.split("");

				for (int j = 0; j < board[i].length; j++) {
					if (i > 1 && i < 10 && j > 1 && j < 10) {
						board[i][j] = temp[j - 2];
					} else {
						board[i][j] = ".";
					}

				}
			}
			
			// If it is the last entry (empty board) break.
			if (checkIfEmpty - 4 == 8) {
				break;
			}
			//TODO bishop case
			//TODO queen case
			// Time to check if kings are in check.
			for (int i = 2; i < 10; i++) {
				for (int j = 2; j < 10; j++) {
					boolean isUpperCase = Character.isUpperCase(board[i][j].charAt(0));
					String pce = board[i][j].toLowerCase();
					switch (pce) {
					case "p":
						if (isUpperCase && (board[i - 1][j - 1].equals("k") || board[i - 1][j + 1].equals("k"))) { wKingInCheck = true; i = 10; j = 10; }
						if (!isUpperCase && (board[i + 1][j - 1].equals("k") || board[i + 1][j + 1].equals("K"))) { bKingInCheck = true;  i = 10; j = 10; }
						break;
					case "r":
						String[] perps = getPerpindiculars(board, j, i);
						for (int a = 0; a < perps.length; a++) {
							if (isUpperCase && perps[a].equals("k")) { wKingInCheck = true; i = 10; j = 10; }
							if (!isUpperCase && perps[a].equals("K")) { bKingInCheck = true;  i = 10; j = 10; }
						}
						break;
					case "b":
						break;
					case "q":
						break;
					case "k":
						String[] kingJumps = {board[i - 1][j - 1], board[i - 1][j], board[i - 1][j + 1], board[i][j + 1],
								board[i][j - 1], board[i + 1][j + 1], board[i + 1][j], board[i + 1][j - 1]};
						for (int n = 0; n < kingJumps.length; n++) {
							if (isUpperCase && kingJumps[n].equals("k")) { wKingInCheck = true; i = 10; j = 10; }
							if (!isUpperCase && kingJumps[n].equals("K")) { bKingInCheck = true;  i = 10; j = 10; }
						}
						break;
					case "n":
						String[] potentialJumps = {board[i - 1][j - 2], board[i - 1][j - 2], board[i - 1][j - 2],
								board[i - 1][j - 2], board[i - 1][j - 2], board[i - 1][j - 2]};
						for (int m = 0; m < potentialJumps.length; m++) {
							if (isUpperCase && potentialJumps[m].equals("k")) { wKingInCheck = true; i = 10; j = 10; }
							if (!isUpperCase && potentialJumps[m].equals("K")) { bKingInCheck = true;  i = 10; j = 10; }
						}
						break;
					}
				}
			}
			
			results.add(wKingInCheck ? "Game #" + gameNo + ": white king is in check"
					: (bKingInCheck ? "Game #" + gameNo + ": black king is in check"
							: "Game #" + gameNo + ": no king is in check."));

		}

		// Print out all of the results.
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}

		in.close();

	}
	
	static String[] getPerpindiculars(String[][] board, int x, int y) {
		String[] ret = {".",".",".","."};
		
		// Gets the vertical row.
		for (int i = y - 1; i > 0; i--) {
			if (!board[i][x].equals(".")) {ret[0] = board[i][x]; i = 0;}
		}
		for (int j = y + 1; j < board.length - 1; j++) {
			if (!board[j][x].equals(".")) {ret[1] = board[j][x]; j = board.length - 1;}
		}
		
		// Gets the horizontal row.
		for (int i = x - 1; i > 0; i--) {
			if (!board[y][i].equals(".")) {ret[2] = board[y][i]; i = 0;}
		}
		for (int j = x + 1; j < board[0].length; j++) {
			if (!board[y][j].equals(".")) {ret[3] = board[y][j]; j = board[0].length;}
		}
		
		return ret;
	}

	static String[] getDiagonals(String[][] board, int x, int y) {
		
		return null;
	}
}




























