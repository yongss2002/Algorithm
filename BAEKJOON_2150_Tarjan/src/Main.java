import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	// cnt : start from 0, SN: number of SCC, 
	static int V, E, cnt = 0, SN = 0;
	// dfsn: dfs found order, sn[i]: SCC number where vertex i in 
	static int[] dfsn, sn; 
	static ArrayList<Integer> adj[];
	// SCC 분리가 끝난 정점만 true
	static boolean finished[];
	static Stack<Integer> S;
	static ArrayList<ArrayList<Integer>> SCC;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(sc.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dfsn = new int[100000];
		sn = new int[100000];
		finished = new boolean[100000];
		
		S = new Stack<>();
		SCC = new ArrayList<ArrayList<Integer>>();
		adj = new ArrayList[100000];
		
		for (int i=0;i<=V;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(sc.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
		}
		
		for (int i=1;i<=V;i++) {
			if (dfsn[i] == 0) {
				dfs(i);
			}
		}
		System.out.println(SN);
		Collections.sort(SCC, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				// TODO Auto-generated method stub
				return o1.get(0) - o2.get(0);
			}
		});
		
		for(ArrayList<Integer> curSCC : SCC ) {
			for(int curr : curSCC) {
				System.out.print(curr + " ");
			}
			System.out.println("-1");
		}
		
		sc.close();
	}
	
	//자신의 결과값을 리턴하는 DFS 함
	static int dfs(int current) {
		dfsn[current] = ++cnt;//dfsn dicided
		S.push(current);//push this node to stack
		
		//save it's dfns or child's dfns or rettrn to result
		int result = dfsn[current];
		for(int to : adj[current]) {
			//not visited
			if (dfsn[to] == 0) {
				result = Math.min(result, dfs(to));
			//visited but not classified
			} else if (!finished[to]) {
				result = Math.min(result, dfsn[to]);
			}
		}
		
		// 자신, 자신의 자손들이 도달 가능한 제일 높은 정점이 자신일 경우 SCC 추출
		if (result == dfsn[current]) {
			ArrayList<Integer> currentSCC = new ArrayList<Integer>();
			while(!S.isEmpty()) {
				int t = S.pop();
				currentSCC.add(t);
				finished[t] = true;
				sn[t] = SN;
				if (t == current) {
					break;
				}
			}
			if (currentSCC.size() > 0) {
				Collections.sort(currentSCC);
				//SCC classified
				SCC.add(currentSCC);
				SN++;
			}
			
		}
		return result;
	}
	
}
