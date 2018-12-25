import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	static int N;
	static int[] STR;
	static int[] INT;
	static int[] PNT;
	
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		STR = new int[N+1];
		INT = new int[N+1];
		PNT = new int[N+1];
		dp = new int[1001][1001];
		visited = new boolean[N+1];
		
		for (int i=1;i<=N;i++) {
			STR[i] = sc.nextInt();
			INT[i] = sc.nextInt();
			PNT[i] = sc.nextInt();
		}
		
		for (int i=0;i<=1000;i++) {
			for (int j=0;j<=1000;j++) {
				dp[i][j] = -1;
			}
		}
		
		Arrays.fill(visited, false);
		
		System.out.println(solve(1,1));
		
	}
	
	static int solve(int _str, int _int) {
		int ret = dp[_str][_int]; 
		if ( ret != -1) {
			return ret; 
		}
		ret = 0;
		int point = 0;
		int succNum = 0;
		
		ArrayList<Integer> visit = new ArrayList();
		
		for (int i=1;i<=N;i++) {
			if (INT[i] <= _int || STR[i] <= _str) {
				if (!visited[i]) {
					visited[i] = true;
					visit.add(i);
					point += PNT[i];
				}
				succNum++;
			}
		}
		
		ret = succNum;
		dp[_str][_int] = ret; 
		for (int s = _str; s <= Math.min(1000, _str + point);s++) {
	        int i = Math.min(1000, _int + point - (s - _str));
	        ret = Math.max(ret, solve(s, i));
	    }
		
		for (int i=0;i<visit.size();i++) {
			visited[visit.get(i)] = false;
		}
		dp[_str][_int] = ret;
		
		return ret;
	}
	
}
