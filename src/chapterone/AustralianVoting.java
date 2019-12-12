package chapterone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedInputStream;
import java.util.Scanner;

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

	public static void main(String[] args) {
		Scanner in;
		List<String> results;
		int numCases;

		in = new Scanner(new BufferedInputStream(System.in));
		results = new ArrayList<String>();
		numCases = Integer.parseInt(in.nextLine());
		in.nextLine();

		for (int caseNum = 0; caseNum < numCases; caseNum++) {
			int numOfCandidates = Integer.parseInt(in.nextLine());
			Candidate[] candidates = new Candidate[numOfCandidates];

			// Get all of the candidates and store it in the candidates array.
			for (int i = 0; i < numOfCandidates; i++) {
				candidates[i] = new Candidate(in.nextLine());
			}

			// Array list of arrays to hold the voting data.
			List<int[]> votingData = new ArrayList<int[]>();

			// Parse the voting data and store it in an array list of arrays.
			String line = null;
			while (!(line = in.nextLine()).isEmpty()) {
				String[] temp = line.split(" ");
				int[] repl = new int[temp.length];
				for (int i = 0; i < repl.length; i++) {
					repl[i] = Integer.parseInt(temp[i]);
				}
				votingData.add(repl);
			}

			int votingSize = votingData.size();
			
			// Find the winner(s) for this data.
			//TODO Account for the case where all candidates are tied. 
			for (int i = 0; i < numOfCandidates; i++) {
				for (int j = 0; j < votingData.size(); j++) {
					for (int l = 0; l < votingData.get(0).length; l++) {
						if (!candidates[votingData.get(j)[l] - 1].eliminated) {
							candidates[votingData.get(j)[l] - 1].votes++;
							candidates[votingData.get(j)[l] - 1].votePercent = ((double) candidates[votingData.get(j)[l] - 1].votes / (double) votingSize) * 100;
							break;
						}
					}
				}
				
				double lowestPercent = 100.00;
				
				// Check if any person is above 50% while also finding the lowest percentage.
				for (int m = 0; m < candidates.length; m++) {			
					if (!candidates[m].eliminated) {
						if (candidates[m].votePercent > 50.00) {
							i = numOfCandidates;
							results.add(candidates[m].name);
							break;
						} else if (candidates[m].votePercent < lowestPercent && candidates[m].votePercent > 0.0) {
							lowestPercent = candidates[m].votePercent;
						}
					}
				}
				
//				List<Integer> temp = new ArrayList<Integer>();
//				boolean same = true;
				
				// All of them are tied case.
				for (int n = 0; n < candidates.length; n++) {
//					if (candidates[n].votes > 0) {
//						temp.add(n);
//					}
//					if (temp.size() > 1 && candidates[temp.get(n)].votes != candidates[temp.get(0)].votes) {
//						same = false;
//						break;
//					}
				}
				
				
				
				
				// Any candidates with the lowest percent are eliminated. If not eliminated, their string of votes is removed from votingData.
				for (int m = 0; m < candidates.length; m++) {
					if (candidates[m].votePercent == lowestPercent) {
						candidates[m].eliminated = true; 
					} else {
						candidates[m].votes = 0;
					}
				}			
			}
		}

		// Print out the results with a new line in between cases.
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i) + (i == results.size() - 1 ? "" : "\n"));
		}

		in.close();
	}

	static class Candidate {
		boolean eliminated;
		int votes;
		double votePercent;
		String name;

		public Candidate(String inputName) {
			eliminated = false;
			votes = 0;
			name = inputName;
			votePercent = 0;
		}
	}

}
