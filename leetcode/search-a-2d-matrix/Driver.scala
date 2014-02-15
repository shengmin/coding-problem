object Driver {
  def main(a: Array[String]) {
    val s = new Solution
    val matrix = Array(
      Array(1, 3, 5, 7),
      Array(10, 11, 16, 20),
      Array(23, 30, 34, 50)
    )

    for (i <- 1 to 50) {
      if (s.searchMatrix(matrix, i)) println(i)
    }
  }
}
