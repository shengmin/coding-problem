import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    print(s.permute(new int[] { 1, 2, 3, 4 }));
  }

  private static void print(ArrayList<ArrayList<Integer>> lists) {
    for (ArrayList<Integer> list: lists) {
      for (int n: list) {
        System.out.printf("%d ", n);
      }
      System.out.println();
    }
    System.out.println("---------------------");
  }

  public ArrayList<ArrayList<Integer>> permute(int[] num) {
    if (num.length == 0) return new ArrayList<ArrayList<Integer>>(0);
    LinkedList<ArrayList<Integer>> permutations = new LinkedList<ArrayList<Integer>>();
    ArrayList<Integer> permutation = new ArrayList<Integer>();
    permutation.add(num[0]);
    permutations.add(permutation);

    for (int i = 1; i < num.length; i++) {
      int n = num[i];
      for (int size = permutations.size(); size > 0; size--) {
        permutation = permutations.removeFirst();
        for (int j = permutation.size(); j >= 0; j--) {
          ArrayList<Integer> newPermutation = new ArrayList<Integer>(permutation.size() + 1);
          for (int k = 0; k < permutation.size(); k++) {
            if (k == j) newPermutation.add(n);
            newPermutation.add(permutation.get(k));
          }

          if (newPermutation.size() == permutation.size()) newPermutation.add(n);
          permutations.addLast(newPermutation);
        }
      }
    }

    return Collections.list(Collections.enumeration(permutations));
  }
}
