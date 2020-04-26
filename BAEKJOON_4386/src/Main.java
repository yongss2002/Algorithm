import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int[] parent;
	static Vertax[] vertax;
	static ArrayList<Edge> arr;
	static int MAX_EDGE = 10000;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		parent = new int[N];
		vertax = new Vertax[N];
		arr = new ArrayList<>();
		for (int i=0;i<N;i++) {
			parent[i] = i;
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			vertax[i] = new Vertax(x,y);	
		}
		
	    for(int i=0;i<N;i++){
	        for(int j =i+1; j < N ;j++){
	        	double dist = Math.sqrt(Math.pow(Math.abs(vertax[i].x - vertax[j].x), 2) + Math.pow(Math.abs(vertax[i].y - vertax[j].y), 2));
	        	Edge e = new Edge(i,j,dist);
	        	arr.add(e);
	        }
	    }
	    Collections.sort(arr, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				double diff = o1.dist - o2.dist;
				if (diff < 0) {
					return -1;
				} else if (diff == 0) {
					return 0;
				} else {
					return 1;
				}
			}
		});
	    
	    double sum = 0;
	    int linkCount = 0;
	    for(int i=0;i<arr.size();i++) {
	    	int rootX = findRoot(arr.get(i).x);
	    	int rootY = findRoot(arr.get(i).y);
	    	if (rootX != rootY) {
	    		sum+=arr.get(i).dist;
	    		merge(rootX, rootY);
	    		linkCount++;
	    		if(linkCount == N-1) break;
	    	}
	    }
	    
	    System.out.println(String.format("%.2f", sum));


	}
	
	static int findRoot(int a) {
		if (parent[a] == a) {
			return a;
		} else {
			parent[a] = findRoot(parent[a]);
			return parent[a];
		}
	}
	
	static void merge (int u, int v) {
		int pu = findRoot(u);
	    int pv = findRoot(v);
	    if(pu > pv){
	        int temp = pu;
	        pu = pv;
	        pv=temp;
	    }
	    parent[pv]=pu;
	}
	
	static class Vertax {
		 double x,y;
		public Vertax(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge {
		int x, y;
		double dist;

		public Edge(int x, int y, double dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
