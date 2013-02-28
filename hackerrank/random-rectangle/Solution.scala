import java.io._
import java.util.{StringTokenizer}
import scala.collection.mutable.HashMap

object Solution {
  object Stage extends Enumeration {
    type Stage = Value
    val Width, Height = Value
  }

  import Stage._
  
  val reader = new BufferedReader(new InputStreamReader(System.in))
  val N = reader.readLine().trim().toInt
  val sticks = new Array[Int](N)
  val tokenizer = new StringTokenizer(reader.readLine())
  
  for (i <- 0 until N) {
    sticks(i) = tokenizer.nextToken().toInt
  }
  
  val R = reader.readLine().trim().toInt
  val table = HashMap.empty[String, (Int, Double)]
  var expectedArea: Double = 0.0D

  def getKey(width: Int, height: Int, usedSticks: BitSet, stage: Stage) = {
    "%d,%d,%d,%d".format(width, height, usedSticks.toLongArray()(0), stage.id)
  }
  
  def solveWidth(width: Int, height: Int, usedSticks: BitSet): List[(Int, Double)] = {
    val stickId = usedSticks.nextClearBit();
    if (stickId == N || width > R) {
      return Nil
    }
    
    val list1 = solve(width + sticks(stickId), height
    
    //val (a1, p1) = solve(width + sticks(stickId), height, stickId + 1)
    return null
  }
  
  def solveHeight(width: Int, height: Int, stickId: Int): (Int, Double) = {
    return null
  }
  
  def solve(width: Int, height: Int, stickId: Int, stage: Stage): (Int, Double) = {
    val key = getKey(width, height, stickId, stage)
    table.get(key) match {
      case Some(x) => x
      case None => {
        val result = stage match {
          case Width => solveWidth(width, height, stickId)
          case Height => solveHeight(width, height, stickId)
        }
        table(key) = result
        result
      }
    }
  }
  
  def main(a: Array[String]) {
    //solve(0, 0, 0, Stage.Width)
    println(expectedArea)
  }
}
