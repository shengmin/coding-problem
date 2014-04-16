/**
 * @author shengmin
 * @version 4
 */
class Solution {
  long sum(long count) {
    return (count - 1) * count / 2;
  }

  public int solution(int[] A) {
    long evenCount = 0L;
    long oddCount = 0L;

    for (int i = 0; i < A.length; i++) {
      int a = A[i];
      if (a % 2 == 0) {
        evenCount++;
      } else {
        oddCount++;
      }
    }

    long count = sum(evenCount) + sum(oddCount);
    return count > 1000000000L ? -1 : (int)count;
  }
}
