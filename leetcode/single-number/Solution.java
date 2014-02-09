public class Solution {
  public int singleNumber(int[] A) {
    int value = 0;
    for (int a: A) {
      value ^= a;
    }
    return value;
  }
}
