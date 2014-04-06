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
  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val t = reader.readLine().toInt
    for (i <- 0 until t) {
      val line = reader.readLine()
      def swap(list: List[Char]) {
        list match {
          case Nil => writer.println()
          case (a :: b :: xs) =>
            writer.print(b)
            writer.print(a)
            swap(xs)
        }
      }
      swap(line.toList)
    }
  }

  def main(arguments: Array[String]) = Io.useReaderWriter(arguments)(run)
}
