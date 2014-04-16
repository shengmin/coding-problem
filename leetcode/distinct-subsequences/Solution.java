public class Solution {
  public int numDistinct(String s, String t) {
    if (t.length() > s.length()) return 0;

    // table[i][j] = number of distinct subsequences of t[0..i-1] in s[0..j-1]
    int[][] table = new int[t.length() + 1][s.length() + 1];
    for (int j = 0; j <= s.length(); j++) {
      // "" is a subsequence of any s[0..j]
      table[0][j] = 1;
    }

    for (int i = 1; i <= t.length(); i++) {
      for (int j = 1; j <= s.length(); j++) {
        if (t.charAt(i - 1) == s.charAt(j - 1)) {
          table[i][j] = table[i][j - 1] + table[i - 1][j - 1];
        } else {
          table[i][j] = table[i][j - 1];
        }
      }
    }

    return table[t.length()][s.length()];
  }
}
