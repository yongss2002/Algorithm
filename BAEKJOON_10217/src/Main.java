import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	static int N, M, K;
	static int[][] dp;
	
	static Queue<Node> queue;
	
	static ArrayList<Node> adj[];
	
	public static class Node implements Comparable<Node>{
		int pos, cost, time;
		public Node(int position, int cost, int time) {
			super();
			this.pos = position;
			this.cost = cost;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for (int tc = 0;tc < TC;tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			adj = (ArrayList<Node>[]) new ArrayList[101] ;
			dp = new int[N+1][10001];
			
			for(int i=1;i<=100;i++) {
				adj[i] = new ArrayList<Node>();
			}
			
			for (int i=0;i<K;i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				int x = sc.nextInt();
				int d = sc.nextInt();
				
				adj[u].add(new Node(v, x, d));
				
			}
			
			int ans = dijkstra(N, M);
			if (ans == Integer.MAX_VALUE) {
				System.out.println("Poor KCM");
			} else {
				System.out.println(ans);
			}
		}
	}
	
	static int dijkstra(int nodes, int money) {
		for(int i=1; i<=nodes; i++){
	        for(int j=1; j<=money; j++) dp[i][j] = Integer.MAX_VALUE;
	    }
		
		queue = new PriorityQueue<>();
		
		queue.add(new Node(1,0,0));
		dp[1][0] = 0;
		
		while(!queue.isEmpty()){
	        Node top = queue.poll();
	        
	        int cost  = top.cost;
	        int dtime = top.time;
	        int pos   = top.pos;
	        if(pos == nodes) break;
	        
	        if(dp[pos][cost] < dtime) continue;
	        dp[pos][cost] = dtime;
	        
	        for(int i=0; i<adj[pos].size(); i++){
	            Node next = adj[pos].get(i);
	            
	            if(cost + next.cost > money) continue;
	            if(dp[next.pos][cost + next.cost] <= dtime + next.time) continue;
	            
	            queue.add(new Node(next.pos, cost + next.cost, dtime + next.time));
	            dp[next.pos][cost + next.cost] = dtime + next.time;
	        }
	    }
	    
	    int ret = Integer.MAX_VALUE;
	    for(int i=1; i<=money; i++)
	        ret = min(ret, dp[nodes][i]);
	    return ret;
	}
	
	public static int min(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}
	
}
