import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Main {
	
	static int N,M;
	static Vector<Integer>[] adj;
	static Vector<Integer>[] revAdj;
	static boolean[] visited;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		adj = new Vector[N+1];
		revAdj = new Vector[N+1];
		visited = new boolean[N+1];
		
		Arrays.fill(visited, false);
		
		for (int i=1;i<=N;i++) {
			adj[i] = new Vector<>();
			revAdj[i] = new Vector<>();
		}
		
		for(int i=0;i<M;i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			adj[x].add(y);
			revAdj[y].add(x);
		}
		
		for (int i=1;i<=N;i++) {
			if (visited[i] == false) {
				DFS(i);
			}
		}
		
		int sscCount = 0;
		Arrays.fill(visited, false);
		while(!stack.isEmpty()) {
			int current = stack.pop();
			if(!visited[current]) {
				scc.add(new ArrayList<>());
				DFS2(current, sscCount);
				sscCount++;
			}
		}
		Collections.sort(scc, new Comparator<ArrayList<Integer>>() {

			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				// TODO Auto-generated method stub
				return o1.size() - o2.size();
			}
			
		});
		System.out.println(scc.size());
		for (int i=0;i<scc.size();i++) {
			System.out.print(scc.get(i).size());
			System.out.print(" ");
		}
	}
	
	static void DFS2(int start, int sscCount) {
		visited[start] = true;
		scc.get(sscCount).add(start);
		for (int i=0;i<adj[start].size();i++) {
			int next = adj[start].get(i);
			if (visited[next] ==false) {
				DFS2(next, sscCount);
			}
		}
	}
	
	static void DFS(int start) {
		visited[start] = true;
		for (int i=0;i<adj[start].size();i++) {
			int next = adj[start].get(i);
			if (visited[next] ==false) {
				DFS(next);
			}
		}
		stack.push(start);
	}
}
