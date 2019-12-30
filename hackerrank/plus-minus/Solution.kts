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

// Complete the plusMinus function below.
fun plusMinus(arr: Array<Int>): Unit {
  var positiveCount = 0
  var negativeCount = 0
  var zeroCount = 0

  for (i in arr) {
    if (i > 0) {
      positiveCount++
    } else if (i < 0) {
      negativeCount++
    } else {
      zeroCount++
    }
  }

  println("%.6f".format(positiveCount / arr.size.toDouble()))
  println("%.6f".format(negativeCount / arr.size.toDouble()))
  println("%.6f".format(zeroCount / arr.size.toDouble()))
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val n = scan.nextLine().trim().toInt()

    val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()

    plusMinus(arr)
}

main(arrayOf())
