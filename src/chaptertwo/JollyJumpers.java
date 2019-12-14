package chaptertwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

/*
Sample input:
4 1 4 2 3
5 1 4 2 -1 6

Sample output:
Jolly
Not Jolly
 */

public class JollyJumpers {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<String> results = new ArrayList<String>();
		
		String line;
		while (true) {
			line = in.readLine();
			
			if (line == null || line.equals("")) { break; }
			
			boolean isJolly = true;
			String[] strArray = line.split(" ");
			int numIntegers = Integer.parseInt(strArray[0]);
			// 1 less length because the first number is removed for the int numIntegers.
			int[] numArray = new int[numIntegers];
			
			// Transfer String array to Integer array.
			for (int i = 0; i < numArray.length; i++) {
				numArray[i] = Integer.parseInt(strArray[i + 1]);
			}
			
			// 1 less length because it is the differences between each set of numbers.
			int[] absDifferences = new int[numArray.length - 1];
			for (int i = 0; i < absDifferences.length; i++) {
				absDifferences[i] = Math.abs(numArray[i] - numArray[i + 1]);
			}
			
			Arrays.sort(absDifferences);
			
			for (int i = 0; i < absDifferences.length; i++) {
				if (absDifferences[i] != i + 1) {
					isJolly = false;
					break;
				}
			}
			
			results.add(isJolly ? "Jolly" : "Not jolly");
		}
		
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		
		in.close();
	}

}
