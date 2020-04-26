import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] matrix;
	static int[] degree;
	static ArrayList<Integer> order = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(sc.readLine());
		matrix = new int[N+1][N+1];
		degree = new int[N+1];
		for (int t=1;t<=N;t++) {
			StringTokenizer st = new StringTokenizer(sc.readLine());
			for (int i=1;i<=N;i++) {
				matrix[t][i] = Integer.parseInt(st.nextToken());	
				degree[t] += matrix[t][i];
			}
		}
		
		for (int i=1;i<=N;i++) {
			if (degree[i] % 2 == 1) {
				System.out.print("-1");
				return;
			}
		}
		
		dfs(1);
		
		sc.close();
	}
	
	public static void dfs(int from) {
		for (int to=1;to<=N;to++) {
			while (matrix[from][to] != 0) {
				matrix[from][to]--;
				matrix[to][from]--;
				dfs(to);
			}
		}
		System.out.print(from + " ");
		return;
	}
	
}
