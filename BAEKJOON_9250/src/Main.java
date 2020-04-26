import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int N;
	static String[] S;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(sc.readLine());
		S = new String[N];
		Trie root = new Trie();
		for (int i = 0; i < N; i++) {
			S[i] = sc.readLine();
			root.insert(S[i]);
		}
		//create fail function
		Queue<Trie> queue = new LinkedList<Trie>();
		root.setFail(root);
		queue.add(root);
		while(queue.size() != 0) {
			Trie current = queue.poll();
			
			for (int i=0;i<26;i++) {
				Trie next = current.getGo(i);
				if (next == null) {
					continue;
				}
				//fail of root is root
				if (current == root) {
					next.setFail(root);
				} else {
					Trie dest = current.getFail();
					// go has to exist for next char of S
					while(dest!=root && dest.getGo(i) == null) {
						dest = dest.getFail();
					}
					// next char of S
					if (dest.getGo(i) != null) {
						dest = dest.getGo(i);
					}
					next.setFail(dest);
				}
				
				if (next.getFail().isOutput()) {
					next.setOutput(true);
				}
				
				queue.add(next);
			}
		}
		//fail function end
		
		int Q = Integer.parseInt(sc.readLine());
		
		for (int i=0;i<Q;i++) {
			String str = sc.readLine();
			Trie current = root;
			boolean result = false;
			
			for (int c=0;c<str.length();c++) {
				int next = str.charAt(c) - 'a';
				while(current != root && current.getGo(next) == null) {
					current = current.getFail();
				}
				if(current.getGo(next) != null) {
					current = current.getGo(next);
				}
				if (current.isOutput()) {
					result = true;
					break;
				}
			}
			System.out.println(result ? "YES" : "NO");
		}
		sc.close();
	}
	
	static class Trie {
		private Trie[] go;
		private Trie fail;
		private boolean output;
		
		public Trie() {
			go = new Trie[26];
			output = false;
			fail = null;
		}
		
		public Trie getGo(int index) {
			return go[index];
		}
		
		public void setGo(int index, Trie trie) {
			go[index] = trie;
		}
		
		public Trie getFail() {
			return fail;
		}

		public void setFail(Trie fail) {
			this.fail = fail;
		}

		public boolean isOutput() {
			return output;
		}

		public void setOutput(boolean output) {
			this.output = output;
		}

		public void insert(String str) {
			Trie currentTrie = this;
			for (int i=0;i<str.length();i++) {
				char c = str.charAt(i);
				int index = c - 'a';
				if (currentTrie.getGo(index) == null) {
					currentTrie.setGo(index, new Trie());
				}
				currentTrie = currentTrie.getGo(index);
			}
			currentTrie.setOutput(true);
		}
	}
	
}
