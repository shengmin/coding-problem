import java.util.*;

public class Solution {
  public int jump(int[] jumps) {
    if (jumps.length == 0) return 0;

    int lastIndex = jumps.length - 1;
    boolean[] visited = new boolean[jumps.length];
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(0);
    int stepCount = 0;
    visited[0] = true;

    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int i = 0; i < queueSize; i++) {
        int index = queue.removeFirst();
        if (index == lastIndex) {
          return stepCount;
        }

        int maxJump = jumps[index];
        for (int jump = 1; jump <= maxJump; jump++) {
          int newIndex = index + jump;
          if (newIndex >= jumps.length) {
            break;
          }
          if (!visited[newIndex]) {
            visited[newIndex] = true;
            queue.addLast(newIndex);
          }
        }
      }

      stepCount++;
    }

    return stepCount;
  }
}
