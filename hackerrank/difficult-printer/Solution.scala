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
    // Sort both arrays
    // For a paper with value a, pick the next available printer whose ability value is just >= a using binary search
    // If no such printer available, wait for all printers to complete, ie. add one more seconds

    var time = 0
    var printerIndex = 0
    var paperIndex = 0
    while (paperIndex < M) {
      find(papers(paperIndex), printerIndex, printerIndex, N) match {
        case Some(capablePrinterIndex) => {
          printerIndex = capablePrinterIndex + 1
          paperIndex += 1
        }
        case None => {
          printerIndex = 0
          time += 1
        }
      }
    }

    println(time + 1)
    //printArray(printers)
    //printArray(papers)
  }

  /**
   * Gets the index of the printer that should print the current paper
   */
  private def find(paper: Int, zero: Int, start: Int, end: Int): Option[Int] = {
    if (start >= end) return None;
    val mid = start + (end - start) / 2;
    val printer = printers(mid);

    if (printer < paper) {
      return find(paper, zero, mid + 1, end);
    } else {
      // Current printer is capable of printing this paper
      if (mid == zero || printers(mid - 1) < paper) {
        return Some(mid);
      } else {
        return find(paper, zero, start, mid);
      }
    }
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
