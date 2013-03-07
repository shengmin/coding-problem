
object Solution {
  val N = readLine().toInt
  val grid = Array.ofDim[Int](N, N)

  def log[E](a: Array[Array[E]]) {
    for (row <- a) {
      for (i <- row) {
        print(i)
        print(' ')
      }
      println()
    }
  }
  
  def main(a: Array[String]) {
    for (i <- 0 until N) grid(i)(0) = 1
    
    for (i <- 1 until N) {
      for (j <- 1 to i) {
        grid(i)(j) = (grid(i - 1)(j) + grid(i)(j - 1)) % 10007
      }
    }

    println((grid(N - 1)(N - 1) * 2) % 10007)
  }
}
