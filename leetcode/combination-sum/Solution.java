import java.util.*;

public class Solution {
  int[] candidates;
  ArrayList<ArrayList<Integer>> result;
  LinkedList<Integer> working;

  void findAll(int level, int target) {
    if (target < 0) return;
    if (level >= candidates.length) {
      if (working.size() > 0 && target == 0) {
        result.add(new ArrayList<Integer>(working));
      }
      return;
    }

    int n = candidates[level];
    int maxCount = target / n;

    findAll(level + 1, target);
    for (int i = 0; i < maxCount; i++) {
      working.add(n);
      target -= n;

      findAll(level + 1, target);
    }

    for (int i = 0; i < maxCount; i++) {
      working.removeLast();
    }
  }

  public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    this.candidates = candidates;
    result = new ArrayList<ArrayList<Integer>>();
    working = new LinkedList<Integer>();

    findAll(0, target);

    return result;
  }
}
