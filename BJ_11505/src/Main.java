import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,K;
	static int[] indexTree;
	static int base;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		base = 1 << (int)(Math.log(N-1)/Math.log(2)+1);
		indexTree = new int[base*4];
		for (int i=0;i<N;i++) {
			int index = i + base;
			indexTree[index] = Integer.parseInt(br.readLine());
		}
		
		for (int i=1)
		
		for (int i=0;i<M+K;i++) {
			
		}
		sc.close();
	}
	
}
