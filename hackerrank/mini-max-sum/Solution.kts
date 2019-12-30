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

// Complete the miniMaxSum function below.
fun miniMaxSum(arr: Array<Int>): Unit {
  var max = 0L
  var min = 0L
  val sorted = arr.sorted()
  for (i in 1 until arr.size) {
    min += sorted[i - 1]
    max += sorted[i]
  }
  println("%d %d".format(min, max))
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    miniMaxSum(arr)
}

main(arrayOf())
