import java.io._
import scala.math.max

object Solution {
  def main(arguments: Array[String]) {
    val word1 = readLine()
    val word2 = readLine()
    
    val table = Array.ofDim[Int](word1.length + 1, word2.length + 1)
    
    for (i <- 0 until word1.length) {
      for (j <- 0 until word2.length) {
        var count = table(i)(j)
        if (word1(i) == word2(j)) count += 1
        table(i + 1)(j + 1) = max(count, max(table(i)(j + 1), table(i + 1)(j)))
      }
    }
    
    println(table(word1.length)(word2.length))
  }
}