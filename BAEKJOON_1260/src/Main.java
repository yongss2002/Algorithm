import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N,M,S;
	static int[][] adjacent;
	static boolean[] visited;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		S = sc.nextInt();
		adjacent = new int[N+1][N+1];
		visited = new boolean[N+1];
		Arrays.fill(visited, false);
		for (int i=0;i<=N;i++) {
			for (int j=0;j<=N;j++) {
				adjacent[i][j] = 0;
			}
		}
		
		for(int i=0;i<M;i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			adjacent[start][end] = 1;
			adjacent[end][start] = 1;
			
		}
		
		DFS(S);
		System.out.println();
		BFS(S);
		sc.close();
	}
	
	public static void DFS(int start) {
		System.out.print(start);
		visited[start] = true;
		for(int i=1;i<=N;i++) {
			if (adjacent[start][i] == 1 && visited[i] == false) {
				System.out.print(" ");
				DFS(i);
			}
		}
	}
	
	
	public static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		Arrays.fill(visited, false);
		visited[start] = true;
		q.add(start);
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current);
			for (int i=0;i<=N;i++) {
				if (adjacent[current][i] == 1 && visited[i] == false) {
					visited[i] = true;
					q.add(i);
				}
			}
			System.out.print(" ");
		}
	}
}
