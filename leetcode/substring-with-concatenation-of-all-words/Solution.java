import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    print(s.findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
    print(s.findSubstring("a", new String[] { "a", "a" }));
    print(s.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[] { "fooo","barr","wing","ding","wing" }));
  }

  static void print(List<Integer> list) {
    for (int n: list) {
      System.out.printf("%d ", n);
    }
    System.out.println();
  }

  public ArrayList<Integer> findSubstring(String text, String[] words) {
    ArrayList<Integer> indices = new ArrayList<Integer>();
    if (words.length == 0) {
      return indices;
    }

    int wordSize = words[0].length();
    int concatSize = wordSize * words.length;

    for (int i = 0; i < wordSize; i++) {
      Map<String, Integer> map = new HashMap<String, Integer>();
      for (String word: words) {
        increment(map, word);
      }

      int concatEnd = i + concatSize;
      if (concatEnd > text.length()) {
        continue;
      }

      for (int j = i; j < concatEnd; j += wordSize) {
        String word = text.substring(j, j + wordSize);
        decrement(map, word);
      }

      if (map.size() == 0) {
        indices.add(i);
      }

      for (int oldStart = i, oldEnd = oldStart + wordSize, newStart = concatEnd, newEnd = newStart + wordSize;
        newEnd <= text.length();
        oldStart = oldEnd, oldEnd += wordSize, newStart = newEnd, newEnd += wordSize) {

        String oldWord = text.substring(oldStart, oldEnd);
        String newWord = text.substring(newStart, newEnd);

        increment(map, oldWord);
        decrement(map, newWord);

        if (map.size() == 0) {
          indices.add(oldEnd);
        }
      }
    }

    return indices;
  }

  void increment(Map<String, Integer> map, String key) {
    adjustCount(map, key, true);
  }

  void decrement(Map<String, Integer> map, String key) {
    adjustCount(map, key, false);
  }

  void adjustCount(Map<String, Integer> map, String key, boolean increment) {
    Integer count = map.get(key);
    if (count == null) {
      count = 0;
    }

    if (increment) {
      count++;
    } else {
      count--;
    }

    if (count == 0) {
      map.remove(key);
    } else {
      map.put(key, count);
    }
  }
}
