import java.io.{FileReader, Reader, BufferedReader, InputStreamReader}
import java.util.StringTokenizer
import scala.collection.mutable
import scala.math.max

object Solution {
  def solve(_rd: Reader) {
    val mod = 100000007
    val rd = new BufferedReader(_rd)
    val T = rd.readLine().toInt

    for (i <- 0 until T) {
      val st = new StringTokenizer(rd.readLine())
      val N = st.nextToken().toInt
      val M = st.nextToken().toInt
      val table = mutable.HashMap.empty[(Int, Int), Int]
      val smallestEnd = N / 2 + 1

      def solve(index: Int, startNum: Int): Int = {
        if (index == M) {
          return N - max(smallestEnd, startNum) + 1
        }

        val key = (index, startNum)
        table.get(key) match {
          case Some(ans) => return ans
          case None => {
            var count = 0

            for (num <- startNum to N) {
              val twoTimes = num * 2
              val nextStartNum = if (twoTimes > N) 1 else twoTimes
              count = (count + solve(index + 1, nextStartNum)) % mod
            }

            table(key) = count
            return count
          }
        }
      }

      println(solve(1, 1))
    }
  }

  def main(arguments: Array[String]) =
    solve(if (arguments.length == 0) new InputStreamReader(System.in) else new FileReader(arguments(0)))
}
