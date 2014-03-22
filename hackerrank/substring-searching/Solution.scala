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
  private[this] def isMatch(text: String, pattern: String): Boolean = {
    val failure = new Array[Int](pattern.length)
    failure(0) = -1
    for (i <- 2 to pattern.length) {
      var j = failure(i - 1)
      var toBreak = false
      while (toBreak) {
        if (pattern(j) == pattern(i - 1)) {
          failure(i) = j + 1
          toBreak = true
        } else if (j == 0) {
          failure(i) = 0
          toBreak = true
        } else {
          j = failure(j)
        }
      }
    }

    var i = 0
    var j = 0

    while (j < text.length) {
      if (text(j) == pattern(i)) {
        i += 1
        j += 1
        if (i == pattern.length)
          return true
      } else if (i > 0) {
        i = failure(i)
      } else {
        j += 1
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
