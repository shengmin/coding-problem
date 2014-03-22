import java.io._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

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

  private[this] object IntMinOrdering extends Ordering[Int] {
    override def compare(x: Int, y: Int) = {
      if (x < y)
        -1
      else if (x > y)
        1
      else
        0
    }
  }

  private[this] object ListMinOrdering extends Ordering[List[Int]] {
    override def compare(xs: List[Int], ys: List[Int]): Int = {
      (xs, ys) match {
        case (x :: xs, y :: ys) =>
          val result = IntMinOrdering.compare(x, y)
          if (result != 0)
            result
          else
            compare(xs, ys)
        case (Nil, Nil) => 0
        case (xs, Nil) => 1
        case (Nil, ys) => -1
      }
    }
  }

  private[this] object IntMaxOrdering extends Ordering[Int] {
    override def compare(x: Int, y: Int) = {
      -IntMinOrdering.compare(x, y)
    }
  }


  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val Array(n, k) = reader.readLine().split(' ').map(_.toInt)
    val ks = reader.readLine().split(' ').map(_.toInt).sorted(IntMaxOrdering)
    val visited = mutable.HashSet(n)
    val queue = mutable.Queue(List(n))

    while (!queue.isEmpty) {
      val nextList = ArrayBuffer.empty[List[Int]]

      while (!queue.isEmpty) {
        val state = queue.dequeue()
        if (state.head == 1) {
          writer.println(state.mkString(" "))
          return
        }

        for (k <- ks) {
          if (state.head % k == 0) {
            val quotient = state.head / k
            if (!visited.contains(quotient)) {
              visited += quotient
              val nextState = quotient :: state
              nextList += nextState
            }
          }
        }

      }

      for (next <- nextList.sorted(ListMinOrdering)) {
        queue.enqueue(next)
      }
    }

    writer.println(-1)
  }

  def main(arguments: Array[String]) = Io.useReaderWriter(arguments)(run)
}
