/**
 * @author ShengMin Zhang
 * @revision 1.0
 * - Store the number of nodes in each subtree
 * - Recursion with memorization to store whether a node can be decomposed into components containing even number of nodes
 */
 
import java.util.{List => _, _}
import java.io._
import scala.collection.immutable._

class Edge(val left: Node, val right: Node)

class Node(val id: Int) {
  var children: List[Node] = Nil
  /** Number of nodes in the tree */
  var nodeCount = 1
  
  def countNodes(): Int = {
    for(child <- children) nodeCount += child.countNodes()
    return nodeCount
  }
  
  def printTree(): Unit = {
    System.err.println(toString())
    for(child <- children) child.printTree()
  }
  
  override def toString() = 
    "id: %d, nodeCount: %d".format(id, nodeCount)
}

class Solver {
  val rd = new BufferedReader(new InputStreamReader(System.in))
  var st = new StringTokenizer(rd.readLine())
  val N = Integer.parseInt(st.nextToken())
  val M = Integer.parseInt(st.nextToken())
  val nodes = new Array[Node](N + 1)
  val edges = new Array[Edge](M)
  
  def solve() = {
    for(i <- 1 to N) {
      // Initialize all nodes
      nodes(i) = new Node(i)
    }
  
    for(i <- 0 until M) {
      st = new StringTokenizer(rd.readLine())
      // Pick the smaller one as the parent so we can use 1 as the root
      val u = Integer.parseInt(st.nextToken())
      val v = Integer.parseInt(st.nextToken())
      var parentIndex = 0
      var childIndex = 0
      if(u < v) {
        parentIndex = u
        childIndex = v
      } else {
        parentIndex = v
        childIndex = u
      }
      
      val parent = nodes(parentIndex)
      parent.children = nodes(childIndex) :: parent.children
      edges(i) = new Edge(nodes(u), nodes(v))
    }
    
    nodes(1).countNodes()
    var removedEdgeCount = 0
    for(edge <- edges) {
      if(edge.left.nodeCount % 2 == 0 && edge.right.nodeCount % 2 == 0) {
        removedEdgeCount += 1
      }
    }
    
    println(removedEdgeCount)
  }
}

object Solution {
  def main(args: Array[String]) = new Solver().solve()
}