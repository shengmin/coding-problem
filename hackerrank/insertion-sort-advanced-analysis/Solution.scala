import java.io._
import java.util.StringTokenizer

object Solution {
  class ListNode(val value: Int) {
    var next: ListNode = null
  }

  def solve(tokenizer: StringTokenizer, count: Int): Int = {
    var totalShiftCount = 0
    var list: ListNode = null

    for (i <- 0 until count) {
      val n = tokenizer.nextToken().toInt
      val (newList, shiftCount) = insert(list, n, i)
      totalShiftCount += shiftCount
      list = newList
    }

    return totalShiftCount
  }

  def insert(list: ListNode, n: Int, length: Int): (ListNode, Int) = {
    val newNode = new ListNode(n)

    if (list == null) {
      return (newNode, 0)
    }

    if (n < list.value) {
      newNode.next = list
      return (newNode, length)
    }

    var i = 0
    var node = list
    var lastNode: ListNode = null

    while (node != null) {
      if (n < node.value) {
        lastNode.next = newNode
        newNode.next = node
        return (list, length - i)
      }

      i += 1
      lastNode = node
      node = node.next
    }

    lastNode.next = newNode
    return (list, 0)
  }

  def main(arguments: Array[String]) {
    val reader = new BufferedReader(
      if (arguments.length > 0) new FileReader(arguments(0))
      else new InputStreamReader(System.in))
    val t = reader.readLine().toInt

    for (i <- 0 until t) {
      val count = reader.readLine().toInt
      println(solve(new StringTokenizer(reader.readLine()), count))
    }
  }
}
