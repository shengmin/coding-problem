import scala.collection.JavaConversions._
import java.util.ArrayList

object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    printList(s.fourSum(Array(1, 0, -1, 0, -2, 2), 0))
  }

  def printList(result: ArrayList[ArrayList[Integer]]) {
    for (list <- result) {
      for (i <- list) {
        print(i)
        print(' ')
      }

      println()
    }
  }
}
