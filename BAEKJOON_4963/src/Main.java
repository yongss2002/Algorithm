import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int W,H;
	static int[][] map;
	static boolean[][] visited;
	static int island = 0;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			W = sc.nextInt();
			H = sc.nextInt();
			if (W==0 && H==0) {
				break;
			}
			
			map = new int[H][W];
			visited = new boolean[H][W];
			island = 0;
			
			for(int i=0;i<H;i++) {
				for (int j=0;j<W;j++) {
					map[i][j] = sc.nextInt();
					visited[i][j] = false;
				}
			}
			
			for(int i=0;i<H;i++) {
				for (int j=0;j<W;j++) {
					if (map[i][j] == 1 && visited[i][j] == false) {
						BFS(i,j);
					}
				}
			}
			System.out.println(island);
		}
		
	}
	
	static class Pair {
		int h, w;

		public Pair(int h, int w) {
			super();
			this.h = h;
			this.w = w;
		}
		
	}
	static int[] x_pos = {-1,-1,0,1,1,1,0,-1};
	static int[] y_pos = {0,-1,-1,-1,0,1,1,1};
	public static void BFS(int h, int w) {
		Queue<Pair> q = new LinkedList<>();
		visited[h][w] = true;
		q.add(new Pair(h,w));
		while(!q.isEmpty()) {
			Pair current = q.poll(); 
//			System.out.print(current);
			for (int i=0;i<8;i++) {
				int next_x = current.w + x_pos[i];
				int next_y = current.h + y_pos[i];
				if (next_x <0 || next_y<0 || next_x>=W || next_y>=H) {
					continue;
				}
				if (map[next_y][next_x] == 1 && visited[next_y][next_x]  == false) {
					visited[next_y][next_x] = true;
					q.add(new Pair(next_y,next_x));
				}
			}
		}
		island++;
	}
}
