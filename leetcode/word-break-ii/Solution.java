import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.wordBreak("catsanddog", new HashSet<String>(){{
        add("cat");
        add("cats");
        add("and");
        add("sand");
        add("dog");
    }}));
  }

  public ArrayList<String> wordBreak(String s, Set<String> dict) {
    int length = s.length();
    ArrayList<ArrayList<String>> table =
      new ArrayList<ArrayList<String>>(length + 1);

    for (int i = 0; i <= length; i++) {
      table.add(new ArrayList<String>());
    }

    table.get(0).add("");

    for (int i = 1; i <= length; i++) {
      ArrayList<String> newList = table.get(i);
      for (int j = i; j >= 0; j--) {
        ArrayList<String> list = table.get(j);
        String word = s.substring(j, i);
        for (String sentence: list) {
          if (dict.contains(word)) {
            newList.add(sentence + (sentence.length() > 0 ? " " : "") + word);
          }
        }
      }
    }

    return table.get(length);
  }
}
