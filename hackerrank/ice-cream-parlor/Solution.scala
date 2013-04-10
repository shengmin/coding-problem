import java.io._
import java.util.{StringTokenizer}
import scala.collection.mutable

object Solution {
	def main(arguments: Array[String]) {
		val rd = new BufferedReader(new InputStreamReader(System.in))
		val T = rd.readLine().toInt
		
		for (i <- 0 until T) {
			val C = rd.readLine().toInt
			val L = rd.readLine().toInt
			val st = new StringTokenizer(rd.readLine())
			val nums = mutable.HashMap.empty[Int, Int]
			
			for (j <- 1 to L) {
				val n = st.nextToken().toInt
				nums.get(C - n) match {
					case None => {
						if (!nums.contains(n)) {
							nums(n) = j
						}
					}
					case Some(index) => {
						printf("%d %d", index, j)
						println()
					}
				}
			}	
		}
		
		rd.close()
	}
	
}