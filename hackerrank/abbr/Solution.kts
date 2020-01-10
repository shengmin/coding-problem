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

// Complete the abbreviation function below.
fun abbreviation(aString: String, bString: String): String {
  val matches = Array(aString.length + 1) { BooleanArray(bString.length + 1) }
  // Empty string is the same as empty string
  matches[0][0] = true
  for (i in 1..aString.length) {
    matches[i][0] = aString[i - 1].isLowerCase() && matches[i - 1][0]
  }

  for (i in 1..aString.length) {
    for (j in 1..bString.length) {
      val a = aString[i - 1]
      val b = bString[j - 1]
      if (a == b) {
        // Same char
        matches[i][j] = matches[i - 1][j - 1]
      } else if (a.isLowerCase()) {
        if (b.toLowerCase() == a) {
          // We can transform lowercase a into uppercase A or discard current A
          matches[i][j] = matches[i - 1][j - 1] || matches[i - 1][j]
        } else {
          // Or we can discard current a
          matches[i][j] = matches[i - 1][j]
        }
      } else {
        // a is uppercase, and the two don't match, nothing we can do
        matches[i][j] = false
      }
    }
  }

  return if (matches[aString.length][bString.length]) "YES" else "NO"
}

fun main(args: Array<String>) {
  val scan = Scanner(System.`in`)

  val q = scan.nextLine().trim().toInt()

  for (qItr in 1..q) {
    val a = scan.nextLine()

    val b = scan.nextLine()

    val result = abbreviation(a, b)

    println(result)
  }
}

main(arrayOf())
