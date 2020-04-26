import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {
	
	static int n, k;
	static int[] value;
	static int[] dp;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		dp = new int[k+1];
		for (int i=1;i<=k;i++) {
			dp[i] = 100001;
		}
		dp[0] = 0;
		
		value = new int[n+1];
		
		for (int i=1;i<=n;i++) {
			value[i] = sc.nextInt();
		}
		
		for(int i=1; i<=n; i++) {
            for(int j=value[i]; j<=k; j++) {
            		dp[j] = Math.min(dp[j], dp[j-value[i]] + 1);
            }
        }
		
		if (dp[k] == 100001) {
			System.out.println("-1");
		} else {
			System.out.println(dp[k]);
		}
		
		sc.close();
	}
}
