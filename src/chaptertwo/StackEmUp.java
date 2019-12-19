package chaptertwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
Sample input:
1
2
2 1 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 52 51
52 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 1
1
2

Sample output:
King of Spades
2 of Clubs
4 of Clubs
5 of Clubs
6 of Clubs
7 of Clubs
8 of Clubs
9 of Clubs
10 of Clubs
Jack of Clubs
Queen of Clubs
King of Clubs
Ace of Clubs
2 of Diamonds
3 of Diamonds
4 of Diamonds
5 of Diamonds
6 of Diamonds
7 of Diamonds
8 of Diamonds
9 of Diamonds
10 of Diamonds
Jack of Diamonds
Queen of Diamonds
King of Diamonds
Ace of Diamonds
2 of Hearts
3 of Hearts
4 of Hearts
5 of Hearts
6 of Hearts
7 of Hearts
8 of Hearts
9 of Hearts
10 of Hearts
Jack of Hearts
Queen of Hearts
King of Hearts
Ace of Hearts
2 of Spades
3 of Spades
4 of Spades
5 of Spades
6 of Spades
7 of Spades
8 of Spades
9 of Spades
10 of Spades
Jack of Spades
Queen of Spades
Ace of Spades
3 of Clubs
 */

public class StackEmUp {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount = Integer.parseInt(br.readLine());

		// Construct the deck.
		String[] suits = new String[] { "Clubs", "Diamonds", "Hearts", "Spades" };
		String[] values = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] stockDeck = new String[52];
		StringBuilder sb;
		for (int i = 0; i < suits.length; i++) {
			for (int i2 = 0; i2 < values.length; i2++) {
				sb = new StringBuilder();
				sb.append(values[i2]);
				sb.append(" of ");
				sb.append(suits[i]);
				stockDeck[i * values.length + i2] = sb.toString();
			}
		}

		// Skip the blank line.
		br.readLine();
		String s;

		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			int n = Integer.parseInt(br.readLine());
			int[][] combn = new int[n][52];

			StringTokenizer st = new StringTokenizer("");
			for (int i = 0; i < n; i++) {
				for (int i2 = 0; i2 < 52; i2++) {
					if (!st.hasMoreTokens()) {
						st = new StringTokenizer(br.readLine());
					}
					combn[i][i2] = Integer.parseInt(st.nextToken()) - 1;
				}
			}

			String[] currDeck = Arrays.copyOf(stockDeck, stockDeck.length);
			while (true) {
				s = br.readLine();
				if (s == null || s.equals("")) {
					break;
				}
				int combnId = Integer.parseInt(s) - 1;
				String[] newDeck = new String[52];
				for (int i = 0; i < 52; i++) {
					newDeck[i] = currDeck[combn[combnId][i]];
				}
				currDeck = newDeck;
			}

			sb = new StringBuilder();
			for (int i = 0; i < currDeck.length; i++) {
				sb.append(currDeck[i]);
				sb.append("\n");
			}

			if (testCase < testCaseCount) {
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}

	}
}
