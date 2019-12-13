package chapterone;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Sample input:
1

3
John Doe
Jane Smith
Sirhan Sirhan
1 2 3
2 1 3
2 3 1
1 2 3
3 1 2

Sample output:
John Doe
 */

public class AustralianVoting {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numberOfCases = Integer.parseInt(in.readLine());
		List<String> results = new ArrayList<String>();
		
		//Skip next blank line.
		in.readLine();
		
		for (int caseNumber = 0; caseNumber < numberOfCases; caseNumber++)  {
			int numberOfCandidates = Integer.parseInt(in.readLine());
			Candidate[] candidates = new Candidate[numberOfCandidates];
			
			// Get all of the candidates and store it in an array.
			for (int i = 0; i < numberOfCandidates; i++) {
				candidates[i] = new Candidate(in.readLine());
			}
			
			// Parse the voting data and store it in an array list of arrays.
			List<int[]> tempData = new ArrayList<int[]>();
			String line;
			while (true) {
				line = in.readLine();
				if (line == null || line.equals("")) { break; }
				String[] temp = line.split(" ");
				int[] repl = new int[temp.length];
				for (int i = 0; i < repl.length; i++) {
					repl[i] = Integer.parseInt(temp[i]);
				}
				tempData.add(repl);
			}
			
			// Move the data into a 2d array.
			int[][] data = new int[tempData.size()][tempData.get(0).length];
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++) {
					data[i][j] = tempData.get(i)[j];
				}
			}
			
			// Find the winner(s).
			for (int votingRounds = 0; votingRounds < numberOfCandidates; votingRounds++) {
				double[] curPercents = new double[numberOfCandidates];
				
				// Parse through the data for the current voting round.
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[0].length; j++) {
						// Current candidate being evaluated.
						int cur = data[i][j] - 1;
						if (!candidates[cur].eliminated) {
							candidates[cur].votes++;
							double curPercent = (double) Math.round(((double) candidates[cur].votes / (double) data.length) * 10000) / 100;
							curPercents[cur] = curPercent;
							// If percent is over 50, return because they are the winner.
							if (curPercent > 50.00) { results.add(candidates[cur].name); i = data.length; votingRounds = numberOfCandidates; }
							break;
						}
					}
				}
				
				if (votingRounds < numberOfCandidates) {
					
					// Find lowest percent.
					double lowestPercent = 100.00;
					// Number of candidates votes greater than one.
					int numGTOne = 0;
					for (int i = 0; i < candidates.length; i++) {
						if (!candidates[i].eliminated) {
							if (curPercents[i] < lowestPercent) { lowestPercent = curPercents[i]; }
							if (candidates[i].votes > 0) { numGTOne++; }
						}
						candidates[i].votes = 0;
					}
					
					// All tied case.
					if (lowestPercent > 0) {
						int numCandidatesMatchingLowest = 0;
						String temp = "";
						for (int i = 0; i < candidates.length; i++ ) {
							if (!candidates[i].eliminated && curPercents[i] == lowestPercent) {
								numCandidatesMatchingLowest++;
								temp += candidates[i].name + (numCandidatesMatchingLowest == numGTOne ? "" : "\n");
							}
						}
						if (numCandidatesMatchingLowest == numGTOne) { results.add(temp); votingRounds = numberOfCandidates;}
					}
					
					// Any candidates with lowest percentage eliminated, vote counts are reset.
					for (int i = 0; i < candidates.length; i++) {
						if (curPercents[i] == lowestPercent) {
							candidates[i].eliminated = true;
						}
					}
					
				}
			}
			
		}
		
		// Print out the winner(s).
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i) + (i == results.size() - 1 ? "" : "\n"));
		}
		
		// Close scanner.
		in.close();
	}
	
	static class Candidate {
		boolean eliminated;
		int votes;
		String name;
		
		public Candidate(String name) {
			this.name = name;
			votes = 0;
			eliminated = false;
		}
	}
}