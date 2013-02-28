/**
 * @author ShengMin Zhang
 * @problem Even Tree
 */
 
import java.util.{List => _, LinkedHashSet => _, Set => _, _}
import java.io._
import scala.collection.mutable._

class Edge(left: Node, right: Node) {
  def remove(): Unit = {
    left.neighbors -= right
    right.neighbors -= left
  }
  
  def canBeRemoved(): Boolean = {
    (left.countNodes(right) % 2 == 0) && (right.countNodes(left) % 2 == 0)
  }
}

class Node(val id: Int) {
  val neighbors: Set[Node] = new LinkedHashSet

  def countNodes(): Int = countNodes(null)
  
  def print(): Unit = {
    printf("node %d: ", id)
    for(neighbor <- neighbors) {
      printf("%d ", neighbor.id)
    }
    println
  }
  
  def countNodes(parent: Node): Int = {
    var count = 1
    for(neighbor <- neighbors) {
      if(neighbor != parent) count += neighbor.countNodes(this)
    }
    count
  }
  
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
      val nodeAId = Integer.parseInt(st.nextToken())
      val nodeBId = Integer.parseInt(st.nextToken())
      val nodeA = nodes(nodeAId)
      val nodeB = nodes(nodeBId)
      
      nodeA.neighbors += nodeB
      nodeB.neighbors += nodeA
      edges(i) = new Edge(nodeA, nodeB)
    }
    
    var removedEdgeCount = 0
    for(edge <- edges) {
      if(edge.canBeRemoved) {
        removedEdgeCount += 1
        edge.remove
      }
    }
    
    println(removedEdgeCount)
  }
}

object Solution {
  def main(args: Array[String]) = new Solver().solve()
}