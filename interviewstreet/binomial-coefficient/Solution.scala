/**
 * @author ShengMin Zhang
 * @problem Binomial Coefficient
 * @revision 1.0
 * - Lucas' Theorem
 */
 
import java.io._
import java.util.{List => _, _}
import scala.math._
import scala.collection.immutable._

object Implicits {
  implicit def fromBigInt(n: BigInt) = new {
    def toBase(b: BigInt): List[BigInt] = {
      var digits: List[BigInt] = Nil
      var q = n
      while(q != 0) {
        digits = (q % b)::digits
        q = q / b
      }
      digits
    }
  }
}

class Solver {
  import Implicits._

  val rd = new BufferedReader(new InputStreamReader(System.in))
  val T = Integer.parseInt(rd.readLine)
  
  def solve(n: BigInt, p: BigInt): Unit = {
    val digits = n.toBase(p)
    var count = BigInt(1)
    for(digit <- digits) {
      count *= digit + 1
    }
    println(n - count + 1)
  }
  
  def solve: Unit = {
    for(i <- 0 until T) {
      val st = new StringTokenizer(rd.readLine)
      val N = BigInt(st.nextToken)
      val P = BigInt(st.nextToken)
      solve(N, P)
    }
  }
}

object Solution {
  def main(args: Array[String]) = new Solver().solve
}
