import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static int R,C;
	static char[][] matrix;
	static boolean alpha[];
	static int maxLength = 0;
	static int xMove[] = {0, 0, -1, 1};
	static int yMove[] = {1, -1 , 0, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
			
		String[] splited = sc.readLine().split(" ");
		R = Integer.parseInt(splited[0]);
		C = Integer.parseInt(splited[1]);
		
		
		matrix = new char[R][C];
		alpha = new boolean [26];
		
		for (int i=0;i<R;i++) {
			String line = sc.readLine();
			for (int j=0;j<C;j++) {
				matrix[i][j] = line.charAt(j);
			}
			
		}
		//데이터 세
		DFS(0,0,1);
		System.out.println(maxLength);
	}
	
	public static void DFS(int startX, int startY, int length) {
		if (length > maxLength) {
			maxLength = length;
		}
		int alphaIndex = (matrix[startY][startX]) - 65;
		alpha[alphaIndex] = true;
		for (int i=0;i<4;i++) {
			int nextX = startX + xMove[i];
			int nextY = startY + yMove[i];
			if (canGo(nextX, nextY)) {
				DFS(nextX, nextY, length+1);
				
			}
			
		}
		alpha[alphaIndex] = false;
		
		
	}
	
	public static boolean canGo(int x, int y) {
		boolean canGo = true;
		if (x < 0 || y < 0 || x >= C || y >= R) {
			canGo = false;
		} else if (alpha[(matrix[y][x]) - 65]) {
			canGo = false;
		}
		
		return canGo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
