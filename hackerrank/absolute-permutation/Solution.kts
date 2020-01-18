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

// Complete the absolutePermutation function below.
fun absolutePermutation(n: Int, k: Int): IntArray {
  val ints = IntArray(n)
  val usedNumbers = mutableSetOf<Int>()

  for (i in 1..n) {
    val lower = i - k
    if (lower >= 1 && !usedNumbers.contains(lower)) {
      ints[i - 1] = lower
      usedNumbers.add(lower)
      continue
    }

    val upper = i + k
    if (upper <= n && !usedNumbers.contains(upper)) {
      ints[i - 1] = upper
      usedNumbers.add(upper)
      continue
    }
    return intArrayOf(-1)
  }

  return ints
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val t = scan.nextLine().trim().toInt()

  for (tItr in 1..t) {
    val nk = scan.nextLine().split(" ")

    val n = nk[0].trim().toInt()

    val k = nk[1].trim().toInt()

    val result = absolutePermutation(n, k)

    println(result.joinToString(" "))
  }
}

main(arrayOf())
