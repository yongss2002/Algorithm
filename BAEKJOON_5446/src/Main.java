import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
	
	//alphabet plus number
	static final int NUM_OF_CHAR = 1000;
	static int N, M;
	static int delNum;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCase = Integer.parseInt(sc.readLine());
		
		for (int tc = 0; tc<TestCase;tc++) {
			Trie trie = new Trie();
			ArrayList<String> delStr = new ArrayList<>();
			String[] keepStr;
			
			N = Integer.parseInt(sc.readLine());
			
			keepStr = new String[N];
			
			for (int i=0;i<N;i++) {
				delStr.add(i, sc.readLine());
				trie.insert(delStr.get(i), true);
			}
			
			N = Integer.parseInt(sc.readLine());
			for (int i=0;i<N;i++) {
				keepStr[i] = sc.readLine();
				trie.insert(keepStr[i], false);
			}
			delNum = 0;
			trie.delNum();
			System.out.println(delNum);
		}
		
		
		
		sc.close();
	}
	
	static class Trie {
		private Node root;
		
		public Trie() {
			this.root = new Node("");
		}
		
		public void insert(String key, boolean del) {
			Node tempNode = root;
			for (int i=0;i<key.length();i++) {
				char ch = key.charAt(i);
				int index = transformASCIIIndex(ch);
				if (index < 0 || index >= NUM_OF_CHAR) {
					index = 0;
				}
				if (tempNode.getChild(index) == null) {
					Node node = new Node(String.valueOf(ch));
					tempNode.setChild(index, node, del);
					tempNode = node;
				} else {
					tempNode = tempNode.getChild(index);
					tempNode.setDel(del);
				}
			}
			if (del == true) {
				tempNode.setValid(del);
			}
			tempNode.setLeaf(true);
		}
		
		public void delNum() {
			this.root.delNum();
		}
		
		private int transformASCIIIndex(char c) {
			return c - '.';
		}
	}
	
	static class Node {
		private boolean del;
		private String character;
		private Node[] chidren;
		private boolean leaf;
		private boolean isValid = false;
		
		public Node(String character) {
			this.character = character;
			chidren = new Node[NUM_OF_CHAR];
		}
		
		public boolean isDel() {
			return del;
		}

		public void setDel(boolean del) {
			this.del = del;
		}

		public String getCharacter() {
			return character;
		}
		public void setCharacter(String character) {
			this.character = character;
		}
		public Node[] getChidren() {
			return chidren;
		}
		public void setChidren(Node[] chidren) {
			this.chidren = chidren;
		}
		public Node getChild(int index) {
			return this.chidren[index];
		}
		public void setChild(int index, Node node, boolean value) {
			node.setDel(value);
			this.chidren[index] = node;
		}
		public boolean isLeaf() {
			return leaf;
		}
		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}
		public boolean isValid() {
			return isValid;
		}

		public void setValid(boolean isValid) {
			this.isValid = isValid;
		}
		
		public void delNum() {
			if (isDel()) {
				delNum++;
				return;
			} 
			
			if (isLeaf() && isValid()) {
				delNum++;
			}
			
			for (int i=0;i<getChidren().length;i++) {
				if (getChild(i) != null) {
					getChild(i).delNum();
				}
			}
		}
	}
	
}
