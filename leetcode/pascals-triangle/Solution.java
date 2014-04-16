import java.util.*;

public class Solution {
  public ArrayList<ArrayList<Integer>> generate(int numRows) {
    ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>(numRows);

    if (numRows == 0) return triangle;

    ArrayList<Integer> row = new ArrayList<Integer>(1);
    row.add(1);
    triangle.add(row);

    for (int i = 1; i < numRows; i++) {
      ArrayList<Integer> lastRow = triangle.get(i - 1);
      ArrayList<Integer> newRow = new ArrayList<Integer>(lastRow.size() + 1);

      newRow.add(1);
      for (int j = 1; j < lastRow.size(); j++) {
        newRow.add(lastRow.get(j - 1) + lastRow.get(j));
      }
      newRow.add(1);
      triangle.add(newRow);
    }

    return triangle;
  }
}
