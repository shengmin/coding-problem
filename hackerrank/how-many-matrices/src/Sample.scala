/**
 * @author ShengMin
 */

import scala.math.sqrt
import collection._

class Solver(N: Long) {

  def log[@specialized(Long) E](a: Array[Array[E]]) {
    for (row <- a) {
      for (i <- row) {
        printf("%5d ", i)
      }
      println()
    }
    println("-------------------------------------------------")
  }

  def solve(
      number: Long,
      unusedCells: mutable.LinkedHashSet[(Int, Int)],
      matrix: Array[Array[Long]]): Int = {

    if (number > N) {
      log(matrix)
      return 1
    }

    val validPositions = mutable.MutableList.empty[(Int, Int)]
    for ((i, j) <- unusedCells) {
      if (!unusedCells.contains((i - 1, j)) && !unusedCells.contains((i, j - 1))) {
        validPositions += ((i, j))
      }
    }

    var count = 0
    for ((i, j) <- validPositions) {
      matrix(i)(j) = number
      unusedCells -= ((i, j))
      count += solve(number + 1, unusedCells, matrix)
      unusedCells += ((i, j))
    }
    return count
  }

  def solve(width: Int, height: Int): Int = {
    val unusedCells = mutable.LinkedHashSet.empty[(Int, Int)]
    val matrix = Array.ofDim[Long](height, width)

    for (i <- 0 until height) {
      for (j <- 0 until width) {
        unusedCells += ((i, j))
      }
    }

    return solve(1, unusedCells, matrix)
  }

  def solve() {
    val end = sqrt(N).ceil.toInt
    var count = 0

    for (i <- 1 to N.toInt) {
      if (N % i == 0) {
        val quotient = (N / i).toInt
        count += solve(quotient, i.toInt)
      }
    }

    println(count)
  }
}

object Sample {
  def main(arguments: Array[String]) {
    val N = arguments(0).toLong
    new Solver(N).solve()
  }
}
