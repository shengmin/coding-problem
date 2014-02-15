public class Solution {

  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0) return false;
    int rowCount = matrix.length;
    int columnCount = matrix[0].length;

    // Binary search
    int start = 0;
    int end = rowCount * columnCount;

    while (start < end) {
      int midIndex = start + (end - start) / 2;
      // Convert index in a flat sorted array into row and column number in the matrix
      int rowIndex = midIndex / columnCount;
      int columnIndex = midIndex % columnCount;
      int mid = matrix[rowIndex][columnIndex];
      if (mid ==  target) return true;
      if (target < mid) end = midIndex;
      else start = midIndex + 1;
    }

    return false;
  }
}
