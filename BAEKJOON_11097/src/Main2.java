import java.util.Arrays;

public class Main2 {

	public static void main(String[] args) throws Exception {
//		int[] arr = {4,2,0};
//		System.out.println(solution(arr, 3));
//		int[] arr = { 4, 4, 3, 3, 1, 0 };
//		System.out.println(solution(arr, 6));
//		int[] arr = { 3, 4, 3, 0, 2, 2, 3, 0, 0 };
//		System.out.println(solution(arr, 9));
		int[] arr = { 0};
		System.out.println(solution(arr, 1));
		
	}

	static int solution(int[] ranks, int N) {
		//먼저 정렬을 해줌
		Arrays.sort(ranks);
		//총합 
		int sum = 0;
		//이전에 나왔던 랭크 저장
		int preRank = -1;
		//동일한 랭크 몇개 나왔는지 카운트 
		int currentCnt = 0;
		//ranks 를 순서대로 순회
		for (int i = 0; i < N; i++) {
			int rank = ranks[i];
			//이전꺼랑 현재 랭크가 같으면 카운트 증가 
			if (preRank == rank) {
				currentCnt++;
			} else if (preRank > -1) {
				//이전꺼랑 다르면서 최초 진입이 아닌 경우 
				if (rank == preRank + 1) {
					//이전 랭크 +1 인경우 보고 가능 
					sum += currentCnt;
					currentCnt = 1;
					preRank = rank;
				} else {
					//이전 랭크 +1 이 아닌 경우 카운트를 1로 수정, 이전의 랭크를 현재 랭크로 수정 
					currentCnt = 1;
					preRank = rank;
				}
			} else {
				preRank = rank;
				currentCnt++;
			}
		}
		return sum;
	}
}
