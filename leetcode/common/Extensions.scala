object Extensions {
  implicit class RichTreeNode(val node: TreeNode) extends AnyVal {
    def printTree(level: Int = 0) {
      if (node != null) {
        print((0 until level).map(_ => "  ").mkString(""))
        println(node.`val`)
        node.left.printTree(level + 1)
        node.right.printTree(level + 1)
      } else {
        println()
      }
    }
  }
}
