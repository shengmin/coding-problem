import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.ladderLength("hit", "cog", 
      toHashSet(new String[] { "hot","dot","dog","lot","log" })));
  }

  private static HashSet<String> toHashSet(String[] list) {
    HashSet<String> words = new HashSet<String>(list.length * 2);
    for (String word : list) {
      words.add(word);
    }
    return words;
  }

  private List<String> getNextList(Set<String> dictionary, String word) {
    char[] wordCharacters = word.toCharArray();
    List<String> list = new ArrayList<String>();

    for (int i = 0; i < wordCharacters.length; i++) {
      for (char c = 'a'; c <= 'z'; c++) {
        if (c == word.charAt(i)) continue;
        wordCharacters[i] = c;
        String newWord = new String(wordCharacters);
        wordCharacters[i] = word.charAt(i);
        if (dictionary.contains(newWord)) list.add(newWord);
      }
    }

    return list;
  }

  public int ladderLength(String start, String end, HashSet<String> dictionary) {
    dictionary.add(start);
    dictionary.add(end);
    Map<String, List<String>> map = new HashMap<String, List<String>>(dictionary.size() * 2);
    for (String word: dictionary) {
      map.put(word, getNextList(dictionary, word));
    }

    Set<String> visited = new HashSet<String>();
    LinkedList<String> queue = new LinkedList<String>();
    int length = 1;
    queue.add(start);

    while (!queue.isEmpty()) {
      for (int i = 0, size = queue.size(); i < size; i++) {
        String word = queue.poll();
        if (word.equals(end)) return length;
        List<String> nextList = map.get(word);
        if (nextList == null) continue;
        for (String nextWord: nextList) {
          if (visited.contains(nextWord)) continue;
          queue.offer(nextWord);
          visited.add(nextWord);
        }
      }
      length++;
    }

    return 0;
  }
}
