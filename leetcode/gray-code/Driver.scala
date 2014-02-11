import scala.collection.JavaConversions._
import java.util

object Driver {
  def main(a: Array[String]) {
    val s = new Solution();
    printList(s.grayCode(1))
    printList(s.grayCode(2))
    printList(s.grayCode(3))
  }

  def printList(list: util.ArrayList[Integer]) {
    for (i <- list) {
      print(Integer.toBinaryString(i))
      print(' ')
    }
    println()
  }
}
