import java.io.{FileReader, Reader, BufferedReader, InputStreamReader}
import java.util.StringTokenizer
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
      println(solve(Array.ofDim[Int](N + 1, M + 1), N, M, 1, 1))
    }

    def solve(table: Array[Array[Int]], N: Int, M: Int, index: Int, startNum: Int): Int = {
      if (index == M) {
        return N - max(N / 2 + 1, startNum) + 1
      }

      var count = table(startNum)(index)
      if (count > 0) {
        return count
      }

      var num = startNum

      while (num <= N) {
        val twoTimes = num * 2
        val nextStartNum = if (twoTimes > N) 1 else twoTimes
        count = (count + solve(table, N, M, index + 1, nextStartNum)) % mod
        num += 1
      }

      table(startNum)(index) = count
      return count
    }
  }

  def main(arguments: Array[String]) =
    solve(if (arguments.length == 0) new InputStreamReader(System.in) else new FileReader(arguments(0)))
}
