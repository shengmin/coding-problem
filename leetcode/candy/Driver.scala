object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    println(s.candy(Array(1, 0, 2)))
    println(s.candy(Array(1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1)))
    println(s.candy(Array(5, 5, 4, 3, 3, 5, 8, 9, 7)))
  }
}
