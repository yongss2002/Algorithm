import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		int[] x = new int[3];
		int[] y = new int[3];
		for (int i=0;i<3;i++) {
			StringTokenizer st = new StringTokenizer(sc.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int a = x[0]*y[1] + x[1]*y[2] + x[2]*y[0];
		int b = y[0]*x[1] + y[1]*x[2] + y[2]*x[0];
		
		if (a<b) {
			System.out.println("-1");
		} else if (a==b) {
			System.out.println("0");
		} else {
			System.out.println("1");
		}
		sc.close();
	}
	
}
