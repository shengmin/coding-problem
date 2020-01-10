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

// Complete the jumpingOnClouds function below.
fun jumpingOnClouds(clouds: Array<Int>): Int {
  val jumps = IntArray(clouds.size) { Int.MAX_VALUE }
  jumps[0] = 0
  for (i in 1 until clouds.size) {
    if (clouds[i] == 1) {
      continue
    }
    val lastIndices = arrayOf(i - 1, i - 2)
    val lastJumps = lastIndices.map(fun(lastIndex) = if (lastIndex < 0) Int.MAX_VALUE else jumps[lastIndex])
    val minJump = lastJumps.min<Int>()!!
    jumps[i] = if (minJump == Int.MAX_VALUE) Int.MAX_VALUE else minJump + 1
  }

  return jumps[clouds.size - 1]
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val n = scan.nextLine().trim().toInt()

  val c = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

  val result = jumpingOnClouds(c)

  println(result)
}

main(arrayOf())
