package chapterone;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Interpreter {

	public static void main(String[] args) {
		Scanner in;
		int numCases;
		//TODO switch to bufferedreader like i did in australian voting
		in = new Scanner(new BufferedInputStream(System.in));
		numCases = Integer.parseInt(in.nextLine());
		in.nextLine();
		
		while (in.hasNextLine()) {
			
		}
		
		
		
		
		in.close();
	}

}
