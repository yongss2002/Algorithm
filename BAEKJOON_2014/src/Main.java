import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(sc.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(st.nextToken());
		
		Queue<Long> primeQueue = new PriorityQueue<Long>();
		Long[] prime = new Long[K];
		
		st = new StringTokenizer(sc.readLine());
		
		for(int i=0;i<K;i++) {
			prime[i] = Long.parseLong(st.nextToken());
			primeQueue.add(prime[i]);
		}
		
		Long min = null;
		for(int i=0;i<N;i++) {
			min = primeQueue.poll();
			for(int j=0;j<K;j++) {
				Long value = min * prime[j];
				primeQueue.add(value);
				if (min%prime[j]==0) { 
					break;
				}
			}
		}
		
		System.out.println(min);
		
		sc.close();
	}
	
}
