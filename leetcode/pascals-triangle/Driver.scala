import scala.collection.JavaConversions._
import java.util

object Driver {
  def main(a: Array[String]) {
    val s = new Solution()

    for (i <- 1 to 5) {
      printAnswer(s.generate(i))
    }
  }

  def printAnswer(list: util.ArrayList[util.ArrayList[Integer]]) {
    for (row <- list) {
      for (i <- row) {
        print(i)
        print(' ')
      }
      println()
    }
    println()
  }
}
