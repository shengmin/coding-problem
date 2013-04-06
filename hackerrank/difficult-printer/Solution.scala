import java.io.{FileReader, InputStreamReader, BufferedReader}
import java.util.StringTokenizer
import util.Sorting

/**
 * @author @shengmin
 */

object Solution {

  val reader = new BufferedReader(new InputStreamReader(System.in))
  val tokenizer = new StringTokenizer(reader.readLine())
  val N = tokenizer.nextToken().toInt
  val M = tokenizer.nextToken().toInt
  val printers = nextArray(N)
  val papers = nextArray(M)

  def main(arguments: Array[String]) {
    // Binary search for the answers

    var seconds = 1
    var min = 1
    var max = M + 1

    while (min < max) {
      val mid = min + (max - min) / 2
      if (isValid(mid)) {
        seconds = mid
        max = mid
      } else {
        min = mid + 1
      }
    }

    println(seconds)
    //printArray(printers)
    //printArray(papers)
  }

  /**
   * Checks if all printers can print all papers in {@link seconds}
   */
  private def isValid(seconds: Int): Boolean = {
    var paperIndex = 0
    var printerIndex = 0

    while (printerIndex < N && paperIndex < M) {
      var paperCount = 0
      while (paperCount < seconds && paperIndex < M && printers(printerIndex) >= papers(paperIndex)) {
        paperCount += 1
        paperIndex += 1
      }
      printerIndex += 1
    }

    return if (paperIndex >= M) true else false
  }

  private def printArray[@specialized(Int) T](array: Array[T]) {
    for (i <- array) {
      print(i)
      print(' ')
    }
    println()
  }

  private def nextArray(size: Int) = {
    val tokenizer = new StringTokenizer(reader.readLine())
    val array = new Array[Int](size)
    for (i <- 0 until size) {
      array(i) = tokenizer.nextToken().toInt
    }
    Sorting.quickSort(array)
    array
  }
}
