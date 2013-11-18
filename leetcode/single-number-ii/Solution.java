public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.singleNumber(new int[] { 8, 8, 8, 2, 999, 6, 6, 6, 2, 2 }));
  }

  public int singleNumber(int[] A) {
    int once = A[0];
    int twice = 0;
    int thrice = ~once;

    for (int i = 1; i < A.length; i++) {
      int a = A[i];
      int _once = once;
      int _twice = twice;
      int _thrice = thrice;


      once = ((a & _thrice) | _once) & ~(a & _once);
      twice = ((a & _once) | _twice) & ~(a & _twice);
      thrice = ((a & _twice) | _thrice) & ~(a & _thrice);
    }

    return once;
  }
}
