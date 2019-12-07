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
				String line = in.nextLine();
				// If it is the first empty line between boards, skip it.
				if (line.length() < 8)
					line = in.nextLine();
				String[] temp = line.split("");

				for (int j = 0; j < board[i].length; j++) {
					if (i > 1 && i < 10 && j > 1 && j < 10) {
						board[i][j] = temp[j - 2];
					} else {
						board[i][j] = ".";
					}

				}

				checkIfEmpty += line.equals("........") ? 1 : 0;
			}
			
			// If it is the last entry (empty board) break.
			if (checkIfEmpty == 8) {
				break;
			}
			
			// Time to check if kings are in check.
			for (int i = 2; i < 10; i++) {
				for (int j = 2; j < 10; j++) {
					boolean isUpperCase = Character.isUpperCase(board[i][j].charAt(0));
					String pce = board[i][j].toLowerCase();
					switch (pce) {
					case "p":
						if (isUpperCase && (board[i - 1][j - 1].equals("k") || board[i - 1][j + 1].equals("k")))
							wKingInCheck = true;
						i = 10;
						j = 10;
						if (!isUpperCase && (board[i + 1][j - 1].equals("k") || board[i + 1][j + 1].equals("K")))
							bKingInCheck = true;
						i = 10;
						j = 10;
						break;
					case "r":
						break;
					case "b":
						break;
					case "q":
						break;
					case "k":
						break;
					case "n":
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

}
