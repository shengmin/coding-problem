import java.io._
import java.util._

object Solution {
  def main(arguments: Array[String]) {
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val t = reader.readLine().toInt
    val tokenizer = new StringTokenizer(reader.readLine())
    var n = 0

    for (i <- 0 until t) {
      n ^= tokenizer.nextToken().toInt
    }

    println(n)
  }
}
