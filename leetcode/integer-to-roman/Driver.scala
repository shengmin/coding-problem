object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    for (i <- 1 to 100) {
      println(s.intToRoman(i))
    }
  }
}
