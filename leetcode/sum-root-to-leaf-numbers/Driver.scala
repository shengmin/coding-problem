object Driver {
  def main(a: Array[String]) {
    val s = new Solution
    val n1 = new TreeNode(1)
    val n2 = new TreeNode(2)
    val n3 = new TreeNode(3)
    n1.left = n2
    n1.right = n3

    println(s.sumNumbers(n1))
  }
}
