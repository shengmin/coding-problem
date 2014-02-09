public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a = new int[] { 1,1,2 };
    print(a, s.removeDuplicates(a));
  }

  private static void print(int[] A, int length) {
    for (int i = 0; i < length; i++) {
      System.out.printf("%d ", A[i]);
    }
    System.out.println();
  }

  public int removeDuplicates(int[] A) {
    if (A.length == 0) return 0;

    int length = 1;
    for (int i = 1; i < A.length; i++) {
      if (A[length - 1] != A[i]) {
        A[length++] = A[i];
      }
    }

    return length;
  }
}
