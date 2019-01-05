import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int V,E;
	static Edge[] arr;
	static int[] parent;
	static int minCost = 0;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		arr = new Edge[E];
		parent = new int[V+1];
		for(int i=0;i<E;i++) {
			arr[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		for(int i = 1;i<=V;i++) {
			parent[i] = i;
		}
		
		Arrays.sort(arr, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.cost - o2.cost;
			}
			
		});
		
		for (int i=0;i<E;i++) {
			int RootX = findRoot(arr[i].x);
			int RootY = findRoot(arr[i].y);
			if (RootX == RootY) {
				continue;
			} else {
				parent[RootX] = RootY;
				minCost += arr[i].cost;
			}
		}
		
		System.out.println(minCost);
	}
	
	static int findRoot(int a) {
		if (a == parent[a]) {
			return a;
		} else {
			parent[a] = findRoot(parent[a]);
			return parent[a];
		}
	}
	
	static class Edge {
		int x,y,cost;
		public Edge(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}
