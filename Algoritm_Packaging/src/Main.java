import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main {
	
	static int n, capacity;
	static int[] volume;
	static int[] need;
	static int[][] cache;
	static String[] name;
	static ArrayList<Integer> picked;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();

		for (int a = 0; a < testCase; a++) {
			n = sc.nextInt();
			volume = new int[n];
			need = new int[n];
			name = new String[n];
			cache = new int[1001][100];
			picked = new ArrayList<Integer>();
			
			for (int i=0;i<1001;i++) {
				for (int j=0;j<100;j++) {
					cache[i][j] = -1;
				}
			}
			
			capacity = sc.nextInt();
			for (int i =0; i<n;i++) {
				name[i] = sc.next();
				volume[i] = sc.nextInt();
				need[i] = sc.nextInt();
			}
			int maxCapacity = pack(capacity, 0);
			reconstruct(capacity, 0);
			System.out.print(maxCapacity + " ");
			System.out.print(picked.size());
			System.out.println();
			for (int i =0;i<picked.size();i++)  {
				System.out.println(name[picked.get(i)]);
			}
		}
	}
	
	static int pack(int capacity, int item) {
		if (item == n) return 0;
		int ret = cache[capacity][item];
		
		if (ret != -1) return ret;
		
		ret = pack(capacity, item+1);
		
		if (capacity >= volume[item]) {
			int a = pack(capacity-volume[item], item+1) + need[item];
			if (a > ret) {
//				picked.add(item);
				ret = a;
			}
		}
		cache[capacity][item] = ret;
		return ret;
		
	}
	
	static void reconstruct(int capacity, int item) {
		if(item == n) {
			return;
		}
		
		if (pack(capacity, item) == pack(capacity, item+1)) {
			reconstruct(capacity, item+1);
		} else {
			picked.add(item);
			reconstruct(capacity - volume[item], item+1);
		}
	}
}
