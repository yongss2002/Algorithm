public class Main {

	// Main.java
	public static void main(String[] args) throws Exception {
		String str = "    -0000000001234500";
		char[] arr = str.toCharArray();
		System.out.println(solution(arr));

	}

	static int solution(char[] S) {
		int size = S.length;
		int ans = 0;
		int multiply = 1;
		int sign = 1;
		int startPos = 0;
		for (; startPos < size; startPos++) {
			if (S[startPos] != ' ') {
				break;
			}
		}
		for (int i = startPos; i < size; i++) {
			char current = S[i];
			if (current == '+' || current == '-') {
				if (i != startPos) {
					return 0;
				} else if (current == '-') {
					sign = -1;
				}
			} else if (current != '0') {
				startPos = i;
				break;
			}
		}
		for (int i=0;i<size - startPos - 1;i++) {
			multiply *= 10;
		}
		multiply = (int) Math.pow(10, size - startPos - 1);
		for (int i = startPos; i < size; i++) {
			char current = S[i];
			if (current == ' ') {
				return 0;
			} else if (current == '-' || current == '+') {
				return 0;
			} else if (current > 57 || current < 48) {
				return 0;
			} else {
				ans += (current - '0') * multiply;
			}
			multiply /= 10;
		}
		ans *= sign;
		if (ans > 2147483647 || ans < -2147483648) {
			return 0;
		} else {
			return ans;
		}
	}
}
