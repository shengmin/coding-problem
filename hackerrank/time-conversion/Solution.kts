import java.io.*
import java.math.*
import java.text.*
import java.util.*
import java.util.regex.*

/*
 * Complete the timeConversion function below.
 */
fun timeConversion(s: String): String {
  val regex = Regex("(\\d\\d):(\\d\\d):(\\d\\d)(AM|PM)")
  val result = regex.find(s)!!
  val (hText, mText, sText, amorpm) = result.destructured

  var h = hText.toInt(10)
  val m = mText.toInt(10)
  val s = sText.toInt(10)

  if (amorpm == "PM") {
    if (h != 12) {
      h += 12
    }
  } else {
    if (h == 12) {
      h = 0
    }
  }

  return "%02d:%s:%s".format(h, mText, sText)
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val s = scan.nextLine()

    val result = timeConversion(s)

    println(result)
}

main(arrayOf())
