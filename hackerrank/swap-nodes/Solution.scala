import java.io._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


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

class BinaryTreeNode[@specialized(Int) T](value: T) {
  var left: BinaryTreeNode[T] = _
  var right: BinaryTreeNode[T] = _

  def inOrderTraversal(): IndexedSeq[BinaryTreeNode[T]] = BinaryTreeNode.inOrderTraversal(this)

  override def toString = value.toString
}

object BinaryTreeNode {
  type Node[T] = BinaryTreeNode[T]

  private[this] def inOrderTraversal[@specialized(Int) T](node: Node[T], list: ArrayBuffer[Node[T]]) {
    if (node != null) {
      inOrderTraversal(node.left, list)
      list += node
      inOrderTraversal(node.right, list)
    }
  }

  def inOrderTraversal[@specialized(Int) T](node: Node[T]): IndexedSeq[Node[T]] = {
    val list = ArrayBuffer.empty[Node[T]]
    inOrderTraversal(node, list)
    list
  }
}

object Solution {
  type Node = BinaryTreeNode[Int]

  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val n = reader.readLine().toInt
    val nodes = new Array[Node](n + 1)
    for (i <- 1 to n) {
      nodes(i) = new Node(i)
    }

    for (i <- 1 to n) {
      val Array(leftIndex, rightIndex) = reader.readLine().split(' ').map(_.toInt)
      val node = nodes(i)
      if (leftIndex != -1) {
        node.left = nodes(leftIndex)
      }

      if (rightIndex != -1) {
        node.right = nodes(rightIndex)
      }
    }

    val t = reader.readLine().toInt
    val ks = (1 to t).map(_ => reader.readLine().toInt)
    val root = nodes(1)

    def swap(k: Int) {
      val queue = mutable.Queue(root)
      var depth = 1

      while (!queue.isEmpty) {
        for (i <- 0 until queue.size) {
          val node = queue.dequeue()
          if (node != null) {
            val left = node.left
            val right = node.right
            if (depth % k == 0) {
              // Swap
              node.left = right
              node.right = left
            }
            queue.enqueue(left, right)
          }
        }
        depth += 1
      }
    }

    for (k <- ks) {
      swap(k)
      writer.println(root.inOrderTraversal().mkString(" "))
    }
  }

  def main(arguments: Array[String]) = Io.useReaderWriter(arguments)(run)
}
