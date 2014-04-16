object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    println(s.numDistinct("rabbbit", "rabbit"))
    println(s.numDistinct("rabbbit", "rabit"))
    println(s.numDistinct("rabbbit", "raa"))
  }
}
