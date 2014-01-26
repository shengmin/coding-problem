import java.io._
import java.util
import java.util.{StringTokenizer}
import scala.collection.mutable

object D {
  class Edge(val u: Int, val v: Int, val w: Int)

  def solve(c: Int, edges: util.HashMap[Int, util.List[Edge]]): Int = {
    val visited = new util.HashSet[Int](edges.size() * 2)

    def findMin(node: Int): Int = {
      val outEdges = edges.get(node)
      if (outEdges == null) return 0
      var cost = 0
      visited.add(node)

      for (i <- 0 until outEdges.size()) {
        val edge = outEdges.get(i)
        if (!visited.contains(edge.v)) {
          cost += math.min(edge.w, findMin(edge.v))
        }
      }

      return if (cost == 0) Int.MaxValue else cost
    }

    return findMin(c)
  }

  def main(arguments: Array[String]) {
    val rd = new BufferedReader(
      if (arguments.length == 0) new InputStreamReader(System.in)
      else new FileReader(arguments(0)))
    var line = rd.readLine()

    while (line != null) {
      var st = new StringTokenizer(line)
      val n = st.nextToken().toInt
      val c = st.nextToken().toInt
      val edges = new util.HashMap[Int, util.List[Edge]](n * 2)

      def addEdge(u: Int, v: Int, w: Int) {
        val edge = new Edge(u, v, w)
        var list = edges.get(u)
        if (list == null) {
          list = new util.ArrayList[Edge]()
          edges.put(u, list)
        }
        list.add(edge)
      }

      for (i <- 0 until n - 1) {
        st = new StringTokenizer(rd.readLine())
        val u = st.nextToken().toInt
        val v = st.nextToken().toInt
        val w = st.nextToken().toInt
        addEdge(u, v, w)
        addEdge(v, u, w)
      }

      println(solve(c, edges))
      line = rd.readLine()
    }

    rd.close()
  }
}
