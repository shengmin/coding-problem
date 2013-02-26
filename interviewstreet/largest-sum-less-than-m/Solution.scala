import scala.io._
import java.util.StringTokenizer
import java.io._
import scala.collection.mutable._

object Solution {
  val rd = new BufferedReader(new InputStreamReader(System.in))
  val st = new StringTokenizer(rd.readLine())
  
  val N = st.nextToken().toInt
  val K = st.nextToken().toInt
  val M = st.nextToken().toInt
  val possibleSums = LinkedHashMap.empty[Int, Set[Int]]

  def printArray[E](arr: Array[Array[E]]) {
    for (row <- arr) {
      for (e <- row) {
        print(e)
        print(' ')
      }
      println()
    }
  }

  def main(args: Array[String]) {
    val st = new StringTokenizer(rd.readLine())
    possibleSums(0) = Set(0)
    
    for (i <- 0 until N) {
      val n = st.nextToken().toInt
      // Task #1
      val newSums = ArrayBuffer.empty[(Int, Int)]
      
      for ((sum, counts) <- possibleSums) {
        val newSum = n + sum
        if (newSum < M) {
          for (count <- counts) {
            newSums += ((newSum, count + 1))
          }
        }
      }
      
      for ((newSum, newCount) <- newSums) {
        val newCounts = possibleSums.get(newSum) match {
          case Some(x) => x
          case None => {
            val x = LinkedHashSet.empty[Int]
            possibleSums(newSum) = x
            x
          } 
        }
        
        newCounts += newCount
      }
    }
    
    rd.close();
    
    var max = 0
    for ((sum, counts) <- possibleSums) {
      if (sum > max && counts.contains(K)) {
        max = sum
      }
    }
    
    // Task #2
    val table = Array.ofDim[Int](max + 1, max + 1)
    table(1)(1) = 1
    
    for (i <- 1 to max) {
      table(i)(i) = 1
      for (j <- i - 1 to 1 by -1) {
        table(i)(j) = (table(i)(j + 1) + table(i - j)(j)) % 1000000007
      }
    }
    
    print(max)
    print(' ')
    println(table(max)(1))
    
    // printArray(table)
  }
}