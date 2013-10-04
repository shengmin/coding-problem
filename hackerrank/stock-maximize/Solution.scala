import java.io.{FileReader, InputStreamReader, BufferedReader}
import java.util.StringTokenizer
import scala.math.max

object Solution {

  private def solveOne(prices: Array[Int]): Long = {

    val maxPrices = new Array[Int](prices.length + 1)
    maxPrices(prices.length) = Int.MinValue

    for (i <- prices.length - 1 to 0 by -1) {
      maxPrices(i) = max(prices(i), maxPrices(i + 1))
    }

    var profit = 0L
    var shares = 0

    for (i <- 0 until prices.length) {
      val price = prices(i)
      val maxPrice = maxPrices(i + 1)

      if (price > maxPrice) {
        // Sell all shares
        profit += shares * price.toLong
        shares = 0
      } else if (price < maxPrice) {
        // Buy one share
        shares += 1
        profit -= price
      }
    }

    return profit
  }

  def main(args: Array[String]) {
    val rd = new BufferedReader(
      if (args.length > 0) new FileReader(args(0))
      else new InputStreamReader(System.in))

    val T = rd.readLine().toInt
    for (_ <- 0 until T) {
      val N = rd.readLine().toInt
      val st = new StringTokenizer(rd.readLine())
      val prices = new Array[Int](N)

      for (i <- 0 until N) {
        prices(i) = st.nextToken().toInt
      }

      println(solveOne(prices))
    }

  }
}
