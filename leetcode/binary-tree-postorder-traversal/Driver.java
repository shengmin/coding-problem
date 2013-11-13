public class Driver {
  public static void main(String[] args) {
    Solution s = new Solution();
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    TreeNode n4 = new TreeNode(4);

    n1.right = n2;
    n2.left = n3;
    n2.right = n4;

    for (int n: s.postorderTraversal(n1)) {
      System.out.println(n);
    }
  }
}
