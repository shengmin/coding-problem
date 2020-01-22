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

// Complete the organizingContainers function below.
fun organizingContainers(containers: Array<Array<Int>>): String {
  val colorCounts = LongArray(containers.size)
  val containerCounts = LongArray(containers.size)
  // count total number of balls for each color
  // for every color, we need to find one unused container that has the same capacity
  for (containerIndex in 0 until containers.size) {
    for (colorIndex in 0 until containers.size) {
      val count = containers[containerIndex][colorIndex]
      colorCounts[colorIndex] = colorCounts[colorIndex] + count
      containerCounts[containerIndex] = containerCounts[containerIndex] + count
    }
  }

  val unusedContainers = (0 until containers.size).toMutableSet()
  for (colorCount in colorCounts) {
    val containerIndex = unusedContainers.find {
      colorCount == containerCounts[it]
    }
    if (containerIndex == null) {
      return "Impossible"
    } else {
      unusedContainers.remove(containerIndex)
    }
  }

  return "Possible"
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val q = scan.nextLine().trim().toInt()

  for (qItr in 1..q) {
    val n = scan.nextLine().trim().toInt()

    val container = Array<Array<Int>>(n, { Array<Int>(n, { 0 }) })

    for (i in 0 until n) {
      container[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
    }

    val result = organizingContainers(container)

    println(result)
  }
}

main(arrayOf())
