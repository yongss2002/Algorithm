import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	static int V, E, K;
	static ArrayList<Node>[] adj;
	static int[] dist;	
	static boolean[] visited;
	
	static class Node {
		
		public Node(int number, int weight) {
			super();
			this.nextNodeNum = number;
			this.weight = weight;
		}
		int nextNodeNum;
		int weight;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		K = sc.nextInt();
		
		adj = new ArrayList[V+1];
		dist = new int[V+1];
		visited = new boolean[V+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		
		for (int i = 1;i<=V;i++) {
			adj[i] = new ArrayList<Node>();
		}
		
		for (int i=0;i<E;i++) {
			int u, v, w;
			u = sc.nextInt();
			v = sc.nextInt();
			w = sc.nextInt();
			adj[u].add(new Node(v, w));
		}
		
		dijkstra(K);
		
		for(int i=1;i<=V;i++) {
			if (i==K) {
				System.out.println(0);	
			} else if(dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
	}
	
	static void dijkstra(int start) {
		int current = 0;
		dist[start] = 0;
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(start);
		
		while(queue.isEmpty() == false) {
			current = queue.poll();
			visited[current] = true;
			
			for (int i=0;i<adj[current].size();i++) { 
				Node node = adj[current].get(i);
				if (dist[node.nextNodeNum] > dist[current] + node.weight) {
					dist[node.nextNodeNum] = Math.min(dist[current] + node.weight, dist[node.nextNodeNum]);
					queue.add(node.nextNodeNum);
				}
			}
		}
		
	}
}
