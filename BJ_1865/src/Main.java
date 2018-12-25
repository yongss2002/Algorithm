import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	static int N;
	static int M;
	static int W;
	static int dist[];
	static ArrayList<Integer> wormHoleExit;
	static ArrayList<RoadInfo>[] roads;
	static boolean cycle = false;
	
	static class RoadInfo {
		public int dest;
		public int distance;
		public RoadInfo(int dest, int distance) {
			super();
			this.dest = dest;
			this.distance = distance;
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int TC = 0;TC < tc;TC++) {
			N = sc.nextInt();
			M = sc.nextInt();
			W = sc.nextInt();
			
			roads = new ArrayList[N+1];
			for (int i=0;i<=N;i++) {
				roads[i] = new ArrayList<RoadInfo>();
			}
			dist = new int[N+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			//웜홀의 출구에 대해서 모두 동작을 해야하므로 함수로 따로 빼줘야
//			dist[1] = 0;
			wormHoleExit = new ArrayList<>();
			
			for (int i=0;i<M;i++) {
				int start, end, distance;
				start = sc.nextInt();
				end = sc.nextInt();
				distance = sc.nextInt();
				
				roads[start].add(new RoadInfo(end, distance));
				roads[end].add(new RoadInfo(start, distance));
			}
			for (int i=0;i<W;i++) {
				int start, end, distance;
				start = sc.nextInt();
				end = sc.nextInt();
				distance = sc.nextInt();
				
				roads[start].add(new RoadInfo(end, -distance));
				wormHoleExit.add(end);
			}
			
			for (int i=0;i<wormHoleExit.size();i++) {
				cycle = isMinusCycle(wormHoleExit.get(i));
				if (cycle) {
					break;
				}
			}
			if (cycle) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			cycle = false;
		}
	}
	
	static boolean isMinusCycle(int start) {
		boolean minusCycle = false;
		dist[start] =  0;
		for (int i=1;i<=N;i++) {
			for (int j=1;j<= N;j++) {
				for(RoadInfo info : roads[j]) {
					if (dist[j] != Integer.MAX_VALUE && dist[info.dest] > info.distance + dist[j])	 {
						dist[info.dest] = info.distance + dist[j];
						if (i==N) {
							minusCycle = true;
							break;
						}
					}
				}
			}
		}
		Arrays.fill(dist, Integer.MAX_VALUE);
		return minusCycle;
	}
}
