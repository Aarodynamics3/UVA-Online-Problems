package chapterone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

/*
Sample input:
1

299
492
495
399
492
495
399
283
279
689
078
100
000
000
000

Sample output: 
16
 */

public class Interpreter {

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		int numCases = Integer.parseInt(in.readLine());	
		List<Integer> results = new ArrayList<Integer>();
		
		// Skip blank line.
		in.readLine();
		
		// Evaluate over each case. 
		for (int i = 0; i < numCases; i++) {
			// Computer has 1000 words of RAM initially filled with "000".
			String[] ram = new String[1000];
			Arrays.fill(ram, "000");
			int index = 0;
			String line;
			
			// Fill all of the ram.
			while (true) {
				line = in.readLine();
				if (line == null || line.equals("")) { break; }
				else { ram[index++] = line; }
			}
			
			// Computer has 10 registers.
			int[] register = new int[10];
			int count = 0;
			int halt = 0;
			
			while (halt != -1 && halt < ram.length) {
				halt = doOp(ram, halt, register);
				count++;
			}
			results.add(count);
		}
		
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i) + (i == results.size() - 1 ? "" : "\n"));
		}
		
		in.close();
	}
	
	static int doOp(String[] ram, int index, int[] register) {
		String temp = ram[index];
		DecimalFormat df = new DecimalFormat("000");
		int code = Integer.parseInt(Character.toString(temp.charAt(0)));
		int inst1 = Integer.parseInt(Character.toString(temp.charAt(1)));
		int inst2 = Integer.parseInt(Character.toString(temp.charAt(2)));
		
		switch (code) {
		case 0:
			if (register[inst2] != 0) {
				return register[inst1];
			} else {
				break;
			}
		case 1:
			return -1;
		case 2:
			register[inst1] = inst2;
			break;
		case 3:
			// Results are reduced modulo 1000.
			register[inst1] = (register[inst1] + inst2) % 1000;
			break;
		case 4:
			// Results are reduced modulo 1000.
			register[inst1] = (register[inst1] * inst2) % 1000;
			break;
		case 5:
			register[inst1] = register[inst2];
			break;
		case 6:
			// Results are reduced modulo 1000.
			register[inst1] = (register[inst1] + register[inst2]) % 1000;
			break;
		case 7:
			// Results are reduced modulo 1000.
			register[inst1] = (register[inst1] * register[inst2]) % 1000;
			break;
		case 8:
			register[inst1] = Integer.parseInt(ram[register[inst2]]);
			break;
		case 9:
			ram[register[inst2]] = df.format(register[inst1]);
			break;
		}
		
		return index + 1;
	}
}
