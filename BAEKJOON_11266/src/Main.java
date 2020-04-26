import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	static int V, E;
	static Vector<Integer>[] adj;
	static int counter = 0;
	static boolean[] isCutVertex;
	static int[] discovered;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		String line = sc.readLine();
		StringTokenizer st = new StringTokenizer(line);
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		isCutVertex = new boolean[V + 1];
		adj = new Vector[V + 1];
		discovered = new int[V + 1];

		Arrays.fill(discovered, 0);

		for (int i = 0; i <= V; i++) {
			adj[i] = new Vector<>();
		}

		for (int i = 0; i < E; i++) {
			line = sc.readLine();
			st = new StringTokenizer(line);
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}

		for (int i = 1; i <= V; i++) {
			if (discovered[i] == 0)
				dfs(i, true);
		}
		
		int count = 0;
		for (int i = 1; i <= V; i++) {
			if (isCutVertex[i]) {
				count++;
			}
		}
		System.out.println(count);
		for (int i = 1; i <= V; i++) {
			if (isCutVertex[i]) {
				System.out.print(i + " ");
			}
		}

		sc.close();
	}

	public static int dfs(int index, boolean isRoot) {
		discovered[index] = ++counter;
		int ret = discovered[index];

		int child = 0;

		for (int i = 0; i < adj[index].size(); i++) {
			int next = adj[index].get(i);
			if (discovered[next] == 0) {
				child++;
				int first = dfs(next, false);
				if (!isRoot && first >= discovered[index]) {
					isCutVertex[index] = true;
				}
				ret = Math.min(first, ret);
			} else {
				ret = Math.min(ret, discovered[next]);
			}
		}
		if (isRoot) {
			isCutVertex[index] = child >= 2;
		}
		return ret;
	}

}
