public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.maxSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
  }

  public int maxSubArray(int[] A) {
    int max = Integer.MIN_VALUE;
    int sum = 0;

    for (int i = 0; i < A.length; i++) {
      sum = Math.max(A[i], sum + A[i]);
      max = Math.max(sum, max);
    }

    return max;
  }
}
