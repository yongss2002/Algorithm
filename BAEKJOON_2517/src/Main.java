import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] money;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(sc.readLine());
		int max = 0;
		money = new int[N];
		StringTokenizer st = new StringTokenizer(sc.readLine());
		for (int t=0;t<N;t++) {
			money[t] = Integer.parseInt(st.nextToken());
			if (max < money[t]) {
				max = money[t];
			}
		}
		
		M = Integer.parseInt(sc.readLine());
		
		int budget = calculate(0, max);
		
		System.out.println(budget);
		
		sc.close();
	}
	
	
	static int calculate (int left, int right) {
		int middle = (left + right) / 2;
		int spend = 0;
		
		while(right >= left) {
			spend = 0;
			for (int i=0;i<N;i++) {
				if (middle > money[i]) {
					spend+=money[i];
				} else {
					spend += middle;
				}
			}
			if (spend < M) {
				left = middle + 1;
			} else if (spend > M) {
				right = middle - 1;
			} else {
				return middle;
			}
			middle = (left + right) / 2;
		}
		return middle;
		
	}
	
}
