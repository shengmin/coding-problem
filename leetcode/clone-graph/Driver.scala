object Driver {
  def main(a: Array[String]) {
    val s = new Solution();
    val n1 = new UndirectedGraphNode(1);
    val n2 = new UndirectedGraphNode(2);
    val n3 = new UndirectedGraphNode(3);
    val n4 = new UndirectedGraphNode(4);

    n1.neighbors.add(n2);
    n1.neighbors.add(n3);
    n1.neighbors.add(n4);
    n4.neighbors.add(n1);
    s.cloneGraph(n1);
  }
}
