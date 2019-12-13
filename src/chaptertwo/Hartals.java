package chaptertwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/*
Sample input:
2
14
3
3
4
8
100
4
12
15
25
40

Sample Output:
5
15
 */

public class Hartals {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> results = new ArrayList<Integer>();
		int numCases = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < numCases; i++) {
			int numDays = Integer.parseInt(in.readLine());
			int numParties = Integer.parseInt(in.readLine());
			List<Integer> hartalParams = new ArrayList<Integer>();
			
			// Parse the data set for all of the hartal parameters. 
			for (int j = 0; j < numParties; j++) {
				int hartalNum = Integer.parseInt(in.readLine());
				int temp = hartalNum;
				for (int n = 0; n <= numDays; n++) {
					if (!hartalParams.contains(hartalNum) && hartalNum <= numDays) {
						hartalParams.add(hartalNum);
					}
					hartalNum += temp;
					if (hartalNum > numDays) { break; }
				}
			}
			
			int count = 0;
			
			// Go through all of the days.
			for (int j = 0; j < hartalParams.size(); j++) {
				if (hartalParams.get(j) % 7 != 6 && hartalParams.get(j) % 7 != 0) {
					count++;
				}
			}
			results.add(count);
		}
		
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		
		in.close();
	}

}
