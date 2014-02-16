package me.shengmin.graph

import org.scalatest._

class FlowNetworkSpec extends FlatSpec with Matchers{
  it should "have maximum network flow value of 3" in {
    val x = NodeReference("x")
    val y = NodeReference("y")
    val a = NodeReference("a")
    val b = NodeReference("b")
    val c = NodeReference("c")
    val d = NodeReference("d")
    val e = NodeReference("e")
    val network = new FlowNetwork(x, y)
      .addEdge(x, a, 3)
      .addEdge(x, b, 1)
      .addEdge(a, c, 3)
      .addEdge(b, c, 5)
      .addEdge(b, d, 4)
      .addEdge(c, y, 2)
      .addEdge(d, e, 2)
      .addEdge(e, y, 3)
    network.findMaximumFlow() should be (3)
    val nodes = network.nodes
    nodes.size should be (7)
    nodes(x).edges(nodes(a)).capacity should be (1)
    nodes(x).edges(nodes(b)).capacity should be (0)
    nodes(a).edges(nodes(c)).capacity should be (1)
    nodes(b).edges(nodes(c)).capacity should be (5)
    nodes(b).edges(nodes(d)).capacity should be (3)
    nodes(c).edges(nodes(y)).capacity should be (0)
    nodes(d).edges(nodes(e)).capacity should be (1)
    nodes(e).edges(nodes(y)).capacity should be (2)
  }
}
