/**
 * @author shengmin
 */
object Solution {
  type Triangle = Array[Array[Int]]

  def printTriangle(triangle: Triangle) {
    for (i <- 0 until triangle.length) {
      for (j <- 0 until i) {
        print(triangle(i)(j))
        print(' ')
      }
      println(triangle(i)(i))
    }
  }

  def computeTriangle(triangle: Triangle): Triangle = {
    triangle(0)(0) = 1
    for (i <- 1 until triangle.length) {
      triangle(i)(0) = 1
      for (j <- 1 until i) {
        triangle(i)(j) = triangle(i - 1)(j - 1) + triangle(i - 1)(j)
      }
      triangle(i)(i) = 1
    }
    return triangle
  }

  def createTriangle(N: Int) = Array.ofDim[Int](N, N)

  def main(args: Array[String]) {
    printTriangle(computeTriangle(createTriangle(readLine().toInt)))
  }
}
