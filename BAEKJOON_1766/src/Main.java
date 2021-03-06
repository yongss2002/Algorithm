import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static int N,M;
	static ArrayList<Integer>[] adj;
	static int[] indegree;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		pq = new PriorityQueue<>();
		adj = new ArrayList[N+1];
		indegree = new int[N+1];
		for(int i=0;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adj[a].add(b);
			indegree[b]+=1;
		}
		
		for (int i=1;i<=N;i++) {
			if (indegree[i] == 0) {
				pq.add(i);
			}
		}
		
		while(!pq.isEmpty()) {
			int current = pq.poll();
			System.out.print(current+" ");
			for (int i=0;i<adj[current].size();i++) {
				int next = adj[current].get(i);
				indegree[next]-=1;
				if (indegree[next] == 0) {
					pq.add(next);
				}
			}
		}
	}
}
