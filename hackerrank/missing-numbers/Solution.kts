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

// Complete the missingNumbers function below.
fun missingNumbers(arr: Array<Int>, brr: Array<Int>): IntArray {
  val originalCounts = countOccurrences(brr)
  val missingCounts = countOccurrences(arr)
  val results = mutableListOf<Int>()

  for (entry in originalCounts) {
    if (!missingCounts.containsKey(entry.key)) {
      results.add(entry.key)
    } else if (missingCounts[entry.key] != entry.value) {
      results.add(entry.key)
    }
  }

  return results.sorted().toIntArray()
}

fun countOccurrences(arr: Array<Int>): Map<Int, Int> {
  val counts = mutableMapOf<Int, Int>()
  for (item in arr) {
    val count = counts.getOrDefault(item, 0)
    counts[item] = count + 1
  }
  return counts
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val m = scan.nextLine().trim().toInt()

    val brr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    val result = missingNumbers(arr, brr)

    println(result.joinToString(" "))
}

main(arrayOf())
