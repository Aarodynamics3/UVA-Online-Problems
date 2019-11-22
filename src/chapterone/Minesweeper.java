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
import java.util.Scanner;

public class Minesweeper {

	public static void main(String[] args) {
		Scanner in;
		
		in = new Scanner(new BufferedInputStream(System.in));
		
		while(in.hasNext()) {
			int fieldNo = 0;
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
			printNewField(n, m, field, fieldNo);
		}
		
		in.close();
	}
	
	static void printNewField(int n, int m, char[][] field, int fieldNo) {
		// Adds a space between input and other fields.
		System.out.println();
		
		// Currently in here for testing, prints out the same exact field as before.
		for (int i = 0; i < n; i++) {
			String newLine = "";
			
			for (int j = 0; j < m; j++) {
				newLine += field[i][j];
			}
			
			System.out.println(newLine);
		}
	}

}
