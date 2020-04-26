import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int V, E, count;
	static ArrayList<Integer>[] adj;
	static int[] disc;
	static boolean[] isCut;
	static List<Pair> res = new ArrayList<Main.Pair>();
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(sc.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		disc = new int[V+1];
		isCut = new boolean[V+1];
		adj = new ArrayList[V+1];
		for (int i=1;i<=V;i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(sc.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
			adj[to].add(from);
		}
		
		for (int i=1;i<=V;i++) {
			if (disc[i] == 0) {
				dfs(i, 0);
			}
		}
		
		Collections.sort(res, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				if(o1.x == o2.x) {
					return o1.y - o2.y;
				}
				return o1.x - o2.x;
			}
		});
		
		System.out.println(res.size());
		for (int i=0;i<res.size();i++) {
			System.out.println(res.get(i).x +" " + res.get(i).y);
		}
		
		sc.close();
	}
	
	//몇번째로 발견되었는지를 리턴해준다. 
	static int dfs(int here, int parent) {
		disc[here] = ++count;//DFS로 몇번째에 발견되었는지 저장하는 어레이
		int ret = disc[here];
		ArrayList<Integer> theres = adj[here];
		for(int there : theres) {
			if (there == parent) {
				continue;
			}
			if (disc[there] == 0) {
				int diff = dfs(there, here);
				if (disc[here] < diff) {
					res.add(new Pair(Math.min(here, there), Math.max(here, there)));
				} 
				ret = Math.min(ret, diff);
			} else {
				ret = Math.min(ret, disc[there]);
			}
		}
		return ret;
	}
	
	static class Pair {
		public int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
}
