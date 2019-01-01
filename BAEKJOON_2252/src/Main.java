import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Main {
	static int N,M;
	static boolean[] visited;
	static Stack<Integer> history;
	static ArrayList<Integer>[] adjacent;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		visited = new boolean[N+1];
		history = new Stack<Integer>();
		adjacent = new ArrayList[N+1];
		Arrays.fill(visited, false);
		for(int i=0;i<=N;i++) {
			adjacent[i] = new ArrayList<>();
		}
		for (int i=0;i<M;i++) {
			adjacent[sc.nextInt()].add(sc.nextInt());
		}
		
		for (int i=1;i<=N;i++) {
			if (visited[i] == false) {
				DFS(i);
			}
		}
		while(!history.isEmpty()) {
			System.out.print(history.pop());
			System.out.print(" ");
		}
	}
	
	public static void DFS(int start) {
		visited[start] = true;
		for (int i=0;i<adjacent[start].size();i++) {
			int next = adjacent[start].get(i); 
			if (visited[next] == false) {
				DFS(next);
			}
		}
		history.push(start);
	}
}
