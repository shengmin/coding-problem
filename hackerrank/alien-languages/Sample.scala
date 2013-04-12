import java.io.{FileReader, Reader, BufferedReader, InputStreamReader}
import java.util.StringTokenizer
import scala.collection.mutable
import scala.math.max
import scala.util.control.Breaks._

object Sample {
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

        val key = (index, startNum)
        table.get(key) match {
          case Some(ans) => return ans
          case None => {
            var count = 0
            if (index == M) {
              count = N - max(smallestEnd, startNum) + 1
            } else {
              var num = startNum
              while (num * 2 <= N) {
                count = (count + solve(index + 1, num * 2)) % mod
                num += 1
              }
              count = (count + (N - num + 1) * solve(index + 1, 1)) % mod
            }

            table(key) = count
            return count
          }
        }
      }

      println(solve(1, 1))
//      for ((key, value) <- table) {
//        print(key + ": " + value)
//        println()
//      }
    }


  }

  def main(arguments: Array[String]) =
    solve(if (arguments.length == 0) new InputStreamReader(System.in) else new FileReader(arguments(0)))
}
