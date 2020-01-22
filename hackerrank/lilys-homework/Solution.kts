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

// Complete the lilysHomework function below.
fun lilysHomework(originalArray: Array<Int>): Int {
  // to minimize the sum of adjacent elements, the array needs to be sorted
  val swapCount1 = countSwaps(originalArray, originalArray.sortedArray())
  val swapCount2 = countSwaps(originalArray, originalArray.sortedArrayDescending())
  return minOf(
      swapCount1,
      swapCount2
  )
}

fun countSwaps(arr: Array<Int>, sortedArray: Array<Int>): Int {
  val originalArray = arr.toIntArray()
  val indices = mutableMapOf<Int, Int>()
  for (i in 0 until originalArray.size) {
    val number = originalArray[i]
    if (number != sortedArray[i]) {
      indices[number] = i
    }
  }

  var count = 0
  for (i in 0 until originalArray.size) {
    val number = originalArray[i]
    val expectedNumber = sortedArray[i]
    if (number == expectedNumber) {
      // in the right place, no need to swap
      continue
    }

    // swap the expected number here
    count++

    val swapIndex = indices[expectedNumber]!!
    indices[number] = swapIndex

    originalArray[i] = expectedNumber
    originalArray[swapIndex] = number
  }

  return count
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val n = scan.nextLine().trim().toInt()

  val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

  val result = lilysHomework(arr)

  println(result)
}

main(arrayOf())
