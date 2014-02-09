import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
    test("ab", ".*");
    test("aa", ".*");
    test("aaa", "ab*a*c*a");
    test("aaa", "ab*a");
    test("aa", "a");
    test("aa","aa");
    test("aaa","aa");
    test("aa", "a*");
    test("aab", "c*a*b");
  }

  private static void test(String text, String pattern) {
    Solution s = new Solution();
    System.out.println(s.isMatch(text, pattern));
  }

  private boolean isMatch(String text, char p, boolean[][] table, int iStart, int j) {
    for (int i = iStart; i > 0; i--) {
      if (p == '.' || p == text.charAt(i)) {
        if (table[i - 1][j - 1]) return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public boolean isMatch(String text, String pattern) {
    pattern = '\0' + pattern;
    text = '\0' + text;
    int patternSize = pattern.length();
    int textSize = text.length();
    boolean[][] table = new boolean[textSize][patternSize];
    table[0][0] = true; // empty pattern matches empty string

    for (int i = 0; i < textSize; i++) {
      for (int j = 1; j < patternSize; j++) {
        char p = pattern.charAt(j);

        switch (p) {
          case '.':
            table[i][j] = (i != 0) && table[i - 1][j - 1];
            break;
          case '*':
            table[i][j] = table[i][j - 2] || isMatch(text, pattern.charAt(j - 1), table, i, j - 1);
            break;
          default:
            table[i][j] = (i != 0) && table[i - 1][j - 1] && (text.charAt(i) == p);
        }
      }
    }

    return table[textSize - 1][patternSize - 1];
  }
}
