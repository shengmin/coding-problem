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

data class Node(val includedMax: Int, val excludedMax: Int)

// Complete the maxSubsetSum function below.
fun maxSubsetSum(arr: Array<Int>): Int {
  if (arr.size == 1) {
    return maxOf(0, arr[0])
  }
  val sums = Array<Node?>(arr.size) { null }
  sums[0] = Node(arr[0], 0)
  sums[1] = Node(arr[1], maxOf(arr[0], 0))
  for (i in 2 until arr.size) {
    val n = arr[i]
    val previousNode = sums[i - 1]!!
    // If we exclude the current element
    // Then, the new excludedMax is just the max(previous includedMax, previous excludedMax)
    val excludedMax = maxOf(previousNode.includedMax, previousNode.excludedMax)
    // If we include the current element, the max will be max(previous excludedMax + current, i - 2 includedMax + current)
    val includedMax = maxOf(previousNode.excludedMax + n, sums[i - 2]!!.includedMax + n)
    sums[i] = Node(includedMax, excludedMax)
  }
  return maxOf(sums.last()!!.includedMax, sums.last()!!.excludedMax)
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val n = scan.nextLine().trim().toInt()

  val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

  val res = maxSubsetSum(arr)

  println(res)
}

main(arrayOf())
