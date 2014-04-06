package me.shengmin.graph

import scala.collection.mutable.ArrayBuffer

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
