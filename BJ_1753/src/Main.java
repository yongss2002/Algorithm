import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	
	static int V, E, K;
	static int[] dist;	
	static boolean[] visited;
	static ArrayList<Pair>[] adj;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(sc.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(sc.readLine());
		K = Integer.parseInt(st.nextToken());
		
		dist = new int[V+1];
		visited = new boolean[V+1];
		adj = new ArrayList[V+1];
		for(int i=0;i<=V;i++) {
			adj[i] = new ArrayList<>();
		}
		
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		dist[K] = 0;
		
		for (int i=0;i<E;i++) {
			int u, v, w;
			st = new StringTokenizer(sc.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adj[u].add(new Pair(v, w));
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
	
	static class Pair implements Comparable<Pair> {
		int next;
		int weitght;
		public Pair(int next, int weitght) {
			super();
			this.next = next;
			this.weitght = weitght;
		}
		
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.weitght - o.weitght;
		}
	}
	
	static void dijkstra(int start) {
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		dist[start] = 0;
        // 시작점 설정해주고 시작점 - 시작점 거리는 0이다.
        q.add(new Pair(K, 0));
//        for (int i=1;i<=V;i++) {
//        	q.add(new Pair(i,Integer.MAX_VALUE));
//        }
        while (!q.isEmpty()) {
            // 다음에 방문할 vertex 설정
            Pair current = q.poll();
            visited[current.next] = true;
            int index = current.next;
            int weight = current.weitght;
            if (weight > dist[index]) continue;
          //우선순위 큐에서 삭제한 정점의 인접한 정점들을 구한다.
            Iterator<Pair> it = adj[index].iterator();
            while(it.hasNext()) {
            	Pair adVertex=it.next();
                int index1=adVertex.next;
                int weight1=adVertex.weitght;
                //거리가 무한대이거나 OR 이전에 갱신된 거리보다 지금 방문한 거리가 작다면 거리를 다시 갱신해준다.
                if(dist[index1] > dist[index] + weight1) {
					dist[index1] = dist[index] + weight1;
					q.offer(adVertex);
				}
            }
		}
	}
}
