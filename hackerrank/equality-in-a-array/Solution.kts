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

// Complete the equalizeArray function below.
fun equalizeArray(arr: Array<Int>): Int {
  val counts = mutableMapOf<Int, Int>()

  for (n in arr) {
    counts[n] = counts.getOrDefault(n, 0) + 1
  }
  val maxEntry = counts.maxBy({ it.value })!!

  return arr.size - maxEntry.value
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val n = scan.nextLine().trim().toInt()

  val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

  val result = equalizeArray(arr)

  println(result)
}

main(arrayOf())
