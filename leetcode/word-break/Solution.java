import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.wordBreak("leetcode", new HashSet<String>(){{
        add("leet");
        add("code");
    }}));
  }

  public boolean wordBreak(String s, Set<String> dict) {
    int length = s.length();
    boolean[] table = new boolean[length + 1];
    table[0] = true;

    for (int i = 1; i <= length; i++) {
      table[i] = isInDictionary(s, dict, table, i);
    }

    return table[length];
  }

  private boolean isInDictionary(String s, Set<String> dict, boolean[] table, int i) {
    for (int j = i; j >= 0; j--) {
      if (table[j] && dict.contains(s.substring(j, i))) return true;
    }
    return false;
  }

}
