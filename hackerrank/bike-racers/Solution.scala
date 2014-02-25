import java.io._
import scala.collection.mutable
import scala.Some

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

case class NodeReference private (val id: Int, val name: String) {
  override def hashCode() = id
}

object NodeReference {
  private [this] var nextId = 0

  def apply(name: String = "") = {
    val reference = new NodeReference(nextId, name)
    nextId += 1
    reference
  }
}

class FlowNetwork(
  sourceReference: NodeReference,
  sinkReference: NodeReference
) {

  val nodes = mutable.HashMap.empty[NodeReference, Node]
  val source = nodes.getOrElseUpdate(sourceReference, Node(sourceReference))
  val sink = nodes.getOrElseUpdate(sinkReference, Node(sinkReference))

  case class OutgoingEdge(
    to: Node,
    var capacity: Int = 0
  )

  case class Node(id: NodeReference) {
    val edges = mutable.LinkedHashMap.empty[Node, OutgoingEdge]
  }

  def addEdge(from: NodeReference, to: NodeReference, weight: Int): this.type = {
    val fromNode = nodes.getOrElseUpdate(from, Node(from))
    val toNode = nodes.getOrElseUpdate(to, Node(to))

    fromNode.edges.put(toNode, OutgoingEdge(toNode, weight))
    toNode.edges.put(fromNode, OutgoingEdge(fromNode))

    return this
  }

  private[this] def findMinimumCapacity(): Int = {
    val parents = mutable.HashMap.empty[Node, Node]

    def findPath() {
      val queue = mutable.Queue.empty[Node]
      val visited = mutable.HashSet.empty[Node]
      queue.enqueue(source)
      visited += source

      while (queue.nonEmpty) {
        val node = queue.dequeue()
        for ((_, edge) <- node.edges) {
          if (edge.capacity > 0 && !visited.contains(edge.to)) {
            queue.enqueue(edge.to)
            visited += edge.to
            parents.put(edge.to, node)
            if (edge.to == sink) return
          }
        }
      }
    }

    findPath()

    def findMinimumCapacity(): Int = {
      var minimumCapacity = Int.MaxValue
      var node = sink
      while (true) {
        parents.get(node) match {
          case None => return minimumCapacity
          case Some(parent) => {
            minimumCapacity = math.min(minimumCapacity, parent.edges(node).capacity)
            node = parent
          }
        }
      }
      minimumCapacity
    }

    val minimumCapacity = findMinimumCapacity()

    def updateCapacity() {
      var node = sink
      while (true) {
        parents.get(node) match {
          case None => return
          case Some(parent) => {
            parent.edges(node).capacity -= minimumCapacity
            node.edges(parent).capacity += minimumCapacity
            node = parent
          }
        }
      }
    }

    updateCapacity()
    return if (minimumCapacity == Int.MaxValue) 0 else minimumCapacity
  }

  def findMaximumFlow(): Int = {
    var maxFlow = 0

    while (true) {
      val flow = findMinimumCapacity()
      if (flow == 0) return maxFlow
      maxFlow += flow
    }

    maxFlow
  }
}

object Solution {

  private[this] case class Entity(id: NodeReference, x: Int, y: Int)

  private[this] case class Pair(bike: Entity, biker: Entity) {
    lazy val distanceSquared = {
      val dx = bike.x.toLong - biker.x
      val dy = bike.y.toLong - biker.y
      dx * dx + dy * dy
    }
  }

  private[this] def readArray(size: Int, reader: BufferedReader) = {
    val list = new Array[Entity](size)
    for (i <- 0 until size) {
      val Array(x, y) = reader.readLine().split(' ').map(_.toInt)
      list(i) = Entity(NodeReference(), x, y)
    }
    list
  }

  private[this] def run(reader: BufferedReader, writer: PrintWriter) {
    val Array(n, m, k) = reader.readLine().split(' ').map(_.toInt)
    val bikers = readArray(n, reader)
    val bikes = readArray(m, reader)
    val pairs = mutable.ArrayBuffer.empty[Pair]
    pairs.sizeHint(n * m)

    for (biker <- bikers) {
      for (bike <- bikes) {
        pairs += Pair(bike, biker)
      }
    }

    val sortedPairs = pairs.sortWith((x, y) => x.distanceSquared < y.distanceSquared)
    val source = NodeReference()
    val sink = NodeReference()

    def isValid(last: Int): Boolean = {
      val network = new FlowNetwork(source, sink)
      for (i <- 0 to last) {
        val pair = sortedPairs(i)
        network
            .addEdge(pair.biker.id, pair.bike.id, 1)
            .addEdge(source, pair.biker.id, 1)
            .addEdge(pair.bike.id, sink, 1)
      }
      network.findMaximumFlow() >= k
    }

    // Binary search
    var start = 0
    var end = sortedPairs.size
    var minimum = Long.MaxValue

    while (start < end) {
      val midIndex = start + (end - start) / 2
      if (isValid(midIndex)) {
        minimum = math.min(minimum, sortedPairs(midIndex).distanceSquared)
        end = midIndex
      } else {
        start = midIndex + 1
      }
    }

    writer.println(minimum)
  }

  def main(arguments: Array[String]) {
    Io.useReaderWriter(arguments)(run)
  }
}
