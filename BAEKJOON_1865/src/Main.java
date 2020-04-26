import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N, M, W;

	public static class Node {
		public int next, time;

		public Node(int next, int time) {
			super();
			this.next = next;
			this.time = time;
		}

	}

	static ArrayList<Node> adj[];
	static int[] dist;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();

		for (int tc = 0; tc < TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			W = sc.nextInt();

			dist = new int[N + 1];
			adj = (ArrayList<Node>[]) new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
				adj[i] = new ArrayList<Node>();
			}
			dist[1] = 0;

			for (int i = 0; i < M; i++) {
				int s, e, t;
				s = sc.nextInt();
				e = sc.nextInt();
				t = sc.nextInt();

				adj[s].add(new Node(e, t));
				adj[e].add(new Node(s, t));
			}

			for (int i = 0; i < W; i++) {
				int s, e, t;
				s = sc.nextInt();
				e = sc.nextInt();
				t = sc.nextInt();
				adj[s].add(new Node(e, -t));
			}

			boolean update = true;
			int cnt = 0;
			while (update && cnt != N) {
				cnt++;
				update = false;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < adj[i].size(); j++) {
						if (dist[adj[i].get(j).next] > dist[i] + adj[i].get(j).time) {
							dist[adj[i].get(j).next] = dist[i] + adj[i].get(j).time;
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

}
