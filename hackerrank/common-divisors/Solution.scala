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

  def use[T <: Closeable, R](resource: T)(block: T => R): R = {
    try {
      block(resource)
    } finally {
      if (resource != null) resource.close()
    }
  }
}

object Solution {
  private[this] def findGreatestCommonDivisor(a: Int, b: Int): Int = {
    if (a < b)
      findGreatestCommonDivisor(b, a)
    else if (b == 0)
      a
    else
      findGreatestCommonDivisor(b, a % b)
  }

  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val t = reader.readLine().toInt

    for (i <- 0 until t) {
      val Array(l, m) = reader.readLine().split(' ').map(_.toInt)
      var count = 1
      val gcd = findGreatestCommonDivisor(l, m)
      if (gcd > 1)
        count += 1

      for (i <- 2 until gcd) {
        if (l % i == 0 && m % i == 0)
          count += 1
      }
      writer.println(count)
    }
  }

  def main(arguments: Array[String]) = Io.useReaderWriter(arguments)(run)
}
