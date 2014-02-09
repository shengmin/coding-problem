import java.io._
import java.util._
import scala.util._

object Solution {
  def main(arguments: Array[String]) {
    val reader = new BufferedReader(new InputStreamReader(System.in))
    var tokenizer = new StringTokenizer(reader.readLine())
    val n = tokenizer.nextToken().toInt
    var k = tokenizer.nextToken().toInt
    val prices = new Array[Int](n)
    tokenizer = new StringTokenizer(reader.readLine())

    for (i <- 0 until n) {
      prices(i) = tokenizer.nextToken().toInt
    }

    Sorting.quickSort(prices)
    var count = 0

    for (i <- 0 until n) {
      if (k >= prices(i)) {
        count += 1
        k -= prices(i)
      } else {
        println(count)
        return
      }
    }

  }
}
