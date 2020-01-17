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

// Complete the acmTeam function below.
fun acmTeam(members: Array<String>): Array<Int> {
  var maxTopicCount = 0
  val topicCounts = mutableListOf<Int>()
  for (i in 0 until members.size) {
    for (j in i + 1 until members.size) {
      val memberA = members[i]
      val memberB = members[j]
      var topicCount = 0
      for (topicIndex in 0 until memberA.length) {
        if (memberA[topicIndex] == '1' || memberB[topicIndex] == '1') {
          topicCount++
        }
      }
      topicCounts.add(topicCount)
      maxTopicCount = maxOf(maxTopicCount, topicCount)
    }
  }

  return arrayOf(maxTopicCount, topicCounts.count { it == maxTopicCount })

}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val nm = scan.nextLine().split(" ")

  val n = nm[0].trim().toInt()

  val m = nm[1].trim().toInt()

  val topic = Array<String>(n, { "" })
  for (i in 0 until n) {
    val topicItem = scan.nextLine()
    topic[i] = topicItem
  }

  val result = acmTeam(topic)

  println(result.joinToString("\n"))
}

main(arrayOf())
