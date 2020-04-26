import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	static int N,M;
	static Vector<Edge>[] adj;
	static int[] dist;
	static PriorityQueue<Edge> que = new PriorityQueue<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		adj = new Vector[N+1];
		for (int i=0;i<=N;i++) {
			adj[i] = new Vector<Edge>();
		}
		
		for (int i=0;i<M;i++) {
			int start = sc.nextInt();
			int dest = sc.nextInt();
			int fee= sc.nextInt();
			adj[start].add(new Edge(dest, fee));
		}
		
		int start = sc.nextInt();
		int dest = sc.nextInt();
		Dijkstra(start, dest);
		System.out.println(dist[dest]);
	}
	
	static void Dijkstra(int start, int end) {
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		que.add(new Edge(start, 0));
		
		while (!que.isEmpty()) {
			Edge e = que.poll();
			int here = e.to;
			int fee = e.busFee;
			
			if (dist[here] < fee) continue;
			
			for(int i = 0;i<adj[here].size();i++) {
				Edge t = adj[here].get(i);
				int there = t.to;
				int thereFee = fee + t.busFee;
				if (dist[there] > thereFee) {
					dist[there] = thereFee;
					if (there != end) {
						que.add(new Edge(there, thereFee));						
					}
				}
			}
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int to;
		int busFee;
		public Edge(int to, int busFee) {
			super();
			this.to = to;
			this.busFee = busFee;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.busFee - o.busFee;
		}
	}
	
	
}
