import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,K;
	static long[] data;
	static long[] tree;
	static long[] lazy;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		line = sc.readLine();
		st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		data = new long[N*4];
		tree = new long[N*4];
		lazy = new long[N*4];
		
		for (int i=1;i<=N;i++) {
			data[i] = Integer.parseInt(sc.readLine());
		}
		
		//init
		init(1, 1, N+1);
		int a,b,c;
		long d;
		for (int i=0;i<K+M;i++) {
			line = sc.readLine();
			st = new StringTokenizer(line);
			
			a = Integer.parseInt(st.nextToken());
			
			if (a==1) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Long.parseLong(st.nextToken());
				//lazy update
				updateRange(1, 1, N+1, b, c, d);
			} else {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				//sum
				System.out.println(sum(1, 1, N+1, b, c));
			}
			
		}
		
		sc.close();
	}
	
	public static long init(int node, int begin, int end) {
		if (begin == end) {
			return tree[node] = data[end];
		}
		
		int middle = (begin + end) / 2;
		
		return tree[node] = init(node*2, begin, middle) + init(node*2 + 1, middle+1, end);
	}
	
	static void updateRange(int node, int begin, int end, int left, int right, long diff) {
		updateLazy(node, begin, end);
	    if (end < left || right < begin) return;
	    if (left <= begin && end <= right) {
	        tree[node] += (end - begin + 1) * diff;
	        if (begin != end) {
	            lazy[node * 2] += diff;
	            lazy[node * 2 + 1] += diff;
	        }
	        return;
	    }
	    int mid = (begin + end) / 2;
	    updateRange(node * 2, begin, mid, left, right, diff);
	    updateRange(node * 2 + 1, mid + 1, end, left, right, diff);
	    tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	public static void updateLazy(int node, int begin, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end - begin +1)*lazy[node];
			if (begin != end) {
				lazy[node*2]+=lazy[node];
				lazy[node*2+1]+=lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	public static long sum(int node, int begin, int end, int left, int right) {
		updateLazy(node, begin, end);
		if (end < left || begin > right) {
			return 0;
		}
		
		if (begin >= left && end <= right) {
			return tree[node];
		}
		
		int middle = (begin + end) / 2;
		
		return sum(node*2, begin, middle, left, right) + sum(node*2+1, middle+1, end, left, right);
		
	}
	
}
