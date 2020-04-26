import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	// dp[pos][val] pos 까지 val 번에 걸쳐 도착했을 때의 최대값
	static int N;
	static int M;
	static int K;
	static ArrayList<Fly>[] dist;
	static int[][] dp;

	static class Fly {
		int startPos;
		int distance;

		public Fly(int dest, int distance) {
			super();
			this.startPos = dest;
			this.distance = distance;
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		dist = new ArrayList[N + 1];
		dp = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			dist[i] = new ArrayList<Fly>();
		}
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				dp[i][j] = 0;
			}
		}

		for (int i = 0; i < K; i++) {
			int start, end, distance;
			start = sc.nextInt();
			end = sc.nextInt();
			distance = sc.nextInt();
			if (start < end) {
				dist[end].add(new Fly(start, distance));
			}
		}
		System.out.println(getMax(N, M));
	}

	static int getMax(int x, int y) {
		if (x > 1 && y == 1)
			return Integer.MIN_VALUE;
		if (x > 1 && 0 == dp[x][y]) {
			dp[x][y] = Integer.MIN_VALUE;
			for (Fly fly : dist[x])
				dp[x][y] = Math.max(dp[x][y], getMax(fly.startPos, y - 1) + fly.distance);
		}
		return dp[x][y];
	}

}
