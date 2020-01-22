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

// Complete the biggerIsGreater function below.
fun biggerIsGreater(w: String): String {
  var nonIncreasingIndex = -1
  for (i in w.length - 2 downTo 0) {
    if (w[i] < w[i + 1]) {
      nonIncreasingIndex = i
      break
    }
  }

  if (nonIncreasingIndex == -1) {
    return "no answer"
  }

  var swapIndex = -1
  for (i in w.length - 1 downTo nonIncreasingIndex + 1) {
    if (w[i] > w[nonIncreasingIndex]) {
      if (swapIndex == -1 || w[i] < w[swapIndex])
        swapIndex = i
    }
  }

  val chars = w.toCharArray()
  chars[swapIndex] = w[nonIncreasingIndex]
  chars[nonIncreasingIndex] = w[swapIndex]

  var i = nonIncreasingIndex + 1
  var j = w.length - 1
  while (i < j) {
    val iChar = chars[i]
    val jChar = chars[j]
    chars[i] = jChar
    chars[j] = iChar
    i++
    j--
  }

  return String(chars)
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val T = scan.nextLine().trim().toInt()

  for (TItr in 1..T) {
    val w = scan.nextLine()

    val result = biggerIsGreater(w)

    println(result)
  }
}

main(arrayOf())
