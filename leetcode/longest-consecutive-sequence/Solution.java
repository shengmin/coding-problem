import java.util.*;

class Node {
  Node next;
  int count = 0;
  final int value;

  Node(int value) {
    this.value = value;
  }

  int countSequence() {
    if (count > 0) {
      return count;
    }

    if (next == null) {
      return count = 1;
    }

    return count = 1 + next.countSequence();
  }
}

public class Solution {
  public int longestConsecutive(int[] num) {
    Map<Integer, Node> map = new LinkedHashMap<Integer, Node>(num.length * 2);

    for (int n: num) {
      Node node = map.get(n);
      if (node == null) {
        node = new Node(n);
        map.put(n, node);
      }

      Node last = map.get(n - 1);
      Node next = map.get(n + 1);

      if (last != null) {
        last.next = node;
      }

      if (next != null) {
        node.next = next;
      }
    }

    int max = Integer.MIN_VALUE;

    for (Node node: map.values()) {
      max = Math.max(max, node.countSequence());
    }

    return max;
  }
}
