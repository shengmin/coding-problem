/**
 * @author shengmin
 * @version 3
 */
public class Solution {
  public int solution(int N) {
    int count = 1;
    int target = N;

    while (target > 1) {
      if (target % 2 == 0) {
        target /= 2;
      } else {
        target--;
      }
      count++;
    }

    return count;
  }
}
