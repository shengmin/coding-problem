public class Solution {
  public int reverse(int x) {

    boolean isNegative = false;
    if (x < 0) {
      isNegative = true;
      x = -1 * x;
    }

    int[] digits = new int[11];
    int i = -1;

    for (; x > 0;) {
      int r = x % 10;
      digits[++i] = r;
      x /= 10;
    }

    int y = 0;
    int m = 1;

    for (int j = i; j >= 0; j--, m *= 10) {
      y += m * digits[j];
    }

    return isNegative ? -1 * y : y;
  }
}
