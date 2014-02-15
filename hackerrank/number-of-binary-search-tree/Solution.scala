import java.io._
import scala.collection.mutable

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

  import Io._

  private final val Mod = 100000007

  private [this] def run(reader: BufferedReader, writer: PrintWriter) {
    val t = reader.readLine().toInt
    // table(n) => number of binary trees with n nodes
    val table = mutable.HashMap.empty[Int, Long]

    def find(lastNode: Int): Long = {
      if (lastNode == 0) return 1
      if (lastNode == 1) return 1
      var count = table.getOrElse(lastNode, -1L)
      if (count != -1) return count
      count = 0
      for (root <- 1 to lastNode) {
        val leftCount: Long = find(root - 1)
        val rightCount: Long = find(lastNode - root)
        val product = (leftCount * rightCount) % Mod
        count += product.toInt
      }

      count %= Mod
      table.put(lastNode, count)
      count
    }

    for (i <- 0 until t) {
      writer.println(find(reader.readLine().toInt))
    }
  }

  def main(arguments: Array[String]) {
    useReaderWriter(arguments)(run)
  }
}
