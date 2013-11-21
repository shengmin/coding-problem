public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n22 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n33 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);
    TreeNode n44 = new TreeNode(4);

    n1.left = n2;
    n1.right = n22;
    n2.left = n3;
    n2.right = n4;
    n22.left = n44;
    n22.right = n33;
    System.out.println(s.isSymmetric(n1));
    System.out.println(s.isSymmetric(n22));
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return isSymmetric(root.left, root.right);
  }

  boolean isSymmetric(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null) return false;
    if (right == null) return false;
    return left.val == right.val
      && isSymmetric(left.left, right.right)
      && isSymmetric(left.right, right.left);
  }
}

class TreeNode {
  int val;
  TreeNode left, right;
  TreeNode(int x) {
    this.val = x;
  }
}
