import java.io.{InputStreamReader, FileReader, BufferedReader}
import scala.math.{max, min}

/**
 * @author shengmin
 */
object Solution {
  private def solve(reader: BufferedReader): Boolean = {
    var maxX = Int.MinValue
    var maxY = Int.MinValue
    var minX = Int.MaxValue
    var minY = Int.MaxValue

    val n = reader.readLine().toInt
    val grid = new Array[String](n)

    for (y <- 0 until n) {
      grid(y) = reader.readLine()
    }

    for (y <- 0 until n) {
      for (x <- 0 until n) {
        val cell = grid(y)(x)
        if (cell == '#') {
          maxX = max(maxX, x)
          maxY = max(maxY, y)
          minX = min(minX, x)
          minY = min(minY, y)
        }
      }
    }

    if ((maxX - minX) != (maxY - minY)) {
      return false
    }

    for (y <- minY to maxY) {
      for (x <- minX to maxX) {
        if (grid(y)(x) != '#') {
          return false
        }
      }
    }

    return true
  }

  def main(arguments: Array[String]) {
    val reader = new BufferedReader(
      if (arguments.length > 0) new FileReader(arguments(0))
      else new InputStreamReader(System.in))

    val t = reader.readLine().toInt
    for (i <- 1 to t) {
      printf("Case #%d: %s\n", i, if (solve(reader)) "YES" else "NO")
    }
  }
}
