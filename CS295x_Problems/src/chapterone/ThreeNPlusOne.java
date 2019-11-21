package chapterone;

/*
 * Sample Input 
1 10
100 200
201 210
900 1000
 * 
 * Sample Output 1 10 20 100 200 125 201 210 89 900 1000 174
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("unused")
class ThreeNPlusOne { 

	public static void main(String args[]) {
		Scanner in;

		in = new Scanner(new BufferedInputStream(System.in));

		while (in.hasNext()) { // as long as there are strings in the input

			int I = in.nextInt();
			int J = in.nextInt();

			findAndPrintMaximumCycleLengths(I, J);

		} // end of while

		// close the scanner
		in.close();

	} // end of main method

	static void findAndPrintMaximumCycleLengths(int I, int J) {

		int maxCycleLength = Integer.MIN_VALUE;

		// store I and J first
		int oldI = I, oldJ = J;

		// f I greater than J then, switch I and J if I > J
		if (I > J) {
			int temp = J;
			J = I;
			I = temp;
		}

		// find max of cycle lengths between I and J
		int cycleLength;
		for (int i = I; i <= J; i++) {

			cycleLength = calculateCycleLength(i);

			if (cycleLength >= maxCycleLength) {
				maxCycleLength = cycleLength;
			}

		} // end of loop

		System.out.println(oldI + " " + oldJ + " " + maxCycleLength);

	}

	// Given a number n, this method returns the cycle length for n.
	static int calculateCycleLength(int n) {
		int cycleLength = 1;

		for (; n != 1; cycleLength++) {
			if (n % 2 == 0) {
				n = n / 2;
			} else {
				n = 3 * n + 1;
			}
		}
		return cycleLength;

	} // end of calculateCycleLength method

}
