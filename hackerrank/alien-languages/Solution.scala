import java.io.{FileReader, Reader, BufferedReader, InputStreamReader}
import java.util.StringTokenizer
import scala.collection.mutable
import scala.math.min
import scala.util.control.Breaks._

object Solution {
  def solve(_rd: Reader) {
    val mod = 100000007
    // log(5*10^5)
    val maxBlockSize = 22
    val rd = new BufferedReader(_rd)
    val T = rd.readLine().toInt

    for (_ <- 0 until T) {
      val st = new StringTokenizer(rd.readLine())
      val N = st.nextToken().toInt
      val M = st.nextToken().toInt
      val bigStart = N / 2 + 1
      val bigSize = N - bigStart + 1

      // smallAccTable(i)(j) = # of ways we can place i small chars and end with any char up to and including char j
      val smallAccTable = Array.ofDim[Int](maxBlockSize, bigStart);
      // there is one way to place any number of chars of size 0
      for (i <- 0 until bigStart) {
        smallAccTable(0)(i) = 1
      }

      for (i <- 1 until maxBlockSize) {
        for (j <- 1 until bigStart) {
          smallAccTable(i)(j) = (smallAccTable(i)(j - 1) + smallAccTable(i - 1)(j / 2)) % mod
        }
      }

      // blockTable(i) = # of ways we can place i - 1 small chars followed by exactly one big char
      val blockTable = new Array[Int](maxBlockSize);
      // a block of only one big char
      blockTable(1) = bigSize;
      for (i <- 2 until maxBlockSize) {
        var count = 0
        for (j <- bigStart to N) {
          count = (count + smallAccTable(i - 1)(j / 2)) % mod
        }
        blockTable(i) = count
      }

      // table(i) = # of way we can place 0 or more blocks
      val table = new Array[Long](M + 1)
      table(0) = 1
      for (i <- 1 to M) {
        var count = 0L
        for (blockSize <- 1 to min(i, maxBlockSize - 1)) {
          val product = (blockTable(blockSize).toLong * table(i - blockSize)) % mod
          count = (count + product) % mod
        }
        table(i) = count
      }

      println(table(M))
    }


  }

  def main(arguments: Array[String]) =
    solve(if (arguments.length == 0) new InputStreamReader(System.in) else new FileReader(arguments(0)))
}

