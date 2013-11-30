import java.io._
import java.util.StringTokenizer

object Solution {

  class SortedArray(size: Int) {
    private val buffer = new Array[Int](size * 3 + 100)

    private var start: Int = buffer.length / 2
    private var end: Int = start

    def insert(n: Int): Int = {
      val insertPosition = findInsertPosition(n)
      val shiftCount = end - insertPosition

      if (insertPosition - start < shiftCount) {
        for (i <- start until insertPosition) {
          buffer(i - 1) = buffer(i)
        }
        buffer(insertPosition - 1) = n
        start -= 1
      } else {
        for (i <- end to insertPosition by -1) {
          buffer(1 + i) = buffer(i)
        }
        buffer(insertPosition) = n
        end += 1
      }

      return shiftCount
    }

    private def findInsertPosition(n: Int): Int = {
      var index = this.end
      var start = this.start
      var end = this.end

      while (start < end) {
        val midIndex = start + (end - start) / 2
        val mid = buffer(midIndex)

        if (n < mid) {
          index = midIndex
          end = midIndex
        } else {
          start = midIndex + 1
        }
      }


      return index
    }
  }

  def solve(tokenizer: StringTokenizer, count: Int): Long = {
    var totalShiftCount = 0L
    val buffer = new SortedArray(count)

    for (i <- 0 until count) {
      val n = tokenizer.nextToken().toInt
      totalShiftCount += buffer.insert(n)
    }

    return totalShiftCount
  }

  def main(arguments: Array[String]) {
    val reader = new BufferedReader(
      if (arguments.length > 0) new FileReader(arguments(0))
      else new InputStreamReader(System.in))
    val t = reader.readLine().toInt

    for (i <- 0 until t) {
      val count = reader.readLine().toInt
      println(solve(new StringTokenizer(reader.readLine()), count))
    }
  }
}
