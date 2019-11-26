package chapterone;

/*
 * Sample input
 * 
 * 4 4
   *...
   ....
   .*..
   ....
   
   3 5
   **...
   .....
   .*...
   
   0 0
 */

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Minesweeper {

	public static void main(String[] args) {
		Scanner in;
		ArrayList<String> fields;
		int fieldNo;
		
		in = new Scanner(new BufferedInputStream(System.in));
		fields = new ArrayList<String>();
		fieldNo = 0;
		
		while (in.hasNext()) {

			int n = in.nextInt();
			int m = in.nextInt();

			// If the numbers are zero then end the input, i.e. break.
			if (n == 0 || m == 0) {
				break;
			}

			char[][] field = new char[n][m];

			for (int i = 0; i < n; i++) {
				String line = in.next();

				for (int j = 0; j < m; j++) {
					field[i][j] = line.charAt(j);
				}
			}

			fieldNo++;
			fields.add(printNewField(n, m, field, fieldNo));
		}

		// Print out each field.
		for (int i = 0; i < fields.size(); i++) {
			System.out.println(fields.get(i));
			if (i != (fields.size() - 1)) {
				System.out.println();
			}
		}
		in.close();
	}

	static String printNewField(int n, int m, char[][] field, int fieldNo) {
		// Creates the field as a string.
		String ret = "Field #" + fieldNo + ":" + "\n";

		for (int i = 0; i < n; i++) {
			String newLine = "";

			for (int j = 0; j < m; j++) {
				if (field[i][j] == '*') {
					newLine += '*';
				} else {
					newLine += getNumOfBombs(field, i, j);
				}
			}

			ret += newLine + (i == (n-1) ? "" : "\n");
		}
		return ret;
	}

	static int getNumOfBombs(char[][] field, int row, int column) {
		int num = 0;

		num += (row > 0 && column > 0 && field[row - 1][column - 1] == '*') ? 1 : 0;
		num += (column > 0 && field[row][column - 1] == '*') ? 1 : 0;
		num += (row + 1 != field.length && column > 0 && field[row + 1][column - 1] == '*') ? 1 : 0;
		num += (row + 1 != field.length && field[row + 1][column] == '*') ? 1 : 0;
		num += (row + 1 != field.length && column + 1 != field[row].length && field[row + 1][column + 1] == '*') ? 1 : 0;
		num += (column + 1 != field[row].length && field[row][column + 1] == '*') ? 1 : 0;
		num += (row > 0 && column + 1 != field[row].length && field[row - 1][column + 1] == '*') ? 1 : 0;
		num += (row > 0 && field[row - 1][column] == '*') ? 1 : 0;

		return num;
	}

}
