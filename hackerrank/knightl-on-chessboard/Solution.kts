import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the knightlOnAChessboard function below.
fun knightlOnAChessboard(n: Int): Array<IntArray> {
  val results = Array(n - 1, {IntArray(n - 1) {0}})

  for (deltaX in 1 until n) {
    for (deltaY in deltaX until n) {
      val result = computeMinMoves(n, deltaX, deltaY)
      results[deltaX - 1][deltaY - 1] = result
      // knightL(i, j) is same as knightL(j, i), so just copy over the results
      results[deltaY - 1][deltaX - 1] = result
    }
  }

  return results
}

data class Candidate(val x: Int, val y: Int, val moves: Int)

fun computeMinMoves(n: Int, a: Int, b: Int): Int {
  val moves = Array(n, {IntArray(n) { -1 }})
  moves[0][0] = 0

  val transformations = arrayOf(
    arrayOf(a, b),
    arrayOf(a, -b),
    arrayOf(-a, -b),
    arrayOf(-a, b),
    arrayOf(b, a),
    arrayOf(b, -a),
    arrayOf(-b, a),
    arrayOf(-b, -a)
  )
  val queue = PriorityQueue(n * n, compareBy<Candidate> {it.moves})
  queue.add(Candidate(0, 0, 0))
  while (!queue.isEmpty()) {
    val head = queue.poll()!!
    if (head.x == n - 1 && head.y == n - 1) {
      return head.moves
    }
    for (transformation in transformations) {
      val (deltaX, deltaY) = transformation
      val newX = head.x + deltaX
      val newY = head.y + deltaY
      if (newX < 0 || newY < 0 || newX >= n || newY >= n) {
        // out of bounds, not a valid move
        continue
      }

      val moveCount = moves[newX][newY]
      if (moveCount != -1) {
        // visited before
        continue
      }

      moves[newX][newY] = head.moves + 1
      queue.add(Candidate(newX, newY, head.moves + 1))
    }
  }
  return -1
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val result = knightlOnAChessboard(n)

    println(result.map{ it.joinToString(" ") }.joinToString("\n"))
}

main(arrayOf())
