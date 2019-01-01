import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	static int M,N;
	static int[][] tomatos;
	static boolean[][] visited;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		tomatos = new int[N][M];
		visited = new boolean[N][M];
		
		LinkedList<pair> pos = new LinkedList<>();
		
		for (int i=0;i<N;i++) {
			for (int k=0;k<M;k++) {
				visited[i][k] = false;
				tomatos[i][k] = sc.nextInt();
				if (tomatos[i][k] == 1) {
					pos.add(new pair(k, i));
				}
			}
		}
		
		bfs(tomatos);
		
		System.out.println(getMaxDate());
		
	}
	
	static int getMaxDate() {
		int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tomatos[i][j] == 0) {
                    //토마토가 모두 익지 못한 상황이라면 -1 을 출력한다.
                    return -1;
                }
                max = Math.max(max, tomatos[i][j]);
            }
        }
        //그렇지 않다면 최대값을 출력한다.
        return max-1;
	}
	
	static void bfs(int[][] matrix) {
		int[] x_pos = {-1, 0, 0, 1};
		int[] y_pos = {0, -1, 1, 0};
		
		Queue<pair> queue = new LinkedList<>();
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (tomatos[i][j] == 1) {
					queue.add(new pair(j,i));
				}
			}
		}
		while(!queue.isEmpty()) {
			pair current = queue.poll();
			for (int j=0;j<4;j++) {
				int ay = current.y + y_pos[j];
				int ax = current.x + x_pos[j];
				if (ax >= 0 && ax < M && ay >= 0 && ay < N) {
					if (tomatos[ay][ax] == 0) {
						tomatos[ay][ax] = tomatos[current.y][current.x] + 1;
						queue.offer(new pair(ax, ay));
						visited[ay][ax] = true;
					}
				}
			}
			
		}
	}
	
	static class pair {
		int x;
		int y;
		public pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	
}
