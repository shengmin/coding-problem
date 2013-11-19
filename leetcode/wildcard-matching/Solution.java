public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.isMatch("aa", "a"));
    System.out.println(s.isMatch("aa", "aa"));
    System.out.println(s.isMatch("aaa", "aa"));
    System.out.println(s.isMatch("aa", "*"));
    System.out.println(s.isMatch("aa", "a*"));
    System.out.println(s.isMatch("aa", "?*"));
    System.out.println(s.isMatch("aab", "c*a*b"));
  }

  public boolean isMatch(String text, String pattern) {
    int textSize = text.length();
    int patternSize = pattern.length();
    boolean[][] table = new boolean[patternSize + 1][textSize + 1];
    table[0][0] = true;

    for (int i = 1; i <= patternSize; i++) {
      char p = pattern.charAt(i - 1);

      for (int j = 1; j <= textSize; j++) {
        char t = text.charAt(j - 1);

        switch (p) {
          case '?':
            table[i][j] = table[i - 1][j - 1];
            break;
          case '*':
            table[i][j] = any(table[i - 1], j + 1);
            break;
          default:
            table[i][j] = table[i - 1][j - 1] && (p == t);
        }
      }
    }

    return table[patternSize][textSize];
  }

  private boolean any(boolean[] array, int end) {
    for (int i = 0; i < end; i++) {
      if (array[i]) return true;
    }
    return false;
  }
}
