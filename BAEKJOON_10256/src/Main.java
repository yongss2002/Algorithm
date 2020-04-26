import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(sc.readLine());
		StringTokenizer st;
		for (int t=0;t<TC;t++) {
			st = new StringTokenizer(sc.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			String dna = sc.readLine();
			String marker = sc.readLine();
			ArrayList<String> markers = new ArrayList<>();
			new StringBuilder(marker).rev
			for (int i=0;i<marker.length()-)
		}
		sc.close();
	}
	
	static IntStream revRange(String str, int from, int to) {
		InputStream is = new ByteArrayInputStream(str.getBytes());
	    return IntStream.range(from, to)
	                    .map(i -> to - i + from - 1);
	}
	
}
