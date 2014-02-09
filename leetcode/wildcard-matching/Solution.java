import java.util.Arrays;

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
    System.out.println(s.isMatch("", "*"));
    System.out.println(s.isMatch("c", "*?*"));
    System.out.println(s.isMatch("a", ""));
    System.out.println(s.isMatch("ho", "**ho"));
  }

  private int countText(String pattern) {
    int count = 0;
    for (int i = pattern.length() - 1; i >= 0; i--) {
      char c = pattern.charAt(i);
      if (c != '*') count++;
    }
    return count;
  }

  public boolean isMatch(String text, String pattern) {
    int textSize = text.length();
    int patternSize = pattern.length();
    int nonStarCount = countText(pattern);
    if (nonStarCount > textSize) return false;

    boolean[] row = new boolean[textSize + 1];
    boolean[] oldRow = new boolean[textSize + 1];
    boolean[] any = new boolean[textSize + 1];
    boolean[] oldAny = new boolean[textSize + 1];
    Arrays.fill(oldAny, true);
    Arrays.fill(any, true);
    oldRow[0] = row[0] = true;

    for (int i = 1; i <= patternSize; i++) {
      char p = pattern.charAt(i - 1);

      for (int j = 0; j <= textSize; j++) {
        switch (p) {
          case '?':
            row[j] = j > 0 && oldRow[j - 1];
            break;
          case '*':
            row[j] = oldAny[j];
            break;
          default:
            row[j] = j > 0 && oldRow[j - 1] && (p == text.charAt(j - 1));
        }

        any[j] = row[j] || (j > 0 && any[j - 1]);
      }

      for (int j = 0; j <= textSize; j++) {
        oldRow[j] = row[j];
        oldAny[j] = any[j];
        any[j] = row[j] = false;
      }
    }

    return oldRow[textSize];
  }
}
