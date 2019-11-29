package chapterone;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Sample inputs:
 * 3
 * 10.0
 * 20.0
 * 30.0
 * 4
 * 15.0
 * 15.01
 * 3.0
 * 3.01
 * 
 * Sample outputs:
 * $10.00
 * $11.99
 */

public class TheTrip {

	public static void main(String[] args) {
		Scanner in;
		ArrayList<Double> amounts;

		in = new Scanner(new BufferedInputStream(System.in));
		amounts = new ArrayList<Double>();

		while (in.hasNext()) {
			int n = in.nextInt();
			if (n == 0) break;
			double[] costs = new double[n];
			double avg = 0.0;

			// Inputs the cost of each person, also adding each amt. to the average to later
			// be calculated.
			for (int i = 0; i < n; i++) {
				costs[i] = in.nextDouble();
				avg += costs[i];
			}
			
			// Sort the costs from least to greatest.
			Arrays.sort(costs);
			
			avg = avg / (double) n;
	        avg = Math.round(avg * 100.0) / 100.0;
	        
			// Calculate the difference and add it to Amounts.
			amounts.add(calculateDif(avg, costs));
		}

		for (int i = 0; i < amounts.size(); i++) {
			System.out.printf("$%.2f\n", amounts.get(i));
		}
		
		in.close();
	}
	
	static double calculateDif(double avg, double[] costs) {
		double high = 0.0;
		double low = 0.0;
		
		for (int i = costs.length - 1; i >= 0; i--) {
			if (costs[i] > avg) {
				high += costs[i] - avg;
			} else {
				low += avg - costs[i];
			}
		}
		return high > low ? low : high;
	}

}




































