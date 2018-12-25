import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Main {
	static int MM, DD, hh, mm, ss, firstNum, lastNum, minNum, capNum;
	static Vector<Visited> complete;
	static Visited visitedHistory;
	static Stack<Integer> history2;
	static int[] move0 = { 1, 10 };
	static int[] move1 = { -1, 1, 9, 10 };
	static int[] move9 = { -1, 9, 10 };
	static int[] move10 = { -10, -9, 1, 10 };
	static int[] move11 = { -10, -9, -1, 1, 9, 10 };
	static int[] move19 = { -10, -1, 9 };
	static int[] move20 = { -10, -9, 1, 9 };
	static int[] move21 = { -10, -9, -1, 1, 8, 9 };
	static int[] move27 = { -10, -9, -1, 1, 8 };
	static int[] move28 = { -9, -10, -1 };
	static int[] move29 = { -9, -8, 1 };
	static int[] move30 = { -9, -8, -1, 1 };
	static int[] move35 = { -9, -8, -1 };

	static int[] getMoves(int num) {
		int[] moves;
		if (num == 0) {
			moves = move0;
		} else if (num < 9 && num > 0) {
			moves = move1;
		} else if (num == 10) {
			moves = move10;
		} else if (10 < num && num < 19) {
			moves = move11;
		} else if (num == 19) {
			moves = move19;
		} else if (num == 20) {
			moves = move20;
		} else if (num < 27) {
			moves = move21;
		} else if (num == 27) {
			moves = move27;
		} else if (num == 28) {
			moves = move28;
		} else if (num == 29) {
			moves = move29;
		} else if (num < 35) {
			moves = move30;
		} else {
			moves = move35;
		}
		return moves;
	}

	public static void main() throws FileNotFoundException {
		System.setIn(new FileInputStream(new File("./sample_input.txt")));
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		for (int tc = 0; tc < testcase; tc++) {
			String date = sc.next();
			String sM = date.substring(0, 2);
			String sD = date.substring(3, 5);
			date = sc.next();
			String sH = date.substring(0, 2);
			String sm = date.substring(3, 5);
			String sss = date.substring(6, 8);
			MM = Integer.parseInt(sM);
			DD = Integer.parseInt(sD);
			hh = Integer.parseInt(sH);
			mm = Integer.parseInt(sm);
			ss = Integer.parseInt(sss);
			firstNum = (DD + ss) % 36;
			lastNum = (MM + hh + mm) % 36;
			minNum = mm / 10;
			capNum = ss / 10;
			complete = new Vector<Visited>();
			history2 = new Stack<Integer>();

			DFSBT(firstNum);

			System.out.println(complete);
		}
	}

	// 다음의 지점으로 갈수 있는지 계산, 다음의 지점으로 이동한 상태
	static class Visited {
		int visited = 0;
		int twiceVisited = 0;

		boolean visit(int n) {
			int visitedNum = getNumOf1(visited);
			int twiceVisitedNum = getNumOf1(twiceVisited);
			if (visitedNum + twiceVisitedNum > 10) {
				return false;
			}
			int index = ++n;
			// 세번째 방문이다.
			if ((twiceVisited & (1 << index)) == 1) {
				return false;
				// 두번째 방문인 경우
			} else if ((visited & (1 << index)) == 1) {
				// 숫자를 두번째 방문
				if (n < 10) {
					return false;
					// 허용된 대문자 갯수보다 두번 누른 키보드가 많아지는 순간(대문자 허용갯수 넘거간거임)
				} else if (getNumOf1(twiceVisited) > capNum) {
					return false;
				} else {
					twiceVisited = twiceVisited | (1 << index);
					gajichigi(index);
				}
				// 첫번째 방문이다.
			} else {
				visited = visited | (1 << index);
				return gajichigi(index);
			}
			return false;
		}

		private boolean gajichigi(int index) {
			// 현재까지의 문자열 길이
			int visitedNum = getNumOf1(visited);
			int twiceVisitedNum = getNumOf1(twiceVisited);
			int numOfNum = getNumOfNum(visited);
			int level = visitedNum + twiceVisitedNum;
			// 문자열 길이 10 완성
			if (level == 10) {
				// 마지막 번호가 아닌 경우
				if (--index != lastNum) {
					return false;
					// 숫자최소갯수 못채웠을 경우
				} else if (numOfNum < minNum) {
					return false;
					// 대문자 갯수를 채우지 못하는 경우
				} else if (numOfNum + capNum > 10) {
					return false;
				} else {
					// 성공케이스
					complete.add(this);
					return true;
				}
			} else {
				return true;
			}
		}

		private int getNumOf1(int value) {
			int count = 0;
			while (value != 0) {
				count += (value & 1);
				value = value >>> 1;
			}
			return count;
		}

		private int getNumOfNum(int value) {
			int count = 0;
			for (int i = 0; i < 10; i++) {
				count += (value & 1);
				value = value >>> 1;
			}
			return count;
		}

		public int getVisited() {
			return visited;
		}

		public void setVisited(int visited) {
			this.visited = visited;
		}

		public int getTwiceVisited() {
			return twiceVisited;
		}

		public void setTwiceVisited(int twiceVisited) {
			this.twiceVisited = twiceVisited;
		}

		public boolean isCompleted() {
			boolean com = false;
			if (getVisited() + getTwiceVisited() == 10) {
				com = true;
			}
			return com;
		}

		public void backTracking(int lastNode) {
			int position = lastNode + 1;
			if ((twiceVisited & (1 << position)) == 1) {
				twiceVisited = twiceVisited & ~(1 << position);
			} else {
				visited = visited & ~(1 << position);
			}
		}
	}

	static public void DFSBTUtil() {
		if(history2.isEmpty())return;
		int startvertex = history2.peek();
		int[] moves = getMoves(startvertex);
		for (int i = 0; i < moves.length; i++) {
			int next = startvertex + moves[i];
			if (visitedHistory.visit(next)) {
				if (visitedHistory.isCompleted()) {
					// 하나 팝 해줘야함
					visitedHistory.backTracking(next);
					history2.pop();
					return;
				} else {
					history2.push(next);
					DFSBTUtil();
				}
			} else {
				visitedHistory.backTracking(next);
			}
		}
		history2.pop();
	}

	// static public void DFSBTUtil (int startvertex) {
	// while (!history2.empty()) {
	// int v = history2.peek();
	// int[] moves = getMoves(v);
	// for (int i = 0; i < moves.length; i++) {
	// int next = v + moves[i];
	// if (visitedHistory.visit(next)) {
	// if (visitedHistory.isCompleted()) {
	// //하나 팝 해줘야함
	// visitedHistory.backTracking(next);
	// } else {
	// history2.push(next);
	// DFSBTUtil(next);
	// }
	// } else {
	// visitedHistory.backTracking(next);
	// }
	// history2.pop();
	// }
	// }
	// }
	static public void DFSBT(int startVertex) {
		visitedHistory = new Visited();
		history2.push(startVertex);
		visitedHistory.visit(startVertex);
		DFSBTUtil();
	}
}
