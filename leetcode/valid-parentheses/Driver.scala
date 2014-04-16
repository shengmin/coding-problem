object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    println(s.isValid("()"))
    println(s.isValid("()[]{}"))
    println(s.isValid("((([]){}))"))
    println(s.isValid("{}{}()[]"))
    println(s.isValid("(]"))
    println(s.isValid("([)]"))
  }
}
