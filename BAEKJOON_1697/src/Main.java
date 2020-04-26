import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int N,K;
	static int min = Integer.MAX_VALUE;
	static Stack<Integer> history;
	static Queue<Integer> queue;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		history = new Stack<>();
		queue = new LinkedList<>();
		N = sc.nextInt();
		K = sc.nextInt();
		System.out.println(BFS(N));
		
	}
	static int BFS(int start) {
		int time = 0;
		boolean[] visited = new boolean[100001];
		Arrays.fill(visited, false);
		visited[start] = true;
		history.push(start);
		queue.add(start);
		
		while(true) {
			int size = queue.size();
			for (int i=0;i<size;i++) {
				int position = queue.poll();
				if (position ==K) {
					return time;
				}
				
				if (position > 0 && visited[position-1] == false) {
					visited[position-1] = true;
					queue.add(position-1);
				}
				
				if (position < 100000 && visited[position +1] == false) {
					visited[position+1] = true;
					queue.add(position+1);
				}
				
				if (position > 0 && position*2<=100000 && visited[position*2] == false) {
					visited[position*2] = true;
					queue.add(position*2);
				}
				
			}
			time++;
		}
	}
	
	static int[] getMoves(int position) {
		int[] moves = null;
		if (position == 0) {
			moves = new int[] {++position};
		} else if(position > K) {
			moves = new int[] {--position};
		} else {
			moves = new int[] {position*2, position+1, position-1};
		}
		return moves;
	}
}
