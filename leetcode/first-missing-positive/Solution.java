public class Solution {
  public int firstMissingPositive(int[] A) {
    for (int i = 0; i < A.length;) {
      int a = A[i];
      if (a <= 0 || a - 1 == i) {
        // Skip non positive numbers
        // or positive number that's already in-place
        i++;
        continue;
      }

      if (a > A.length || A[a - 1] == a) {
        // Swap with some non positive number
        // or when the other number is already in-place
        A[i++] = -1;
        continue;
      }

      A[i] = A[a - 1];
      A[a - 1] = a;
    }

    for (int i = 0; i < A.length; i++) {
      if (A[i] <= 0) return i + 1;
    }

    return A.length + 1;
  }
}
