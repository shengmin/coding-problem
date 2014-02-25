import java.io._

object Io {

  def useReaderWriter[R](arguments: Array[String])(block: (BufferedReader, PrintWriter) => R) = {
    use(
      new BufferedReader(
        if (arguments.length == 0) new InputStreamReader(System.in)
        else new FileReader(arguments(0))),
      new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))))(block)
  }

  def use[A <: Closeable, B <: Closeable, R](resourceA: A, resourceB: B)(block: (A, B) => R): R = {
    try {
      block(resourceA, resourceB)
    } finally {
      if (resourceA != null) resourceA.close()
      if (resourceB != null) resourceB.close()
    }
  }
}

object Solution {
  private final val Mod = 1000000007

  private [this] def run(reader: BufferedReader, writer: PrintWriter) {
    val t = reader.readLine().toInt
    for (i <- 0 until t) {
      val Array(m, n) = reader.readLine().split(' ').map(_.toInt)
      val ys = reader.readLine().split(' ').map(_.toLong).sortWith((a, b) => a > b)
      val xs = reader.readLine().split(' ').map(_.toLong).sortWith((a, b) => a > b)

      var xi = 0
      var yi = 0
      var cost: Long = 0L

      while (yi < ys.length || xi < xs.length) {
        if (yi == ys.length) {
          cost += (xs(xi) * (yi + 1)) % Mod
          xi += 1
        } else if (xi == xs.length) {
          cost += (ys(yi) * (xi + 1)) % Mod
          yi += 1
        } else {
          val xCost = xs(xi) * (yi + 1) + ys(yi) * (xi + 2)
          val yCost = ys(yi) * (xi + 1) + xs(xi) * (yi + 2)

          if (xCost < yCost) {
            cost += (xs(xi) * (yi + 1)) % Mod
            xi += 1
          } else {
            cost += (ys(yi) * (xi + 1)) % Mod
            yi += 1
          }
        }
      }

      writer.println(cost % Mod)
    }
  }

  def main(arguments: Array[String]) {
    Io.useReaderWriter(arguments)(run)
  }
}
