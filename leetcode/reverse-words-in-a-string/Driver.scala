object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    println(s.reverseWords("the sky is blue"))
    println(s.reverseWords("    the sky  is   blue    "))
  }
}
