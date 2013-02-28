import java.io._
import java.util.StringTokenizer
import scala.collection.mutable._

object Solution {
  val rd = new BufferedReader(new InputStreamReader(System.in))
  val pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))
  val N = rd.readLine().toInt
  val bd = Array.ofDim[Int](9, 9)
  val used = Array.ofDim[Set[Int]](9, 9)
  
  def log[E](a: Array[Array[E]]) {
    for (row <- a) {
      for (i <- row) {
        print(i)
        print(' ')
      }
      println()
    }
  }
  
  def solve(i: Int, j: Int): Boolean = {
    if(i == 9) {
      log(bd)
      return true
    }

    if(j == 9) return solve(i + 1, 0)

    if(bd(i)(j) == 0) {
      for(v <- 1 to 9) {
        if (!used(i)(j).contains(v)) {
          val (valid, undos) = invalidate(v, i, j)
          if (valid) {
            bd(i)(j) = v
            if (solve(i, j + 1)) return true
            bd(i)(j) = 0
          }
          for ((x, y) <- undos) used(x)(y) -= v
        }
      }
    } else {
      return solve(i, j + 1)
    }
    return false
  }

  def invalidate(n: Int, i: Int, j: Int, undos: MutableList[(Int, Int)]) = {
    if (used(i)(j) == null) printf("%d %d\n", i, j)
    if (!used(i)(j).contains(n)) {
      undos += ((i, j))
    }
    if ((used(i)(j) += n).size == 9) false else true
  }
  
  def invalidate(N: Int, I: Int, J: Int): (Boolean, MutableList[(Int, Int)]) = {
    val undos = MutableList.empty[(Int, Int)]
    for (j <- 0 until 9) if (j != J && !invalidate(N, I, j, undos)) return (false, undos)
    for (i <- 0 until 9) if (i != I && !invalidate(N, i, J, undos)) return (false, undos)
    val _i = (I / 3) * 3
    val _j = (J / 3) * 3
    for (i <- _i until _i + 3) {
      for (j <- _j until _j + 3) {
        if ((i != I || j != J) && !invalidate(N, i, j, undos)) return (false, undos)
      }
    }
    return (true, undos)
  }
  
  def main(a: Array[String]) {
    for (i <- 0 until N) {
      for (j <- 0 until 9) {
        val st = new StringTokenizer(rd.readLine())
        for (k <- 0 until 9) {
          bd(j)(k) = st.nextToken().toInt
          used(j)(k) = HashSet.empty[Int]
        }
      }
      
      for (j <- 0 until 9) {
        for (k <- 0 until 9) {
          if (bd(j)(k) != 0) invalidate(bd(j)(k), j, k)
        }
      }
      solve(0, 0)
    }
  }
}