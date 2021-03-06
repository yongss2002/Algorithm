import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		ArrayList<Integer>[] adj = new ArrayList[N+1];
		Queue<Integer> q = new PriorityQueue<>();
		int[] time = new int[N+1];
		int[] completeTime = new int[N+1];
		int[] indegree = new int[N+1];
		Arrays.fill(indegree, 0);
		for(int i=1;i<=N;i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i=0;i<N;i++) {
			time[i+1] = sc.nextInt();
			int preBuild = sc.nextInt();
			while (preBuild != -1) {
				//adj 먼저 지어저야 하는 건물이 인덱스, 그 건물이 전재조건인 빌딩의 번호가 리스트로 들어있으면 안될 
				adj[preBuild].add(i+1);
				indegree[i+1] +=1;
				preBuild = sc.nextInt();
			}
		}
		
		for (int i=1;i<=N;i++) {
			if (indegree[i]==0) {
				q.add(i);
				completeTime[i] = time[i];
			}
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			for (int i = 0; i < adj[current].size(); i++) {
				int next = adj[current].get(i);
				indegree[next]--;
				completeTime[next] = Math.max(completeTime[next], completeTime[current] + time[next]);
				if (indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		for (int i=1;i<=N;i++) {
			System.out.println(completeTime[i]);
		}
		
	}
}
