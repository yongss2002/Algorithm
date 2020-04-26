import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static String T, P;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		T = sc.readLine();
		P = sc.readLine();
		
		ArrayList <Integer> list = KMP(P, T);
		System.out.println(list.size());
		for (int i=0;i<list.size();i++) {
			System.out.print(list.get(i));
			if (i<list.size()-1) {
				System.out.print(" ");
			}
		}
	}
	
	static ArrayList<Integer> KMP(String pattern, String text) {
		ArrayList<Integer> list = new ArrayList<>();
		char[] p = pattern.toCharArray();
		char[] t = text.toCharArray();
		int n = p.length;
		int m = t.length;
		int j=0;
		
		int[] pi = getPi(pattern);
		
		for (int i=0;i<m;i++) {
			while(j>0 && t[i] != p[j]) {
				j = pi[j-1];
			}
			if (t[i] == p[j]) {
				if (j == n-1) {
					list.add(i-n+2);
					j=pi[j];
				} else {
					j++;
				}
			}
		}
		return list;
	}
	
	static int[] getPi(String pattern) {
		int j = 0;
		int m = pattern.length();
		int[] pi = new int[m];
		char[] p = pattern.toCharArray();
		Arrays.fill(pi, 0);
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
	
}
