object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    println(s.firstMissingPositive(Array(1, 1)))
    println(s.firstMissingPositive(Array(1, 2, 0)))
    println(s.firstMissingPositive(Array(-1, 2, 0, 3)))
    println(s.firstMissingPositive(Array(3, 4, -1, 1)))
    println(s.firstMissingPositive(Array(1000, -1)))
    println(s.firstMissingPositive(Array(1000, 1)))
  }
}
