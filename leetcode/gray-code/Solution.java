import java.util.*;

public class Solution {
  public ArrayList<Integer> grayCode(int n) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(0);

    for (int i = 0; i < n; i++) {
      int mask = 1 << i;
      for (int j = list.size() - 1; j >= 0; j--) {
        list.add(list.get(j) | mask);
      }
    }

    return list;
  }
}
