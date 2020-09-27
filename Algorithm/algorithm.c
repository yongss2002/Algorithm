#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[]) {
	/* code */
	int arr[] = { 4, 2, 0 };
	printf(solution(arr, 3));
	return 0;
}

int static compare(const void *first, const void *second) {
	if (*(int*) first > *(int*) second)
		return 1;
	else if (*(int*) first < *(int*) second)
		return -1;
	else
		return 0;
}

int static solution(int* ranks, int N) {
	int arr_size = sizeof(ranks) / sizeof(int);
		qsort(ranks, arr_size, sizeof(int), compare);

		int sum = 0;
		int preRank = -1;
		int currentCnt = 0;
		for (int i = 0; i < arr_size; i++) {
			int rank = ranks[i];
			if (preRank == rank) {
				currentCnt++;
			} else if (preRank > -1 && rank == preRank + 1) {
				sum += currentCnt;
				currentCnt = 0;
				preRank = -1;
			} else {
				preRank = rank;
				currentCnt++;
			}
		}
		return sum;
}
