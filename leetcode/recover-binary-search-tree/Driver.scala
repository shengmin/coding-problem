import Extensions._

object Driver {
  def main(a: Array[String]) {
    val s = new Solution()

    def test2() {
      val n1 = new TreeNode(1)
      val n2 = new TreeNode(2)

      n1.left = n2
      s.recoverTree(n1)
      n1.printTree(0)
    }

    def test3() {
      val n1 = new TreeNode(1)
      val n2 = new TreeNode(2)
      val n3 = new TreeNode(3)

      n1.right = n2
      n2.left = n3

      s.recoverTree(n1)
      n1.printTree(0)
    }

    test3()
    test2()
  }
}
