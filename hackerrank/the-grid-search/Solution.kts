import java.util.*;
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


class CompressedRow(val nodes: List<Node>) {
  data class Node(val start: Int, val end: Int, val value: Char) {
    val size: Int = end - start
  }

  companion object {
    fun fromString(rowString: String): CompressedRow {
      val nodes = mutableListOf<Node>()
      var lastChar = rowString[0]
      var lastStart = 0

      for (i in 1 until rowString.length) {
        val char = rowString[i]
        if (char == lastChar) {
          continue
        }
        nodes.add(Node(lastStart, i, lastChar))
        lastStart = i
        lastChar = char
      }
      nodes.add(Node(lastStart, rowString.length, lastChar))

      return CompressedRow(nodes)
    }

    fun fromArray(grid: Array<String>): List<CompressedRow> {
      return grid.map { row -> fromString(row) }
    }
  }

  override fun toString(): String {
    return nodes.flatMap { node ->
      (1..node.end - node.start).map { node.value }
    }.joinToString("")
  }

  fun startingIndices(pattern: CompressedRow): Set<Int> {
    val indices = mutableSetOf<Int>()
    if (pattern.nodes.size == 1) {
      val patternNode = pattern.nodes[0]
      for (gridNode in nodes) {
        if (gridNode.value == patternNode.value && gridNode.size >= patternNode.size) {
          for (i in gridNode.start..gridNode.end - patternNode.size) {
            indices.add(i)
          }
        }
      }
      return indices
    }


    loop@ for (startingIndex in 0..nodes.size - pattern.nodes.size) {
      for (i in 0 until pattern.nodes.size) {
        val patternNode = pattern.nodes[i]
        val gridNode = nodes[startingIndex + i]
        if (patternNode.value != gridNode.value) {
          // It's not the same sequence of chars, so it cannot match
          continue@loop
        }
        if (patternNode.size > gridNode.size) {
          // Pattern has more chars in the sequence
          continue@loop
        }

        if (i != 0 && i != pattern.nodes.size - 1 && patternNode.size != gridNode.size) {
          // Unless it's the prefix/suffix we're matching, the # of chars need to match
          continue@loop
        }
      }

      indices.add(nodes[startingIndex].end - pattern.nodes[0].size)
    }
    return indices
  }
}

// Complete the gridSearch function below.
fun gridSearch(g: Array<String>, p: Array<String>): String {
  val grid = CompressedRow.fromArray(g)
  val pattern = CompressedRow.fromArray(p)

  val startingIndices = Array<Set<Int>>(p.size) { emptySet<Int>() }

  for (gridStartRowIndex in 0..g.size - p.size) {
    for (i in 0 until p.size) {
      val gridRow = grid[gridStartRowIndex + i]
      val patternRow = pattern[i]
      startingIndices[i] = gridRow.startingIndices(patternRow)
    }

    val isMatch = startingIndices[0].any { index ->
      startingIndices.all { s -> s.contains(index) }
    }


    if (isMatch) {
      return "YES"
    }
  }

  return "NO"
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val t = scan.nextLine().trim().toInt()

  for (tItr in 1..t) {
    val RC = scan.nextLine().split(" ")

    val R = RC[0].trim().toInt()

    val C = RC[1].trim().toInt()

    val G = Array<String>(R, { "" })
    for (i in 0 until R) {
      val GItem = scan.nextLine()
      G[i] = GItem
    }

    val rc = scan.nextLine().split(" ")

    val r = rc[0].trim().toInt()

    val c = rc[1].trim().toInt()

    val P = Array<String>(r, { "" })
    for (i in 0 until r) {
      val PItem = scan.nextLine()
      P[i] = PItem
    }

    val result = gridSearch(G, P)

    println(result)
  }
}

main(arrayOf())
