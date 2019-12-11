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

			// Find the winner(s) for this data.
			for (int i = 0; i < numOfCandidates; i++) {
				// Get the votes for the current column.
				for (int j = 0; j < votingData.size(); j++) {
					// Tally up all of the initial votes; update their votes and vote percentage.
					candidates[votingData.get(j)[i] - 1].votes++;
					candidates[votingData.get(j)[i] - 1].votePercent = (double) candidates[votingData.get(j)[i] - 1].votes / (double) votingData.size();
				}

				double lowestPercent = 100.00;
				
				// Check if any person is above 50% while also finding the lowest percentage.
				for (int m = 0; m < candidates.length; m++) {
					if (candidates[m].votePercent > 0.5) {
						i = numOfCandidates;
						results.add(candidates[m].name);
						break;
					} else if (candidates[m].votePercent < lowestPercent && !candidates[m].eliminated) {
						lowestPercent = candidates[m].votePercent;
					}
				}
				
				// Any candidates with the lowest percent are eliminated. If not eliminated, their string of votes is removed from votingData.
				//TODO account for the case that a group can have the same percentage at the end with no more votes to go (use the i counter)
				for (int m = 0; m < candidates.length; m++) {
					if (candidates[m].votePercent == lowestPercent) {
						candidates[m].eliminated = true;
					}
					
					//TODO current objective is to remove each string of votes of the people not eliminated
					//TODO fix this fucking shit
					if (!candidates[m].eliminated) {
						Iterator<int[]> iter = votingData.iterator();
						while (iter.hasNext()) {
							int[] cur = iter.next();
							if (cur[i] == m) { iter.remove(); }
						}
					}
				}

			}
			
			//TODO don't think we need to skip a line at the end anymore
			// Skip the blank line in between inputs.
			//in.nextLine();
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
