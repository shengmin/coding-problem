public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.divide(10, 2));
    System.out.println(s.divide(9, 3));
    System.out.println(s.divide(23, 2));
    System.out.println(s.divide(2147483647, 1));
    System.out.println(s.divide(-1010369383, -2147483648));
    System.out.println(s.divide(-1, -1));
  }

  public int divide(int _dividend, int _divisor) {
    long dividend = _dividend;
    long divisor = _divisor;
    boolean isPositive = (dividend >= 0 && divisor >= 0) || (dividend <= 0 && divisor <= 0);
    if (dividend < 0) dividend = -dividend;
    if (divisor < 0) divisor = -divisor;

    int quotient = 0;
    while (divisor <= dividend) {
      long shiftedDivisor = divisor;
      int shiftCount = 0;
      for (; (shiftedDivisor << 1) <= dividend; shiftedDivisor <<= 1, shiftCount++);

      dividend -= shiftedDivisor;
      quotient |= 1 << shiftCount;
    }

    return isPositive ? quotient : -quotient;
  }
}
