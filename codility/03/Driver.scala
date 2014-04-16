object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    println("codility 3")

    for (i <- 1 until 100) {
      println(s"${i}: ${s.solution(i)}")
    }
  }
}
