import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    print(s.findLadders("red", "tax", toSet(new String[]{"ted", "tex", "red", "tax", "tad", "den", "rex", "pee"})));
    print(s.findLadders("hot", "dog", toSet(new String[]{"hot", "dog"})));
    print(s.findLadders("hit", "cog", toSet(new String[]{"hot", "dot", "dog", "lot", "log"})));
  }

  private static HashSet<String> toSet(String[] list) {
    HashSet<String> words = new HashSet<String>(list.length * 2);
    for (String word : list) {
      words.add(word);
    }
    return words;
  }

  private static void print(ArrayList<ArrayList<String>> list) {
    for (List<String> words : list) {
      for (String word : words) {
        System.out.printf("%s ", word);
      }
      System.out.println();
    }
  }

  public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
    Map<String, List<String>> nextMap = new HashMap<String, List<String>>(dict.size() * 2);
    ArrayList<ArrayList<String>> transformations = new ArrayList<ArrayList<String>>();
    dict.add(end);

    for (String word : dict) {
      nextMap.put(word, getNextList(word, dict));
    }
    nextMap.put(start, getNextList(start, dict));

    LinkedList<ListNode> queue = new LinkedList<ListNode>();
    queue.add(new ListNode(start, null));
    boolean isEnd = false;
    Set<String> seen = new HashSet<String>();

    while (!isEnd && !queue.isEmpty()) {
      List<String> words = new ArrayList<String>();
      for (int i = queue.size(); i > 0; i--) {
        ListNode node = queue.removeFirst();
        if (seen.contains(node.word)) continue;
        List<String> nextList = nextMap.get(node.word);
        if (nextList == null) continue;

        for (String next : nextList) {
          if (end.equals(next)) {
            isEnd = true;
          }

          queue.addLast(new ListNode(next, node));
        }

        words.add(node.word);
      }
      seen.addAll(words);
    }

    for (ListNode list : queue) {
      if (list.word.equals(end)) {
        transformations.add(getTransformation(list));
      }
    }

    return transformations;
  }

  private ArrayList<String> getTransformation(ListNode list) {
    ArrayList<String> transformation = new ArrayList<String>();
    for (ListNode node = list; node != null; node = node.previous) {
      transformation.add(node.word);
    }
    Collections.reverse(transformation);
    return transformation;
  }

  private List<String> getNextList(String word, Set<String> dict) {
    List<String> nextList = new ArrayList<String>();
    for (int i = word.length() - 1; i >= 0; i--) {
      for (char c = 'a'; c <= 'z'; c++) {
        if (c != word.charAt(i)) {
          char[] nextChars = word.toCharArray();
          nextChars[i] = c;
          String nextWord = new String(nextChars);
          if (dict.contains(nextWord)) {
            nextList.add(nextWord);
          }
        }
      }
    }

    return nextList;
  }

  class ListNode {
    String word;
    ListNode previous;

    ListNode(String word, ListNode previous) {
      this.word = word;
      this.previous = previous;
    }
  }
}
