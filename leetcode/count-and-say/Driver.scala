object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    for (i <- 1 to 10) {
      println(s.countAndSay(i))
    }
  }
}
