public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    int sizeA = 10;
    int sizeB = 5;
    int[] a = new int[100];
    int[] b = new int[sizeB];

    for (int i = 0; i < sizeB; i++) {
      b[i] = i;
    }

    for (int i = 0; i < sizeA; i++) {
      a[i] = i + 100;
    }

    s.merge(a, sizeA, b, sizeB);

    print(a, sizeA + sizeB);
  }

  private static void print(int[] array, int length) {
    for (int i = 0; i < length; i++) {
      System.out.printf("%d ", array[i]);
    }
    System.out.println();
  }

  public void merge(int[] A, int m, int[] B, int n) {
    for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0; k--) {
      if (i < 0) {
        A[k] = B[j--];
      } else if (j < 0) {
        A[k] = A[i--];
      } else {
        if (A[i] < B[j]) {
          A[k] = B[j--];
        } else {
          A[k] = A[i--];
        }
      }
    }
  }
}
