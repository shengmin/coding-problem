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
  private [this] def run(reader: BufferedReader, writer: PrintWriter) {
    val Array(n, k, q) = reader.readLine().split(' ').map(_.toInt)
    val list = reader.readLine().split(' ').map(_.toInt)
    val newList = new Array[Int](n)
    for (i <- 0 until n) {
      newList((i + k) % n) = list(i)
    }
    for (i <- 0 until q) {
      writer.println(newList(reader.readLine().toInt))
    }
  }

  def main(arguments: Array[String]) {
    Io.useReaderWriter(arguments)(run)
  }
}
