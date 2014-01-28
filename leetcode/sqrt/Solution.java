import java.util.*;

public class Solution {
  public static void main(String[] args) {
    for (int i = 0; i <= 10; i++) {
      test(i);
    }
  }

  private static void test(int x) {
    Solution s = new Solution();
    System.out.printf("%d: %d %f", x, s.sqrt(x), Math.sqrt(x));
    System.out.println();
  }

  public int sqrt(int x) {
    int answer = 0;
    int lower = 0;
    int upper = x / 2 + 2;

    while (lower < upper) {
      int mid = lower + (upper - lower) / 2;
      long product = (long)mid * mid;
      if (product == x) return mid;

      if (product < x) {
        lower = mid + 1;
        answer = mid;
      } else {
        upper = mid;
      }
    }

    return answer;
  }
}
