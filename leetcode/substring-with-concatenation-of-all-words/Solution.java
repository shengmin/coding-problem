import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    print(s.findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
    print(s.findSubstring("a", new String[] { "a", "a" }));
  }

  static void print(List<Integer> list) {
    for (int n: list) {
      System.out.printf("%d ", n);
    }
    System.out.println();
  }

  public ArrayList<Integer> findSubstring(String S, String[] L) {
    Map<String, Integer> words = new HashMap<String, Integer>();
    ArrayList<Integer> indices = new ArrayList<Integer>();
    if (L.length == 0) return indices;
    int wordSize = L[0].length();
    int totalSize = wordSize * L.length;

    for (String word: L) {
      Integer count = words.get(word);
      if (count == null) words.put(word, 1);
      else words.put(word, count + 1);
    }

    for (int i = 0; i < S.length(); i++) {
      if (isPossible(i, S, words, wordSize, totalSize)) indices.add(i);
    }

    return indices;
  }

  boolean isPossible(int start, String text, Map<String, Integer> words, int wordSize, int totalSize) {
    Map<String, Integer> seen = new HashMap<String, Integer>();
    int totalEnd = start + totalSize;

    for (int i = start, end = i + wordSize;
      i < text.length() && end <= text.length() && end <= totalEnd;
      i = end, end += wordSize) {

      String word = text.substring(i, end);
      if (words.containsKey(word)) {
        Integer count = seen.get(word);
        if (count == null) seen.put(word, 1);
        else seen.put(word, count + 1);
      } else {
        break;
      }
    }

    if (seen.size() != words.size()) return false;
    for (Map.Entry<String, Integer> entry: seen.entrySet()) {
      Integer count = words.get(entry.getKey());
      if (count == null) return false;
      if (count != entry.getValue()) return false;
    }
    return true;
  }
}
