object Driver {
  def main(a: Array[String]) {
    val s = new Solution
    println(s.strStr("mississippi", "a"))
    println(s.strStr("text", "ex"))
    println(s.strStr("text", "tea"))
    println(s.strStr("abcabd", "abd"))
    println(s.strStr("abcabd", "abc"))
  }
}
