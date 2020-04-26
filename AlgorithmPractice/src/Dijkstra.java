import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class Dijkstra {
	public static final int INF = 1000000000;
	static Vector<Vector<IntegerPair>> AdjList=new Vector<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		int V, E, s, u, v, w;

	    /*
	    // Graph in Figure 4.17
	    5 7 2
	    2 1 2
	    2 3 7
	    2 0 6
	    1 3 3
	    1 4 6
	    3 4 5
	    0 4 1
	    */

	    File f = new File("in_05.txt");
	    Scanner sc = new Scanner(f);
	    V = sc.nextInt();
	    E = sc.nextInt();
	    s = sc.nextInt();

	    AdjList.clear();
	    for (int i = 0; i < V; i++) {
	      Vector< IntegerPair > Neighbor = new Vector < IntegerPair >();
	      AdjList.add(Neighbor); // add neighbor list to Adjacency List
	    }

	    for (int i = 0; i < E; i++) {
	      u = sc.nextInt();
	      v = sc.nextInt();
	      w = sc.nextInt();
	      AdjList.get(u).add(new IntegerPair (v, w)); // first time using weight
	    }
	    
	    //DIjkstra!!
	    Vector<Integer> dist = new Vector<>();
	    dist.addAll(Collections.nCopies(V, INF));
	    dist.set(s, 0);
	    PriorityQueue<IntegerPair> pq = new PriorityQueue<>(1, new Comparator<IntegerPair>() {
	    		@Override
	    		public int compare(IntegerPair o1, IntegerPair o2) {
	    			// TODO Auto-generated method stub
	    			return o1.w - o2.w;
	    		}
	    });
	    
	    pq.offer(new IntegerPair(0, s))
	}
	
	
	static class IntegerPair {
		int v, w;

		public IntegerPair(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
}
