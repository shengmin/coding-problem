import java.io._
import scala.collection.mutable
import scala.math

case class Node(
  val start: Int,
  val end: Int,
  val min: Int,
  val left: Node,
  val right: Node
) {
  def findMin(start: Int, end: Int): Int = {
    if (start == this.start && end == this.end) return min
    if (left == null && right == null) return min
    if (left == null) return right.findMin(start, end)
    if (right == null) return left.findMin(start, end)

    if (end < right.start) return left.findMin(start, end)
    if (start >= right.start) return right.findMin(start, end)
    return math.min(left.findMin(start, left.end), right.findMin(right.start, end))
  }
}

object Solution {
  def main(a: Array[String]) {
    val rd = new BufferedReader(
      if (a.length == 0) new InputStreamReader(System.in)
      else new FileReader(a(0)))

    val Array(n, m) = rd.readLine().split(' ').map(_.toInt)
    val ns = rd.readLine().split(' ').map(_.toInt)

    val queue = mutable.Queue.empty[Node]
    for (i <- 0 until n) {
      queue.enqueue(Node(i, i, ns(i), null, null))
    }

    while (queue.size > 1) {
      val size = queue.size
      var i = 0
      while (i < size) {
        val left = queue.dequeue()
        i += 1
        if (i < size) {
          val right = queue.dequeue()
          i += 1
          val newNode = Node(left.start, right.end, math.min(left.min, right.min), left, right)
          queue.enqueue(newNode)
        } else {
          queue.enqueue(left)
        }
      }
    }

    val node = queue.dequeue()
    for (i <- 0 until m) {
      val Array(start, end) = rd.readLine().split(' ').map(_.toInt)
      println(node.findMin(start, end))
    }
  }
}
