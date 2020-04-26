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
	static int dist[];
	static ArrayList<busInfo>[] bus;
	static boolean cycle = false;
	
	static class busInfo {
		public int dest;
		public int distance;
		public busInfo(int dest, int distance) {
			super();
			this.dest = dest;
			this.distance = distance;
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		bus = new ArrayList[N+1];
		dist = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[1] = 0;
		
		for (int i=0;i<=N;i++) {
			bus[i] = new ArrayList<busInfo>();
		}
		
		for (int i=0;i<M;i++) {
			int from, to, distance;
			from = sc.nextInt();
			to = sc.nextInt();
			distance = sc.nextInt();
			bus[from].add(new busInfo(to, distance));
		}
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<= N;j++) {
				for(busInfo info : bus[j]) {
					if (dist[j] != Integer.MAX_VALUE && dist[info.dest] > info.distance + dist[j])	 {
						dist[info.dest] = info.distance + dist[j];
						if (i==N) {
							cycle = true;
						}
					}
				}
			}
		}
		
		if (cycle) {
			System.out.println("-1");
		} else {
			for (int i=2;i<=N;i++) {
				if (dist[i] == Integer.MAX_VALUE) {
					System.out.println("-1");
				} else {
					System.out.println(dist[i]);
				}
			}
		}
		
	}
}
