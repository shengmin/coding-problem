public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] a = new int[] { 1, 2, 3, 1, 4, 5, 1, 6 };
    int l = s.removeElement(a, 1);

    print(a, l);
  }

  private static void print(int[] A, int length) {
    for (int i = 0; i < length; i++) {
      System.out.printf("%d ", A[i]);
    }
    System.out.println();
  }

  public int removeElement(int[] A, int elem) {
    int j = 0;
    for (int i = 0; i < A.length; i++) {
      if (A[i] != elem) {
        A[j++] = A[i];
      }
    }

    return j;
  }
}
