import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Main {
	
	static int counter = 0;
	static Long count = 0l;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(sc.readLine());
		int[] num = new int[N];
		int[] binaryTree = new int[N*4];
		
		for (int t=0;t<N;t++) {
			num[t] = Integer.parseInt(sc.readLine());
		}
		
		NavigableMap<Integer, Long> mapArr = new TreeMap<>();
		mapArr.put(0, -1l);
		mapArr.put(300001, -1l);
		
		for(int i=0;i<N;i++) {
			int key = num[i];
			Entry<Integer, Long> floor = mapArr.floorEntry(key);
			Entry<Integer, Long> ceiling = mapArr.ceilingEntry(key);
			Long height = Math.max(floor==null ? 0 : floor.getValue(), ceiling==null ? 0 : ceiling.getValue()) + 1;
			count += height;
			mapArr.put(key, height);
			System.out.println(count);
		}
		
		
		sc.close();
	}
	
	static class Node {
		int number;
		Node leftChild;
		Node rightChild;
		
		
		
		public Node(int number) {
			super();
			this.number = number;
		}
		
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public Node getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(Node leftChild) {
			this.leftChild = leftChild;
		}
		public Node getRightChild() {
			return rightChild;
		}
		public void setRightChild(Node rightChild) {
			this.rightChild = rightChild;
		}
		
		
	}
	
	static class BinarySearchTree {
		
		Node root;
		int counter;
		
		public BinarySearchTree() {
			this.root = null;
			counter = 0;
		}
		
		public BinarySearchTree(Node root) {
			this.root = root;
			counter = 0;
		}
		
		void insert(int key) {
			root = insertRec(root, key);
		}
		
		Node insertRec(Node root, int key) {
			counter++;
			if (root == null) {
				Node node = new Node(key);
				this.root = node;
				return this.root;
			}
			
			if (root.getNumber() > key) {
				if (root.leftChild == null) {
					Node node = new Node(key);
					root.leftChild = node;
				} else {
					root.leftChild = insertRec(root.leftChild, key);
				}
			} else if (root.getNumber() < key) {
				if (root.rightChild == null) {
					Node node = new Node(key);
					root.rightChild = node;
				} else {
					root.rightChild = insertRec(root.rightChild, key);	
				}
				
			}
			
			return root;
		}
		
		int getCount() {
			return this.counter;
		}
		
	}
	
}
