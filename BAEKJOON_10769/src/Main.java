import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static String sad = ":-(";
	static String happy = ":-)";
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		String str = sc.readLine();
		
		ArrayList<Integer> sads = kmp(str, sad);
		ArrayList<Integer> happys = kmp(str, happy);
		
		if (sads.size() == 0 && happys.size() == 0) {
			System.out.println("none");
		} else if  (sads.size() == happys.size()) {
			System.out.println("unsure");
		} else if (sads.size() > happys.size()) {
			System.out.println("sad");
		} else {
			System.out.println("happy");
		}
		
		
		sc.close();
	}
	
	public static int[] getPi(String pattern) {
		int m = pattern.length();
		int j = 0;
		char[] p = pattern.toCharArray();
		int[] pi = new int[m];
		
		for (int i=1;i<m;i++) {
			while(j>0 && p[i] != p[j]) {
				j = pi[j-1];
			}
			if (p[i] == p[j]) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	public static ArrayList<Integer> kmp(String str, String pattern) {
		char[] s = str.toCharArray();
		char[] p = pattern.toCharArray();
		int n = str.length(), m = pattern.length(), j = 0;
		int[] pi = getPi(pattern);
		ArrayList<Integer> results = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			while(j>0 && s[i] != p[j]) {
				j = pi[j-1];
			}
			
			if (s[i] == p[j]) {
				if (j==m-1) {
					results.add(i-m+1);
					j = pi[j];
				} else {
					j++;
				}
				
			}
		}
		return results;
	}
}
