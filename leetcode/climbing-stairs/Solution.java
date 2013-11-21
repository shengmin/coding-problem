public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.climbStairs(0));
    System.out.println(s.climbStairs(1));
    System.out.println(s.climbStairs(2));
    System.out.println(s.climbStairs(1000));
  }

  public int climbStairs(int n) {
    if (n <= 1) return 1;

    int[] table = new int[n + 1];
    table[0] = 1;
    table[1] = 1;

    for (int i = 2; i <= n; i++) {
      table[i] = table[i - 1] + table[i - 2];
    }

    return table[n];
  }
}
