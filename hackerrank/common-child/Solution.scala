import scala.math.max

object Solution {
  def main(arguments: Array[String]) {
    val word1 = readLine()
    val word2 = readLine()
    
    val table = Array.ofDim[Int](word1.length + 1, word2.length + 1)

    for (i <- 1 to word1.length) {
      for (j <- 1 to word2.length) {
        if (word1(i - 1) == word2(j - 1)) {
          table(i)(j) = max(max(table(i)(j - 1), table(i - 1)(j)), table(i - 1)(j - 1) + 1)
        } else {
          table(i)(j) = max(max(table(i)(j - 1), table(i - 1)(j)), table(i - 1)(j - 1))
        }
      }
    }
    
    println(table(word1.length)(word2.length))
  }
}
