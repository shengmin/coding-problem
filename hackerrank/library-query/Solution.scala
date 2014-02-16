import SegmentTree.{Segment, ReadOnlySegment}
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

class SegmentTree[T](size: Int)(initializer: Int => T, aggregator: (ReadOnlySegment[T], ReadOnlySegment[T]) => T) {

  private val segments = {
    val height = math.ceil(math.log(size) / math.log(2)).toInt
    val capacity = 2 * math.pow(2, height).toInt
    new Array[Segment[T]](capacity)
  }

  initialize(0, 0, size)

  private[this] def leftChildIndex(index: Int) = 2 * index + 1

  private[this] def rightChildIndex(index: Int) = 2 * index + 2

  private[this] def initialize(index: Int, start: Int, end: Int) {
    if (start >= end) return
    if (end - start == 1) {
      val value = initializer(start)
      segments(index) = Segment(start, end, value)
    } else {
      val leftIndex = leftChildIndex(index)
      val rightIndex = rightChildIndex(index)
      val midIndex = start + (end - start) / 2
      initialize(leftIndex, start, midIndex)
      initialize(rightIndex, midIndex, end)
      val value = aggregator(segments(leftIndex), segments(rightIndex))
      segments(index) = Segment(start, end, value)
    }
  }

  def update(itemIndex: Int)
        (base: ReadOnlySegment[T] => T, aggregator: (ReadOnlySegment[T], ReadOnlySegment[T], ReadOnlySegment[T]) => T): this.type = {
    update(0, itemIndex, base, aggregator)
    return this
  }

  private[this] def update(
    index: Int,
    itemIndex: Int,
    base: ReadOnlySegment[T] => T,
    aggregator: (ReadOnlySegment[T], ReadOnlySegment[T], ReadOnlySegment[T]) => T
  ) {
    val segment = segments(index)
    if (segment == null) return
    if (segment.start == itemIndex && (segment.end - segment.start) == 1) {
      segment.value = base(segment)
      return
    }

    val leftIndex = leftChildIndex(index)
    val rightIndex = rightChildIndex(index)
    val left = segments(leftIndex)
    val right = segments(rightIndex)

    if (itemIndex < right.start) {
      update(leftIndex, itemIndex, base, aggregator)
    } else {
      update(rightIndex, itemIndex, base, aggregator)
    }

    segment.value = aggregator(segment, left, right)
  }

  def query[R](start: Int, end: Int, defaultValue: => R)(base: ReadOnlySegment[T] => R, aggregator: (R, R) => R): R = {
    query(0, start, end, defaultValue, base, aggregator)
  }

  private[this] def query[R](
    index: Int,
    start: Int,
    end: Int,
    defaultValue: R,
    base: Segment[T] => R,
    aggregator: (R, R) => R
  ): R = {
    if (start >= end) return defaultValue
    val segment = segments(index)
    if (segment == null) return defaultValue
    if (segment.start == start && segment.end == end) {
      return base(segment)
    }

    val leftIndex = leftChildIndex(index)
    val rightIndex = rightChildIndex(index)
    val left = segments(leftIndex)
    val right = segments(rightIndex)

    if (end < right.start) {
      return query(leftIndex, start, end, defaultValue, base, aggregator)
    } else if (start > left.end) {
      return query(rightIndex, start, end, defaultValue, base, aggregator)
    } else {
      val leftResult = query(leftIndex, start, left.end, defaultValue, base, aggregator)
      val rightResult = query(rightIndex, right.start, end, defaultValue, base, aggregator)
      return aggregator(leftResult, rightResult)
    }
  }
}

object SegmentTree {

  trait ReadOnlySegment[+T] {
    def start: Int

    def end: Int

    def value: T
  }

  private case class Segment[T](val start: Int, val end: Int, var value: T) extends ReadOnlySegment[T]

}

object Solution {
  private[this] final val MaxCount = 1000

  private[this] class Data {
    val table = new Array[Int](MaxCount + 1)
  }

  private[this] def findValue(k: Int, table: Array[Int]): Int = {
    var count = 0
    for (i <- 0 to MaxCount) {
      if (count >= k) return i - 1
      count += table(i)
    }
    MaxCount
  }

  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val t = reader.readLine().toInt
    for (i <- 0 until t) {
      val n = reader.readLine().toInt
      val shelves = reader.readLine().split(' ').map(_.toInt)
      val tree = new SegmentTree[Data](n)(
        index => {
          val data = new Data
          data.table(shelves(index)) += 1
          data
        },
        (left, right) => {
          val data = new Data
          for (i <- 0 to MaxCount) {
            data.table(i) = left.value.table(i) + right.value.table(i)
          }
          data
        }
      )
      val q = reader.readLine().toInt
      for (j <- 0 until q) {
        val line = reader.readLine()
        if (line(0) == '0') {
          val Array(_, x, y, k) = line.split(' ').map(_.toInt)
          val table = new Array[Int](MaxCount + 1)
          tree.query(x - 1, y, table)(
            segment => {
              for (i <- 0 to MaxCount) {
                table(i) += segment.value.table(i)
              }
              table
            },
            (_, _) => table
          )
          writer.println(findValue(k, table))
        } else {
          val Array(_, x, k) = line.split(' ').map(_.toInt)
          tree.update(x - 1)(
            segment => {
              val data = segment.value
              data.table(shelves(x - 1)) -= 1
              data.table(k) += 1
              data
            },
            (segment, left, right) => {
              val data = segment.value
              val leftData = left.value
              val rightData = right.value
              for (i <- Seq(shelves(x - 1), k)) {
                data.table(i) = leftData.table(i) + rightData.table(i)
              }
              data
            }
          )
          shelves(x - 1) = k
        }
      }
    }
  }

  def main(arguments: Array[String]) {
    Io.useReaderWriter(arguments)(run)
  }
}
