import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int n;//num of team
	static int m;//
	static boolean[][] adj;
	static int[] order;
	static int[] indegree;
	static List<Integer> result;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(sc.readLine());
		for (int t=0;t<TC;t++) {
			n = Integer.parseInt(sc.readLine());
			
			order = new int[n+1];
			indegree = new int[n+1];
			result = new ArrayList<>();
			adj = new boolean[n+1][n+1];
			
			String str = sc.readLine();
			StringTokenizer st = new StringTokenizer(str);
			for (int i=1;i<=n;i++) {
				order[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=1;i<n;i++) {
				for (int j=i+1;j<=n;j++) {
					adj[order[i]][order[j]] = true;
					indegree[order[j]]++;
				}
			}
			
			m = Integer.parseInt(sc.readLine());
			for (int i=0;i<m;i++) {
				String line = sc.readLine();
				StringTokenizer st2 = new StringTokenizer(line);
				int from = Integer.parseInt(st2.nextToken());
				int to = Integer.parseInt(st2.nextToken());
				if (adj[from][to]) {
					adj[from][to] = false;
					adj[to][from] = true;
					indegree[from]++;
					indegree[to]--;
				} else {
					adj[from][to] = true;
					adj[to][from] = false;
					indegree[from]--;
					indegree[to]++;
				}
				
			}
			//data set end
			
			
			//topological sort
			Queue<Integer> queue = new PriorityQueue<Integer>();
			for (int i=1;i<=n;i++) {
				if (indegree[i] == 0) {
					queue.add(i);
				}
			}
			
			for (int i=0;i<n;i++) {
				if(queue.isEmpty()) {
					System.out.println("IMPOSSIBLE");
					break;
				} else if (queue.size() > 1) {
					System.out.println("?");
					break;
				} else {
					int start = queue.poll(); 
					result.add(start);
					indegree[start]--;
					for (int j=1;j<=n;j++) {
						if (adj[start][j]) {
							indegree[j]--;
							if(indegree[j]==0) {
								queue.add(j);
							}
						}
						
					}
					
				}
			}
			
			if (result.size() == n) {
				for (int i=0;i<n;i++) {
					System.out.print(result.get(i) + " ");
				}
				System.out.println();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}
	
}
