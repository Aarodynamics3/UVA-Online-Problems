package chapterone;

import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.ArrayList;

/*
I 5 6
L 2 3 A
S one.bmp
G 2 3 J
F 3 3 J
V 2 3 4 W
H 3 4 2 Z
S two.bmp
X


I 5 5
V 1 5 1 B
S batman.jpg
C
H 4 1 5 B
S nightwing.jpg
C
K 1 3 2 2 B
S robin.jpg
C
K 5 4 3 1 B
S alfred.jpg
C
K 3 4 5 1 B
S gordon.jpg
X
 */

public class GraphicalEditor {

	public static void main(String[] args) {
		Scanner in;
		GraphicalTable gt = null;
		ArrayList<String> fileNames;

		in = new Scanner(new BufferedInputStream(System.in));
		fileNames = new ArrayList<String>();

		while (in.hasNextLine()) {
			String input = in.nextLine();
			// Splits the input into each individual part.
			String[] inpSpl = input.split(" ");
			if (inpSpl[0].equals("X"))
				break;

			// Cases for each input.
			switch (inpSpl[0]) {
			case "I":
				// Create a new GraphicalTable with dimensions m and n.
				gt = new GraphicalTable(Integer.parseInt(inpSpl[2]), Integer.parseInt(inpSpl[1]));
				break;
			case "C":
				// Clears the GraphicalTable.
				gt.clear();
				break;
			case "S":
				// Saves the current state with a filename.
				fileNames.add(gt.getPicture(inpSpl[1]));
				break;
			case "L":
				// Put one color at one position.
				gt.table[Integer.parseInt(inpSpl[2]) - 1][Integer.parseInt(inpSpl[1]) - 1] = inpSpl[3];
				break;
			case "V":
				// Draw vertical line of color on column x between y1 and y2.
				int y_one = Integer.parseInt(inpSpl[2]);
				int y_two = Integer.parseInt(inpSpl[3]);
				if (y_two < y_one) {
					int temp = y_two;
					y_two = y_one;
					y_one = temp;
				}
				gt.drawVertical(Integer.parseInt(inpSpl[1]), y_one, y_two, inpSpl[4]);
				break;
			case "H":
				// Draw horizontal line of color between x1 and x2 on row y.
				int x_one = Integer.parseInt(inpSpl[1]);
				int x_two = Integer.parseInt(inpSpl[2]);
				if (x_two < x_one) {
					int temp = x_two;
					x_two = x_one;
					x_one = temp;
				}
				gt.drawHorizontal(x_one, x_two, Integer.parseInt(inpSpl[3]), inpSpl[4]);
				break;
			case "K":
				// Draw in a rectangle with inputs x1, y1, x2, y2.
				int x1 = Integer.parseInt(inpSpl[1]);
				int y1 = Integer.parseInt(inpSpl[2]);
				int x2 = Integer.parseInt(inpSpl[3]);
				int y2 = Integer.parseInt(inpSpl[4]);
				if (x1 >= x2) {
					int temp = x1;
					x1 = x2;
					x2 = temp;
				}
				if (y1 >= y2) {
					int temp = y1;
					y1 = y2;
					y2 = temp;
				}
				gt.drawRectangle(x1, y1, x2, y2, inpSpl[5]);
				break;
			case "F":
				int x = Integer.parseInt(inpSpl[1]) - 1;
				int y = Integer.parseInt(inpSpl[2]) - 1;
				String newCol = inpSpl[3];
				String oldCol = gt.table[y][x];
				// Fill a region surrounding a color.
				gt.fillRegion(x, y, newCol, oldCol);
				break;
			}
		}

		for (int i = 0; i < fileNames.size(); i++) {
			System.out.println(fileNames.get(i));
		}

		in.close();
	}

	static class GraphicalTable {
		String[][] table;
		int m;
		int n;

		// Initialize the graphical table.
		public GraphicalTable(int inpm, int inpn) {
			this.m = inpm;
			this.n = inpn;
			this.table = new String[m][n];

			// Initializes the table with each space being a capital O
			this.clear();
		}

		// Clears the table by making each space a capital O.
		public void clear() {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					table[i][j] = "O";
				}
			}
		}

		// Returns the current state of the GraphicalTable as a string tied to a
		// "filename."
		public String getPicture(String fileName) {
			String ret = fileName;
			for (int i = 0; i < m; i++) {
				String tempLine = "";
				for (int j = 0; j < n; j++) {
					tempLine += table[i][j];
				}
				ret += "\n" + tempLine;
			}
			return ret;
		}

		// Draws a vertical line in column x bounded by two y values.
		public void drawVertical(int x, int y_one, int y_two, String col) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (j == x - 1 && i >= y_one - 1 && i <= y_two - 1) {
						table[i][j] = col;
					}
				}
			}
		}

		// Draws a horizontal line in row y bounded by two x values.
		public void drawHorizontal(int x_one, int x_two, int y, String col) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (i == y - 1 && j >= x_one - 1 && j <= x_two - 1) {
						table[i][j] = col;
					}
				}
			}
		}

		// Draws a rectangle.
		public void drawRectangle(int x_one, int y_one, int x_two, int y_two, String col) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if ((j >= x_one - 1 && i >= y_one - 1) && (j <= x_two - 1 && i <= y_two - 1)) {
						table[i][j] = col;
					}
				}
			}
		}

		// Fill a region based on certain rules.
		public void fillRegion(int x, int y, String newCol, String oldCol) {
			if (newCol.equals(oldCol)) {
				return;
			}

			table[y][x] = newCol;

			// Recursion, yay.
			if (x > 0 && table[y][x - 1].equals(oldCol)) {
				fillRegion(x - 1, y, newCol, oldCol);
			}
			if (x < table[0].length - 1 && table[y][x + 1].equals(oldCol)) {
				fillRegion(x + 1, y, newCol, oldCol);
			}
			if (y > 0 && table[y - 1][x].equals(oldCol)) {
				fillRegion(x, y - 1, newCol, oldCol);
			}
			if (y < table.length - 1 && table[y + 1][x].equals(oldCol)) {
				fillRegion(x, y + 1, newCol, oldCol);
			}
		}

	}

}
