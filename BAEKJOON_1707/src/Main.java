import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int v,e;
	static ArrayList<ArrayList<Integer>> adj;
	static final int RED = 1;
	static final int BLUE = -1;
	static int[] colors;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(sc.readLine());
		StringTokenizer st;
		for (int t=0;t<TC;t++) {
			st = new StringTokenizer(sc.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			colors = new int[v+1];
			
			adj = new ArrayList<>();
			for (int i=0;i<=v;i++) {
				adj.add(new ArrayList<Integer>());
				colors[i] = 0;
			}
			
			for (int i=0;i<e;i++) {
				st = new StringTokenizer(sc.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj.get(from).add(to);
				adj.get(to).add(from);
			}
			
			boolean success = true;
			for (int i=1;i<=v;i++) {
				if (colors[i] == 0) {
					success = dfs(i, RED);
					if (!success) {
						break;
					}
				}
			}
			if (success) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
		}
		sc.close();
	}
	
	static boolean dfs(int v, int color) {
		boolean success = true;
		colors[v] = color;
		ArrayList<Integer> thisAdj = adj.get(v);
		for(int i=0;i<thisAdj.size();i++) {
			if (colors[thisAdj.get(i)] == 0) {
				if(dfs(thisAdj.get(i), color*-1) == false) {
					success = false;
					break;
				}
			} else {
				if (colors[thisAdj.get(i)] == colors[v]) {
					success = false;
					break;
				}
			}
		}
		return success;
	}
	
}
