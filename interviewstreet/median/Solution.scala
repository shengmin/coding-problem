/**
 * @author ShengMin Zhang
 * @problem Median
 * @revision 1.0
 * - Use a max and a min priority queue of equal size
 */
 
import java.io._
import java.util._
import java.text._
import scala.math._

class MedianQueue(initialCapacity: Int) {
  val leftQueue = new PriorityQueue[Int](initialCapacity / 2 + 10, new Comparator[Int] {
    def compare(a: Int, b: Int) = b compare a
  })
  val rightQueue = new PriorityQueue[Int](initialCapacity / 2 + 10, new Comparator[Int] {
    def compare(a: Int, b: Int) = a compare b
  })
  
  def rebalance: Unit = {
    if(leftQueue.size < rightQueue.size) {
      while(leftQueue.size != rightQueue.size) {
        leftQueue.add(rightQueue.poll)
      }
    } else if(leftQueue.size > rightQueue.size) {
      while((leftQueue.size - 1) != rightQueue.size) {
        rightQueue.add(leftQueue.poll)
      }
    }
  }
  
  def median: Double = {
    if(leftQueue.isEmpty && rightQueue.isEmpty) -1.0
    else if(leftQueue.size == rightQueue.size) (leftQueue.peek + rightQueue.peek).toDouble / 2
    else leftQueue.peek
  }
  
  def add(item: Int) = {
    if(leftQueue.size == rightQueue.size) {
      leftQueue.add(item)
    } else {
      rightQueue.add(item)
    }
    this
  }
  
  def remove(item: Int): Boolean = {
    val hasItem = 
      if(leftQueue.isEmpty && rightQueue.isEmpty) false
      else if(leftQueue.isEmpty) rightQueue.remove(item)
      else if(rightQueue.isEmpty) leftQueue.remove(item)
      else {
        if(item > leftQueue.peek) rightQueue.remove(item)
        else leftQueue.remove(item)
      }
    rebalance
    return hasItem
  }
  
  override def toString = {
    val sb = new StringBuilder()
    sb.append("[")
    var it = leftQueue.iterator
    while(it.hasNext) {
      sb.append(it.next)
      sb.append(", ")
    }
    sb.append("], [")
    it = rightQueue.iterator
    while(it.hasNext) {
      sb.append(it.next)
      sb.append(", ")
    }
    sb.append("]")
    
    sb.toString
  }
}

class Solver {
  val rd = new BufferedReader(new InputStreamReader(System.in))
  val N = Integer.parseInt(rd.readLine)
  
  def solve: Unit = {
    val queue = new MedianQueue(N)
    val formatter = new DecimalFormat("####.###")
  
    for(i <- 0 until N) {
      val st = new StringTokenizer(rd.readLine)
      val op = st.nextToken
      val value = Integer.parseInt(st.nextToken)
      
      op match {
        case "r" => {
          if(queue.remove(value)) {
            val median = queue.median
            if(median < 0) println("Wrong!")
            else println(formatter.format(median))
          } else {
            println("Wrong!")
          }
        }
        case "a" => {
          println(formatter.format(queue.add(value).median))
        }
      }
    }
  }
}

object Solution {
  def main(args: Array[String]) = new Solver().solve
}
