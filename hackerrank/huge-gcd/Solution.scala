import java.io._

object Solution {

  def gcd(a: Long, b: Long): Long = {
    if (a < b) return gcd(b, a)
    if (b == 0) return a
    return gcd(b, a % b)
  }

  def main(a: Array[String]) {
    val rd = new BufferedReader(new InputStreamReader(System.in))
    val n = rd.readLine().toInt
    val ns = rd.readLine().split(' ').map(x => x.toLong)
    val m = rd.readLine().toInt
    val ms = rd.readLine().split(' ').map(x => x.toLong)
    var result = 1L

    for (i <- 0 until n) {
      for (j <- 0 until m) {
        val g = gcd(ns(i), ms(j))
        ns(i) /= g
        ms(j) /= g
        result = (result * g) % 1000000007
      }
    }

    println(result)
  }
}
