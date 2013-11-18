public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    for (int i = 1; i <= 10; i++) {
      System.out.println(s.numTrees(i));
    }
  }

  public int numTrees(int n) {
    int[] table = new int[n + 1];

    table[0] = 0;
    table[1] = 1;

    for (int i = 2; i <= n; i++) {
      int sum = 0;
      for (int j = 1; j <= i; j++) {
        int left = j - 1;
        int right = i - j;
        if (left == right) sum += table[left];
        else sum += table[left] + table[right];
      }

      table[i] = sum;
    }

    return table[n];
  }
}
