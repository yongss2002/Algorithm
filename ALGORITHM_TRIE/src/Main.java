import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static final int ALPHABET_SIZE = 26;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(sc.readLine());
		for (int t = 0; t < TC; t++) {
			
		}
		sc.close();
	}

	static class Node {

		private String character;
		private int value;
		private Node[] children;
		private boolean leaf;

		public Node(String character) {
			this.character = character;
			children = new Node[ALPHABET_SIZE];

		}

		public String getCharacter() {
			return character;
		}

		public void setCharacter(String character) {
			this.character = character;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node[] getChildren() {
			return children;
		}

		public void setChildren(Node[] children) {
			this.children = children;
		}

		public Node getChild(int index) {
			return children[index];
		}

		public void setChild(int index, Node node, int value) {
			node.setValue(value);
			this.children[index] = node;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		@Override
		public String toString() {
			return this.character;
		}

	}

	static public class Trie {

		private Node root;
		private int indexOfSingleChild;

		public Trie() {
			this.root = new Node("");
		}

		public void insert(String key, int value) {
			Node temp = root;

			for (int i = 0; i < key.length(); i++) {
				char c = key.charAt(i);
				int asciiIndex = transformASCIIIndex(c);
				if (temp.getChild(asciiIndex) == null) {
					Node node = new Node(String.valueOf(c));
					temp.setChild(asciiIndex, node, value);
					temp = node;
				} else {
					temp = temp.getChild(asciiIndex);
				}
			}
			temp.setLeaf(true);
		}

		public boolean search(String key) {
			Node trieNode = root;

			for (int i = 0; i < key.length(); i++) {
				char c = key.charAt(i);
				int asciiIndex = transformASCIIIndex(c);
				if (trieNode.getChild(asciiIndex) == null) {
					return false;
				} else {
					trieNode = trieNode.getChild(asciiIndex);
				}
			}
			return true;
		}

		public Integer searchAsMap(String key) {
			Node trieNode = root;

			for (int i = 0; i < key.length(); i++) {
				char c = key.charAt(i);
				int asciiIndex = transformASCIIIndex(c);
				if (trieNode.getChild(asciiIndex) == null) {
					return null;
				} else {
					trieNode = trieNode.getChild(asciiIndex);
				}
			}
			return trieNode.getValue();
		}
		
		public List<String> allWordsWithPrefix(String prefix) {
			Node trieNode = root;
			List<String> allWords = new ArrayList<>();
			for (int i=0;i<prefix.length();i++) {
				int asciiIndex = transformASCIIIndex(prefix.charAt(i));
				trieNode = trieNode.getChild(asciiIndex);
			}
			collect(trieNode, prefix, allWords);
			return allWords;
		}
		
		private void collect(Node node, String prefix, List<String> allWords) {
			if (node == null) {
				return;
			}
			
			if (node.isLeaf()) {
				allWords.add(prefix);
			}
			
			for (Node childNode : node.getChildren()) {
				if (childNode == null) {
					continue;
				}
				String childChar = childNode.getCharacter();
				collect(childNode, prefix+childChar, allWords);
			}
		}
		
		public void sort() {
			List<String> list = allWordsWithPrefix("");
			for (String s : list) {
				System.out.println(s);
			}
		}
		
		public String longestCommonPrefix() {
			Node trieNode = root;
			String longestCommonPrefix = "";
			
			while(countNumOfChildren(trieNode)==1 && !trieNode.isLeaf()) {
				trieNode = trieNode.getChild(indexOfSingleChild);
				longestCommonPrefix = longestCommonPrefix + String.valueOf((char)(indexOfSingleChild-'a'));
			}
			return longestCommonPrefix;
			
		}
		
		private int countNumOfChildren(Node trieNode) {
			int num = 0;
			for(int i=0;i<trieNode.getChildren().length;i++) {
				if (trieNode.getChild(i) != null) {
					num++;
					indexOfSingleChild = i;
				}
			}
			return num;
		}

		private int transformASCIIIndex(char c) {
			return c - 'a';
		}
	}
}
