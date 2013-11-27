import java.io._
import scala.math.abs

object Solution {
  def solve(line: String): Int = {
    if ((line.length & 1) == 1) return -1

    val firstEnd = line.length / 2
    var count = 0
    val firstCount = new Array[Int](26)
    val secondCount = new Array[Int](26)

    for (i <- 0 until firstEnd) {
      firstCount(line(i) - 'a') += 1
    }

    for (i <- firstEnd until line.length) {
      secondCount(line(i) - 'a') += 1
    }

    for (i <- 0 until 26) {
      count += abs(firstCount(i) - secondCount(i))
    }

    return count / 2
  }

  def main(arguments: Array[String]) {
    val reader = new BufferedReader(new InputStreamReader(System.in))
    val writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))
    val t = reader.readLine().toInt

    for (i <- 0 until t) writer.println(solve(reader.readLine()))

    reader.close()
    writer.flush()
    writer.close()
  }
}
