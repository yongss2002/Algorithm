import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/sample_input.txt"));
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(sc.readLine());

		int[] nums = new int[TC];

		for (int t = 0; t < TC; t++) {
			nums[t] = Integer.parseInt(sc.readLine());
		}
		
		
		Stack<Integer> stack = new Stack<Integer>();
		Queue<String> queue = new LinkedList<String>();
		
		int currentNum = 1;
		int currentIndex = 0;
		stack.push(currentNum);
		currentNum++;
		queue.add("+");
		boolean isSolved = true;
		
		while(stack.isEmpty() == false) {
			if (currentIndex >= TC) {
				break;
			}
			int stackTop = stack.peek();
			if (nums[currentIndex] == stackTop) {
				stack.pop();
				queue.add("-");
				currentIndex++;
				if(stack.isEmpty() && currentNum <= TC) {
					stack.push(currentNum);	
					currentNum++;
					queue.add("+");
				}
				
			} else if (nums[currentIndex] > stackTop) {
				// add stack
				stack.push(currentNum);
				currentNum++;
				queue.add("+");
			} else if (nums[currentIndex] < stackTop) {
				// impossible state
				isSolved = false;
				break;
			}
		}
		
		if (isSolved) {
			while(queue.isEmpty()==false) {
				System.out.println(queue.poll());
			}
		} else {
			System.out.println("NO");
		}
		
		sc.close();
	}

}
