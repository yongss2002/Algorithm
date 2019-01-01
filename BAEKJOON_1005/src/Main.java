import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	static int[] memoization;
	static int N;
	static int[][] order;
	static int[] buildTime;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for (int tc = 0;tc < TC;tc++) {
			N = sc.nextInt();
			int K = sc.nextInt();
			
			order = new int[N+1][N+1];
			memoization = new int[N+1];
			Arrays.fill(memoization, -1);
			
			for (int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					order[i][j] = -1;
				}
			}
			
			buildTime = new int[N+1];
			for (int i=1;i<= N;i++) {
				buildTime[i] = sc.nextInt();
			}
			
			for (int i=0;i<K;i++) {
				order[sc.nextInt()][sc.nextInt()] = 1;
			}
			
			int goal = sc.nextInt();
			int minimumTime = getMinimumTime(goal);
			System.out.println(minimumTime);
			
		}
	}
	
	public static int getMinimumTime(int destination) {
		int totalTime = memoization[destination];
		int time = 0;
		if (totalTime == -1) {
			for (int i=1;i<=N;i++) {
				if (order[i][destination] == 1) {
					time = Math.max(time, getMinimumTime(i));
				}
			}
			memoization[destination] = totalTime = time + buildTime[destination];
		}
		
		return totalTime;
	}
	
	
}
