package me.shengmin.graph

import scala.collection.mutable

class FlowNetwork(
  sourceReference: NodeReference,
  sinkReference: NodeReference
) {

  private[graph] val nodes = mutable.HashMap.empty[NodeReference, Node]
  private[graph] val source = nodes.getOrElseUpdate(sourceReference, Node(sourceReference))
  private[graph] val sink = nodes.getOrElseUpdate(sinkReference, Node(sinkReference))

  private[graph] case class OutgoingEdge(
    to: Node,
    var capacity: Int = 0
  )

  private[graph] case class Node(id: NodeReference) {
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

