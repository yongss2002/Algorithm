import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	static int M,N,H;
	static int[][][] tomatos;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		tomatos = new int[H][N][M];
		
		LinkedList<pair> pos = new LinkedList<>();
		
		for (int a=0;a<H;a++) {
			for (int i=0;i<N;i++) {
				for (int j=0;j<M;j++) {
					tomatos[a][i][j] = sc.nextInt();
					if (tomatos[a][i][j] == 1) {
						pos.add(new pair(j, i, a));
					}
				}
			}
		}
		
		
		
		for (pair e : pos) {
			bfs(e.x, e.y, e.z);
		}
		
		System.out.println(getMaxDate());
		
	}
	
	static int getMaxDate() {
		int max = -1;
		for (int i=0;i<N;i++) {
			for (int k=0;k<M;k++) {
				
				if (tomatos[i][k] == 0) {
					return -1;
				} else if (tomatos[i][k] > max){
					max = tomatos[i][k]; 
				}
			}
		}
		return max -1;
	}
	
	static void bfs (int a, int b, int z) {
		int[] x_pos = {-1, 0, 0, 1};
		int[] y_pos = {0, -1, 1, 0};
		int[] z_pos = {0, -1, 1, 0};
		
		Queue<pair> queue = new LinkedList<>();
		queue.offer(new pair(a, b));
		
		while(!queue.isEmpty()) {
			pair p = queue.poll();
			int x = p.x;
			int y = p.y;
			
			for (int j=0;j<4;j++) {
				int ay = y + y_pos[j];
				int ax = x + x_pos[j];
				if (ax >= 0 && ax < M && ay >= 0 && ay < N) {
					if (tomatos[ay][ax] == 0 || tomatos[y][x] + 1 < tomatos[ay][ax]) {
						tomatos[ay][ax] = tomatos[y][x] + 1;
						queue.offer(new pair(ax, ay));
					}
				}
			}
			
		}
	}
	
	static class pair {
		int x;
		int y;
		int z;
		public pair(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
}
