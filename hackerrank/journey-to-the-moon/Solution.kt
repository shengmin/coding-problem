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

// Complete the journeyToMoon function below.
fun journeyToMoon(n: Int, pairs: Array<Array<Int>>): Long {
  val nodeEdges = kotlin.arrayOfNulls<MutableList<Int>>(n)
  for (pair in pairs) {
    val (a, b) = pair
    val aNodeEdges = nodeEdges[a] ?: mutableListOf<Int>()
    val bNodeEdges = nodeEdges[b] ?: mutableListOf<Int>()
    aNodeEdges.add(b)
    bNodeEdges.add(a)
    nodeEdges[a] = aNodeEdges
    nodeEdges[b] = bNodeEdges
  }

  val visitedNodes = BooleanArray(n)

  fun countNodes(node: Int): Int {
    if (visitedNodes[node]) {
      return 0
    }
    visitedNodes[node] = true
    var nodeCount = 1
    val nextNodes = nodeEdges[node]
    if (nextNodes == null) {
      return 1
    }
    for (nextNode in nextNodes) {
      nodeCount += countNodes(nextNode)
    }
    return nodeCount
  }

  val countries = mutableListOf<Int>()
  var countryWithSingleNodeCount = 0
  for (node in 0 until n) {
    val nodeCount = countNodes(node)
    if (nodeCount == 0) {
      continue
    }

    if (nodeCount == 1) {
      countryWithSingleNodeCount++
    } else {
      countries.add(nodeCount)
    }
  }

  var totalCount = 0L
  for (i in 0 until countries.size) {
    for (j in i + 1 until countries.size) {
      totalCount += countries[i].toLong() * countries[j].toLong()
    }
  }

  // pair single node with multiple nodes
  for (countryCount in countries) {
    totalCount += countryCount * countryWithSingleNodeCount.toLong()
  }

  // pair single node with single node
  totalCount += (countryWithSingleNodeCount) * (countryWithSingleNodeCount.toLong() - 1) / 2

  return totalCount
}


fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val np = scan.nextLine().split(" ")

  val n = np[0].trim().toInt()

  val p = np[1].trim().toInt()

  val astronaut = Array<Array<Int>>(p, { Array<Int>(2, { 0 }) })

  for (i in 0 until p) {
    astronaut[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
  }

  val result = journeyToMoon(n, astronaut)

  println(result)
}

