import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    String[] words = new String[] { "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa" };
    System.out.println(s.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", new HashSet<String>(Arrays.asList(words))));
    String[] words2 = new String[] { "cat", "cats", "and", "sand", "dog" };
    System.out.println(s.wordBreak("catsanddog", new HashSet<String>(Arrays.asList(words2))));
  }

  public ArrayList<String> wordBreak(String s, Set<String> dict) {
    int length = s.length();
    ArrayList<List<Integer>> table =
      new ArrayList<List<Integer>>(length + 1);

    for (int i = 0; i <= length; i++) {
      table.add(new ArrayList<Integer>());
    }

    table.get(0).add(-1);

    for (int i = 1; i <= length; i++) {
      List<Integer> newList = table.get(i);
      for (int j = i; j >= 0; j--) {
        List<Integer> list = table.get(j);
        String word = s.substring(j, i);
        if (list.size() > 0 && dict.contains(word)) {
          newList.add(j);
        }
      }
    }

    ArrayList<String> words = new ArrayList<String>();
    findWords(s, words, table, length, new StringBuilder(length * 2));
    return words;
  }

  private void findWords(
    String s, ArrayList<String> words, ArrayList<List<Integer>> table, int level, StringBuilder sb) {

    if (level <= 0) {
      if (sb.length() > 0) {
        words.add(sb.toString());
      }
      return;
    }

    for (int i: table.get(level)) {
      boolean needSpace = sb.length() > 0;
      if (needSpace) sb.insert(0, ' ');
      String word = s.substring(i, level);
      sb.insert(0, word);

      findWords(s, words, table, i, sb);

      sb.delete(0, word.length());
      if (needSpace) sb.delete(0, 1);

    }

  }
}
