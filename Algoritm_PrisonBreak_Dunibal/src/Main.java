import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int n;// 마을의 수
	static int d; // 지금까지 지난 일 수
	static int p; // 교도소가 있는 마을의 번호

	static int[][] connected; // 마을 연결산길 나타냄
	static double[][] cache;
	static int[] deg; // 각 마을이 몇개의 마을과 연결되었는지 나타냄

	static int t; // 확률을 계산할 마을의 수
	static int[] q; // 확률을 계산할 마을의 번호
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();

		for (int i = 0; i < testCase; i++) {

			connected = new int[50][50];
			cache = new double[50][100];
			
			for (int a=0;a<50;a++) {
				for (int b=0;b<100;b++ )
				cache[a][b] = -1;
			}
			
			
			n = sc.nextInt();
			d = sc.nextInt();
			p = sc.nextInt();

			deg = new int[n];

			for (int a = 0; a < n; a++) {
				for (int j = 0; j < n; j++) {
					int conn = sc.nextInt();
					if (conn == 1) {
						deg[a] += 1;
						connected[a][j] = conn;
					}
				}
			}
			
			t = sc.nextInt();
			q = new int[t];
			
			for (int a=0; a<t; a++) {
				q[a] = sc.nextInt();
			}
			
			for (int a = 0; a < t; a++) {
				
				double poss = search(q[a], d);
				System.out.print(Math.round(poss*100000000d) / 100000000d);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	static double search(int here, int days) {
		if (days == 0) return (here == p? 1.0 : 0.0);
		double ret = cache[here][days];
		
		if (ret > -0.5) return ret;
		
		ret = 0.0;
		for (int there = 0; there < n; ++there) {
			if (connected[here][there] == 1) {
				ret += search(there, days-1) / deg[there];
			}
		}
		
		cache[here][days] = ret;
		
		return ret;
	}
}
