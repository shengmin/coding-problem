import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) throws Exception {
    Solution s = new Solution();
    BufferedReader rd = new BufferedReader(new FileReader(args[0]));
    int t = Integer.parseInt(rd.readLine());
    for (int i = 0; i < t; i++) {
      StringTokenizer st = new StringTokenizer(rd.readLine());
      List<Integer> ns = new ArrayList<Integer>();
      while (st.hasMoreTokens()) {
        ns.add(Integer.parseInt(st.nextToken()));
      }
      int[] ints = new int[ns.size()];
      for (int j = 0; j < ints.length; j++) {
        ints[j] = ns.get(j);
      }
      print(s.sortedArrayToBST(ints), 0);
      System.out.println("-------------------");
    }
  }

  private static void print(TreeNode tree, int level) {
    if (tree == null) return;

    for (int i = 0; i < level; i++) {
      System.out.print(' ');
    }
    System.out.println(tree.val);
    print(tree.left, level + 1);
    print(tree.right, level + 1);
  }

  public TreeNode sortedArrayToBST(int[] num) {
    return toBinarySearchTree(num, 0, num.length);
  }

  private TreeNode toBinarySearchTree(int[] numbers, int start, int end) {
    if (start >= end) return null;

    int midIndex = start + (end - start) / 2;
    int mid = numbers[midIndex];

    TreeNode node = new TreeNode(mid);
    node.left = toBinarySearchTree(numbers, start, midIndex);
    node.right = toBinarySearchTree(numbers, midIndex + 1, end);
    return node;
  }
}

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode(int x) {
    this.val = x;
  }
}
