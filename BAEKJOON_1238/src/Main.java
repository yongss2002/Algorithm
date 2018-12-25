import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	static int N, S, D, M;

	public static class Node {
		public int dest, cost;

		public Node(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}
	}

	static Vector<Node> adj[];
	
	static int[] dist;
	static int[] fee;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
			N = sc.nextInt();
			S = sc.nextInt();
			D = sc.nextInt();
			M = sc.nextInt();
			
			dist = new int[N + 1];
			adj = (Vector<Node>[]) new Vector[N + 1];
			
			for (int i = 0; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
				adj[i] = new Vector<Node>();
			}
			
			for (int i=0;i<M;i++) {
				int start = sc.nextInt();
				int dest = sc.nextInt();
				int cost = sc.nextInt();
				
				Node n = new Node(dest, cost);
				adj[start].add(n);
			}
			for (int i=0;i<N;i++) {
				fee[i] = sc.nextInt();
			}

			Arrays.fill(dist, 0);


			boolean update = true;
			int cnt = 0;
			while (update && cnt != N) {
				cnt++;
				update = false;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < adj[i].size(); j++) {
						
						if (dist[adj[i].get(j).dest] > dist[i] + adj[i].get(j).dest) {
							dist[adj[i].get(j).dest] = dist[i] + adj[i].get(j).dest;
							update = true;
						}
					}
				}
			}

			if (cnt == N) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

}
