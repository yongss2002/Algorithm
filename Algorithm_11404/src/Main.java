import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {
	
	static int n, m;
	static int[][] dist;
	static int INF = 1000000000;
			
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));    
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(sc.readLine());
		m = Integer.parseInt(sc.readLine());
		
		dist = new int[n+1][n+1];
		
		for (int i=1;i<=n;i++) {
			for (int j=1;j<=n;j++) {
				if (i==j) {
					dist[i][j] = 0;
				} else {
					dist[i][j] = INF;	
				}
				
			}
		}
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(sc.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			//아래 소스 코드가 제일 중요
			dist[a][b] = Math.min(dist[a][b], c);
//			dist[a][b] = c;
		}
		
		for (int k = 1;k<=n;k++) {
			for (int i=1;i<=n;i++) {
				for (int j=1;j<=n;j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		for (int i=1;i<=n;i++) {
			for (int j=1;j<=n;j++) {
				if (dist[i][j] == INF || i==j) {
					System.out.print("0");
				} else {
					System.out.print(dist[i][j]);
				}
				if (j==n) {
					System.out.println();
				} else {
					System.out.print(" ");
				}
			}
		}
	}
}
