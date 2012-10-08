/**
 * @author ShengMin Zhang
 * @problem Flowers
 * @revision 1.0
 * - Greedy
 * - Always choose the most expensive flower to buy first
 */
 
import java.util.{PriorityQueue => _, _}
import java.io._
import scala.math._
import scala.util._

class Solver {
  val rd = new BufferedReader(new InputStreamReader(System.in))
  var st = new StringTokenizer(rd.readLine)
  val N = Integer.parseInt(st.nextToken)
  val K = Integer.parseInt(st.nextToken)
  var flowersPrice = new Array[Int](N)
  
  def solve = {
    st = new StringTokenizer(rd.readLine)
    for(i <- 0 until N) {
      flowersPrice(i) = Integer.parseInt(st.nextToken)
    }
    
    Sorting.quickSort(flowersPrice)
    flowersPrice = flowersPrice.reverse
    
    var money = 0
    for(i <- 0 until N) {
      val multiple = (i / K) + 1
      money += flowersPrice(i) * multiple
    }
    println(money)
  }
}

object Solution {
  def main(args: Array[String]) = new Solver().solve
}