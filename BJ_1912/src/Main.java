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
	static int dp[];
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		card = new int[N+1];
		dp = new int[N+1];
		
		for (int i=1;i<=N;i++) {
			card[i] = sc.nextInt();
		}
		
		dp[1] = card[1];
		
		for (int i=2;i<=N;i++) {
			if (dp[i-1] + card[i] > card[i]) {
				dp[i] = dp[i-1] + card[i];
			} else {
				dp[i] = card[i];
			}
		}
		
		int max = dp[1];
		
		for (int i=2;i<=N;i++) {
			if(dp[i] > max) {
				max = dp[i];
			}
		}
		
		System.out.println(max);
		
	}
}
