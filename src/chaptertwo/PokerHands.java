package chaptertwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;

/*
Sample input:
2H 3D 5S 9C KD 2C 3H 4S 8C AH
2H 4S 4C 2D 4H 2S 8S AS QS 3S
2H 3D 5S 9C KD 2C 3H 4S 8C KH
2H 3D 5S 9C KD 2D 3H 5C 9S KH

Sample output:
White wins.
Black wins.
Black wins.
Tie.

 */
//TODO UNCOMPLETED
public class PokerHands {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<String> results = new ArrayList<String>();
		
		String line;
		while (true) {
			line = in.readLine();
			if (line == null || line.equals("")) { break; }
			// Black poker hand.
			pokerHand black = new pokerHand(Arrays.copyOfRange(line.split(" "), 0, 5));
			// White poker hand.
			pokerHand white = new pokerHand(Arrays.copyOfRange(line.split(" "), 5, 10));

			if (black.value != white.value) {
				results.add(black.value > white.value ? "Black wins." : "White wins.");
			} else {
				boolean bWins = false;
				boolean wWins = false;
				for (int i = black.location.size() - 1; i >= 0; i--) {
					if (black.values[black.location.get(i)] > white.values[white.location.get(i)]) {
						bWins = true;
						break;
					} else if (black.values[black.location.get(i)] < white.values[white.location.get(i)]) {
						wWins = true;
						break;
					}
				}
				if (!bWins && !wWins) {
					results.add("Tie.");
				} else {
					results.add(bWins ? "Black wins." : "White wins.");
				}
			}
			
			
		}	
		
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		
		in.close();
	}
	
	static class pokerHand {
		int[] values;
		String[] suits;
		// "Value" or ranking of the hand, stored as an int.
		int value;
		// This will be used if you need to compare beyond ranking.
		//TODO set this one up
		List<Integer> location;
		
		public pokerHand(String[] hand) {
			values = new int[hand.length];
			suits = new String[hand.length];
			location = new ArrayList<Integer>();
			for (int i = 0; i < hand.length; i++) {
				String temp = Character.toString(hand[i].charAt(0));
				if (temp.equals("T")) { values[i] = 10; }
				else if (temp.equals("J")) { values[i] = 11; }
				else if (temp.equals("Q")) { values[i] = 12; }
				else if (temp.equals("K")) { values[i] = 13; }
				else if (temp.equals("A")) { values[i] = 14; }
				else { values[i] = Integer.parseInt(temp); }
				suits[i] = Character.toString(hand[i].charAt(1));
			}
			Arrays.sort(values);
			this.setValue();
		}
		
		// Use the suits and values to determine the ranking, or "value," of the hand.
		void setValue() {
			boolean straight = true;
			boolean allSameSuit = true;
			// Check for straight and same suit.
			for (int i = 0, j = values[0]; i < values.length; i++, j++) {
				if (values[i] != j) { straight = false; }
				if (!suits[i].equals(suits[0])) { allSameSuit = false; }
			}
			if (allSameSuit) {
				// Straight flush case.
				if (straight) {
					value = 8;
					return;
				// Flush case.
				} else {
					value = 5;
					return;
				}
			// Straight case.
			} else if (straight) {
				value = 4;
				return;
			}
			
			// Check for four of a kind, three of a kind.
			boolean fourOfAKind = false;
			boolean threeOfAKind = false;
			int counter = 0;
			for (int i = 0; i < values.length; i++) {
				counter = 0;
				for (int j = 0; j < values.length; j++) {
					counter += values[j] == values[i] ? 1 : 0;
				}
				fourOfAKind = counter >= 4;
				threeOfAKind = counter >= 3;
			}
			
			if (fourOfAKind) {
				value = 7;
				return;
			} else if (threeOfAKind) {
				// Check for full house first
				if ((values[0] == values[1] && values[0] != values[2])
						|| (values[3] == values[4] && values[3] != values[2])) { 
					value = 6;
					return;
				} 
				// else threeOfAKind
				else {
					value = 3;
					return;
				}
			}
			// Individual pairs.
			boolean i1 = values[0] == values[1];
			boolean i2 = values[1] == values[2];
			boolean i3 = values[2] == values[3];
			boolean i4 = values[3] == values[4];
			// Two pairs.
			boolean a1 = i1 && i3;
			boolean a2 = i1 && i4;
			boolean a3 = i2 && i4;
			boolean twoPairs = a1 || a2 || a3;
			if (twoPairs) {
				value = 2;
				return;
			}
			// one pair case.
			else if (i1 || i2 || i3 || i4) {
				value = 1;
				return;
			}
			// every other case, only needed to set up location value.
			else {
				location.add(0);
				location.add(1);
				location.add(2);
				location.add(3);
				location.add(4);
				return;
			}
			
		}
	}

}
