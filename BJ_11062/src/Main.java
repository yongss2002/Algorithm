import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	static int N;
	static int card[];
	static int[][][] dp;
	
	static int solve(int myTurn, int x, int y) {
		int ret = dp[myTurn][x][y];
		if (ret != -1) {
			return ret;
		}
		
		if (x==y) {
			if (myTurn == 1) {
				dp[myTurn][x][y] = ret = card[x];
			} else {
				dp[myTurn][x][y] = ret = 0;
			}
			return ret;
		}
		
		if (myTurn == 1) {
			ret = Math.max(solve(0, x+1, y) + card[x], solve(0, x, y-1) + card[y]);
		} else {
			ret = Math.min(solve(1, x+1, y), solve(1, x, y-1));
		}
		dp[myTurn][x][y] = ret;
		return ret;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for (int tc = 0;tc < TC;tc++) {
			N = sc.nextInt();
			card = new int[N+1];
			dp = new int[2][1001][1001];
			
			for (int i=0;i<2;i++) {
				for (int j=0;j<1001;j++) {
					for (int k=0;k<1001;k++) {
						dp[i][j][k] = -1;
					}
				}
			}
			
			for(int i=1;i<=N;i++) {
				card[i] = sc.nextInt();
			}
			
			System.out.println(solve(1,1,N));
		}
		
		
	}
}
