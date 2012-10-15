/**
 * @author ShengMin Zhang
 * @problem Meeting Point
 * @revision 1.0
 * - Find the centroid of the set of points
 * - Sort the points by the distance to the centroid
 * - Start trying from the first point as the meeting point until a point whose distance sum is greater than the previous sum 
 */

import java.io._
import java.util._
import scala.util._
import scala.math._
 
case class Point(x: Int, y: Int) {
  def distanceTo(that: Point): Int = max(abs(x - that.x), abs(y - that.y))
}
 
class Solver {
  val reader = new BufferedReader(new InputStreamReader(System.in))
  val N = Integer.parseInt(reader.readLine)
  val points = new Array[Point](N)
  
  def findMinDistanceSum: Long = {
    var minDistanceSum = Long.MaxValue
    for(meetingPoint <- points) {
      var distanceSum: Long = 0
      
      for(point <- points) {
        distanceSum += meetingPoint.distanceTo(point)
      }
      
      if(distanceSum > minDistanceSum) {
        return minDistanceSum
      } else {
        minDistanceSum = distanceSum
      }
    }
    return minDistanceSum
  }
  
  def solve: Unit = {
    var xSum = 0
    var ySum = 0
    
    for(i <- 0 until N) {
      val tokenizer = new StringTokenizer(reader.readLine)
      val x = Integer.parseInt(tokenizer.nextToken)
      val y = Integer.parseInt(tokenizer.nextToken)
      xSum += x
      ySum += y
      points(i) = Point(x, y)
    }
    
    val centroid = Point(xSum / N, ySum / N)
    object PointOrdering extends Ordering[Point] {
      def compare(a: Point, b: Point): Int = a.distanceTo(centroid) - b.distanceTo(centroid) 
    }
    Sorting.quickSort(points)(PointOrdering)
    
    println(findMinDistanceSum)
  }
}
 
object Solution {
  def main(args: Array[String]) = new Solver().solve
}
