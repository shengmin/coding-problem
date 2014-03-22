import java.io._

object Io {

  def useReaderWriter[R](arguments: Array[String])(block: (BufferedReader, PrintWriter) => R) = {
    use(
      new BufferedReader(
        if (arguments.length == 0) new InputStreamReader(System.in)
        else new FileReader(arguments(0))
      ),
      new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))
    )(block)
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
  private[this] def buildFailureArray(pattern: String) = {
    val failure = new Array[Int](pattern.length)
    failure(0) = -1
    var i = 2
    var j = 0
    while (i < pattern.length) {
      if (pattern(i - 1) == pattern(j)) {
        j += 1
        failure(i) = j
        i += 1
      } else if (j > 0) {
        j = failure(j)
      } else {
        failure(i) = 0
        i += 1
      }
    }
    failure
  }

  private[this] def isMatch(text: String, pattern: String): Boolean = {
    val failure = buildFailureArray(pattern)
    var i = 0
    var j = 0

    while (j + i < text.length) {
      if (pattern(i) == text(i + j)) {
        if (i == pattern.length - 1)
          return true
        i += 1
      } else {
        j = j + i - failure(i)
        if (failure(i) > -1)
          i = failure(i)
        else
          i = 0
      }
    }

    false
  }

  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val t = reader.readLine().toInt

    for (i <- 0 until t) {
      val text = reader.readLine()
      val pattern = reader.readLine()

      if (isMatch(text, pattern))
        writer.println("YES")
      else
        writer.println("NO")
    }
  }

  def main(arguments: Array[String]) = Io.useReaderWriter(arguments)(run)
}
