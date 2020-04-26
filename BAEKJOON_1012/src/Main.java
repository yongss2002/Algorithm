import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int M,N,K;
	static int[] x_pos = {-1,0,1,0};
	static int[] y_pos = {0, -1, 0, 1};
	static int[][] veg;
	static boolean[][] visited;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int t=0;t<T;t++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			
			veg = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i=0;i<N;i++) {
				Arrays.fill(veg[i], 0);
				Arrays.fill(visited[i], false);
			}
			
			for (int i=0;i<K;i++) {
				int x= sc.nextInt();
				int y = sc.nextInt();
				veg[y][x] = 1;
			}
			
			int bug = 0;
			for (int i=0;i<N;i++) {
				for (int j=0;j<M;j++) {
					if (veg[i][j] == 1 && visited[i][j] ==false) {
						DFS(j, i);
						bug++;
					}
				}
			}
			System.out.println(bug);
		}
	}
	
	static void DFS(int x, int y) {
		visited[y][x] = true;
		for (int i=0;i<4;i++) {
			int nextX = x + x_pos[i];
			int nextY = y + y_pos[i];
			if (nextX <0 || nextY <0 || nextX>=M || nextY>=N) {
				continue;
			}
			if (veg[nextY][nextX] == 1 && visited[nextY][nextX] ==false) {
				DFS(nextX,nextY);
			}
		}
	}
}