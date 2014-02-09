import java.io._
import scala.collection.mutable

object E {

  val fromChar = mutable.HashMap(
    'A' -> ".-",
    'H' -> "....",
    'O' -> "---",
    'V' -> "...-",
    'B' -> "-...",
    'I' -> "..",
    'P' -> ".--.",
    'W' -> ".--",
    'C' -> "-.-.",
    'J' -> ".---",
    'Q' -> "--.-",
    'X' -> "-..-",
    'D' -> "-..",
    'K' -> "-.-",
    'R' -> ".-.",
    'Y' -> "-.--",
    'E' -> ".",
    'L' -> ".-..",
    'S' -> "...",
    'Z' -> "--..",
    'F' -> "..-.",
    'M' -> "--",
    'T' -> "-",
    'G' -> "--.",
    'N' -> "-.",
    'U' -> "..-",
    '_' -> "..--",
    ',' -> ".-.-",
    '.' -> "---.",
    '?' -> "----"
  )

  val fromMorse = {
    val map = mutable.HashMap.empty[String, Char]
    for ((c, t) <- fromChar) {
      map.put(t, c)
    }
    map
  }

  def main(arguments: Array[String]) {
    val rd = new BufferedReader(
      if (arguments.length == 0) new InputStreamReader(System.in)
      else new FileReader(arguments(0)))

    var ln = rd.readLine()
    while (ln != null) {
      val lengths = new Array[Int](ln.length)
      val morseBuilder = StringBuilder.newBuilder
      for (i <- 0 until ln.length) {
        val morse = fromChar(ln(i))
        lengths(i) = morse.length
        morseBuilder.append(morse)
      }

      val morseText = morseBuilder.toString()
      val sb = StringBuilder.newBuilder
      var iText = 0
      for (i <- ln.length - 1 to 0 by -1) {
        val length = lengths(i)
        sb.append(fromMorse(morseText.substring(iText, iText + length)))
        iText += length
      }
      println(sb.toString())
      ln = rd.readLine()
    }

    rd.close()
  }
}
