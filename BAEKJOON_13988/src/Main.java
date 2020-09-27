import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(sc.readLine());
		for (int t=0;t<TC;t++) {
			
		}
		sc.close();
	}
	
}
