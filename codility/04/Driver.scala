import scala.util.Random

object Driver {
  def main(a: Array[String]) {
    val s = new Solution()
    val random = new Random()
    println("codility 4")

    println(s.solution(Array()))
    println(s.solution(Array(2, 1, 5, -6, 9)))
    println(s.solution(Array(-1, -3, -4)))

    val a1 = (0 to 100000).map(_ => random.nextInt()).toArray
    println(s.solution(a1))
  }
}
