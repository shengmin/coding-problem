/**
 * @author ShengMin Zhang
 * @revision 1.0 
 * - Greedy
 */

import java.util._
import java.io._
import scala.util._
import scala.math._

class Solver {
  class Child(val id: Int, val rating: Int) extends Ordered[Child] {
    def compare(that: Child): Int = {
      rating - that.rating
    }
  }

  val rd = new BufferedReader(new InputStreamReader(System.in))
  val N = Integer.parseInt(rd.readLine())
  val counts = new Array[Int](N + 2)
  val ratings = new Array[Int](N + 2)
  val children = new Array[Child](N)
  
  def solve() = {
    ratings(0) = Integer.MAX_VALUE
    ratings(N + 1) = Integer.MAX_VALUE
    for(i <- 1 to N) {
      val rating = Integer.parseInt(rd.readLine())
      ratings(i) = rating
      children(i - 1) = new Child(i, rating)
    }
    rd.close()
    
    Sorting.quickSort(children)
    
    for(child <- children) {
      val id = child.id
      val rating = child.rating
      val leftCount = counts(id - 1)
      val rightCount = counts(id + 1)
      val leftRating = ratings(id - 1)
      val rightRating = ratings(id + 1)
      
      counts(id) =
        if(leftCount == 0 && rightCount == 0) {
          1
        } else if(leftCount == 0) {
          if(rightRating == rating) 1 else rightCount + 1
        } else if(rightCount == 0) {
          if(leftRating == rating) 1 else leftCount + 1
        } else {
          val left = if(leftRating == rating) 1 else leftCount + 1
          val right = if(rightRating == rating) 1 else rightCount + 1
          max(left, right)
        }
    }
    
    var sum = 0
    for(i <- 1 to N) {
      sum += counts(i)
    }
    
    println(sum)
  }
}

object Solution {
  def main(args: Array[String]) = new Solver().solve()
}