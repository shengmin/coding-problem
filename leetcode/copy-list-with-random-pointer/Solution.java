import java.util.*;

public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    return copy(head, new HashMap<Integer, RandomListNode>());
  }

  private RandomListNode copy(RandomListNode node, Map<Integer, RandomListNode> nodes) {
    if (node == null) return null;
    RandomListNode newNode = nodes.get(node.label);
    if (newNode != null) return newNode;
    newNode = new RandomListNode(node.label);
    nodes.put(node.label, newNode);
    newNode.next = copy(node.next, nodes);
    newNode.random = copy(node.random, nodes);
    return newNode;
  }
}
