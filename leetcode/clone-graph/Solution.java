import java.util.*;

public class Solution {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    return cloneGraph(node, new HashMap<UndirectedGraphNode, UndirectedGraphNode>());
  }

  UndirectedGraphNode cloneGraph(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
    if (node == null) return null;
    UndirectedGraphNode newNode = map.get(node);
    if (newNode != null) return newNode;

    newNode = new UndirectedGraphNode(node.label);
    map.put(node, newNode);
    for (UndirectedGraphNode neighbor: node.neighbors) {
      newNode.neighbors.add(cloneGraph(neighbor, map));
    }

    return newNode;
  }
}

class UndirectedGraphNode {
  int label;
  ArrayList<UndirectedGraphNode> neighbors = new ArrayList<UndirectedGraphNode>();
  UndirectedGraphNode(int x) {
    this.label = x;
  }
}
