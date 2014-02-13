import java.util
import scala.collection.JavaConversions._

object Driver {
  def main(a: Array[String]) {
    val s = new Solution
    printList(s.combinationSum(Array(2, 3, 6, 7), 7))
  }

  def printList(lists: util.ArrayList[util.ArrayList[Integer]]) {
    for (list <- lists) {
      for (i <- list) {
        print(i)
        print(' ')
      }
      println()
    }
    println("-----------------------------------")
  }
}
