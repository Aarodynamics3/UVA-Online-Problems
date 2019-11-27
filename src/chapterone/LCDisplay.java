package chapterone;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LCDisplay {

	public static void main(String[] args) {
		Scanner in;
		ArrayList<lcNumber> finalResults;
		
		in = new Scanner(new BufferedInputStream(System.in));
		finalResults = new ArrayList<lcNumber>();
		
		while (in.hasNext()) {
			int size = in.nextInt();
			int inputNums = in.nextInt();
			
			// End case to stop program, do not eval.
			if (size == 0 && inputNums == 0) {
				break;
			}
			
			// Simple array of the input of integers each represented as strings.
			String[] nums = Integer.toString(inputNums).split("");
			
			// List of the arrays of the numbers.
			ArrayList<numArray> myList = new ArrayList<numArray>();
			
			// Represents all of the integers in the nums array as double arrays of strings; puts them into myList.
			for (int i = 0; i < nums.length; i++) {
				myList.add(new numArray(getNumAs2dArray(size, nums[i])));
			}
			
			finalResults.add(new lcNumber(myList));
		}
		
		for (int i = 0; i < finalResults.size(); i++) {
			System.out.println(finalResults.get(i).getLCDislpay() + (i == finalResults.size() - 1 ? "" : "\n"));
		}
		
		in.close();
	}
	
	static String[][] getNumAs2dArray(int size, String num) {
		String[][] lcNum = new String[(2 * size) + 3][size + 2];
		
		for (int i = 1; i < (lcNum[0].length - 1); i++) {
			// Top horizontal row.
			if (!num.equals("1") && !num.equals("4")) {
				lcNum[0][i] = "-";
			}
			
			// Bottom horizontal row.
			if (!num.equals("1") && !num.equals("7") && !num.equals("4")) {
				lcNum[lcNum.length - 1][i] = "-";
			}
			
			// Middle horizontal row.
			if (!num.equals("1") && !num.equals("7") && !num.equals("0")) {
				lcNum[size + 1][i] = "-";
			}
		}
		
		for (int i = 1; i < (lcNum.length - 1); i++) {
			// First if skips the middle line to create two sections.
			if (i != size + 1) {
				if (num.equals("0") || num.equals("8")) {
					lcNum[i][0] = "|";
					lcNum[i][lcNum[0].length - 1] = "|";
				} else if (num.equals("1") || num.equals("3") || num.equals("7")) {
					lcNum[i][lcNum[0].length - 1] = "|";
				} else if (num.equals("4") || num.equals("9")) {
					if (i < size + 1) {
						lcNum[i][0] = "|";
					}
					lcNum[i][lcNum[0].length - 1] = "|";
				} else if (num.equals("2")) {
					if (i < size + 1) {
						lcNum[i][lcNum[0].length - 1] = "|";
					} else {
						lcNum[i][0] = "|";
					}
				} else if (num.equals("5")) {
					if (i > size + 1) {
						lcNum[i][lcNum[0].length - 1] = "|";
					} else {
						lcNum[i][0] = "|";
					}
				} else if (num.equals("6")) {
					lcNum[i][0] = "|";
					if (i > size + 1) {
						lcNum[i][lcNum[0].length - 1] = "|";
					}
				}
			}
		}
		
		// Any blank spaces are made blank spaces instead of nulls.
		for (int i = 0; i < lcNum.length; i++) {
			for (int j = 0; j < lcNum[0].length; j++) {
				if (lcNum[i][j] == null) {
					lcNum[i][j] = " ";
				}
			}
		}
		
		return lcNum;
	}
	
	static class numArray {
		String[][] array;
		
		numArray(String[][] inpArray) {
			this.array = inpArray;
		}
		
		String getLine(int row) {
			String ret = "";
			
			for (int i = 0; i < array[0].length; i++) {
				ret += array[row][i];
			}
			
			return ret;
		}
	}

	static class lcNumber {
		ArrayList<numArray> nums = new ArrayList<numArray>();
		String lcDisplay = "";
		
		lcNumber(ArrayList<numArray> input) {
			this.nums = input;
		}
		
		String getLCDislpay() {	
			for (int i = 0; i < nums.get(0).array.length; i++) {
				String temp = "";
				for (int j = 0; j < nums.size(); j++) {
					temp += nums.get(j).getLine(i);
					if (j < nums.size() - 1) {
						temp += " ";
					}
				}
				lcDisplay += temp + (i == (nums.get(0).array.length) - 1 ? "" : "\n");
			}
			return lcDisplay;
		}
	}
}
