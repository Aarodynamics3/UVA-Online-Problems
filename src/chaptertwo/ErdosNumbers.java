package chaptertwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/*
Sample input:
1
4 3
Smith, M.N., Martin, G., Erdos, P.: Newtonian forms of prime factor matrices
Erdos, P., Reisig, W.: Stuttering in petri nets
Smith, M.N., Chen, X.: First oder derivates in structured programming
Jablonski, T., Hsueh, Z.: Selfstabilizing data structures
Smith, M.N.
Hsueh, Z.
Chen, X.

Sample output:
Scenario 1
Smith, M.N. 1
Hsueh, Z. infinity
Chen, X. 2
 */

public class ErdosNumbers {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Count of how many cases to solve.
		int testCaseCount = Integer.parseInt(br.readLine());
		
		// For each test case.
		for (int testCase = 1; testCase <= testCaseCount; testCase++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// P is the number of papers.
			int P = Integer.parseInt(st.nextToken());
			// N is the number of names to check at the end.
			int N = Integer.parseInt(st.nextToken());

			HashMap<String, ArrayList<String>> edgeList = new HashMap<>();
			
			for (int p = 0; p < P; p++) {
				st = new StringTokenizer(br.readLine().trim(), ":");
				String[] nameHalf = st.nextToken().split(",");
				ArrayList<String> names = new ArrayList<>();
				
				// Remove the comma from the name if needed.
				for (int i = 0; i + 1 < nameHalf.length; i += 2) {
					if (nameHalf[i + 1].contains(".")) {
						names.add(nameHalf[i].trim() + "," + nameHalf[i + 1].trim());
					} else { i--; }
				}
				
				for (int i = 0; i < names.size(); i++) {
					for (int i2 = 0; i2 < names.size(); i2++) {
						if (i != i2) {
							String n1 = names.get(i), n2 = names.get(i2);
							if (!edgeList.containsKey(n1))
								edgeList.put(n1, new ArrayList<>());
							edgeList.get(n1).add(n2);
							if (!edgeList.containsKey(n2))
								edgeList.put(n2, new ArrayList<>());
							edgeList.get(n2).add(n1);
						}
					}
				}
			}

			HashMap<String, Integer> ans = new HashMap<>();
			HashSet<String> visited = new HashSet<>();
			Queue<String> queue = new ArrayDeque<>();
			
			queue.offer("Erdos,P.");
			
			// Erdos has a distance of 0 from himself.
			ans.put("Erdos,P.", 0);
			
			// If doesn't contain Erdos.
			if (!edgeList.containsKey("Erdos,P.")) {
				edgeList.put("Erdos,P.", new ArrayList<>());
			}
			
			while (!queue.isEmpty()) {
				String name = queue.poll();
				
				for (String next : edgeList.get(name))
					if (!ans.containsKey(next)) {
						visited.add(next);
						queue.offer(next);
						ans.put(next, ans.get(name) + 1);
					}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("Scenario ");
			sb.append(testCase);
			sb.append('\n');
			for (int n = 0; n < N; n++) {
				String nameOri = br.readLine().trim();
				String[] nameHalf = nameOri.split(",");
				String name = nameHalf[0].trim() + "," + nameHalf[1].trim();
				sb.append(nameOri);
				sb.append(' ');
				if (ans.getOrDefault(name, -1) >= 0) {
					sb.append(ans.get(name));
				} else {
					sb.append("infinity");
				}
				sb.append('\n');
			}
			// Print out the results.
			System.out.print(sb.toString());
		}
	}
}
