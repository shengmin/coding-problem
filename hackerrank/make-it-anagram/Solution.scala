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
  private final val AlphabetSize = 26

  private [this] def run(reader: BufferedReader, writer: PrintWriter) {
    val lineA = reader.readLine()
    val lineB = reader.readLine()
    val countA = new Array[Int](AlphabetSize)
    val countB = new Array[Int](AlphabetSize)

    for (c <- lineA) {
      countA(c - 'a') += 1
    }

    for (c <- lineB) {
      countB(c - 'a') += 1
    }

    var count = 0

    for (i <- 0 until AlphabetSize) {
      count += math.abs(countA(i) - countB(i))
    }

    writer.println(count)
  }
  def main(arguments: Array[String]) {
    Io.useReaderWriter(arguments)(run)
  }
}
