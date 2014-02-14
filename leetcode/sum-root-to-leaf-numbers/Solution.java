public class Solution {
  int sum;

  public int sumNumbers(TreeNode root) {
    sum = 0;
    sumNumbers(root, 0);
    return sum;
  }

  Solution sumNumbers(TreeNode node, int prefix) {
    if (node == null) return this;

    int number = prefix * 10 + node.val;
    if (node.left == null && node.right == null) sum += number;
    else if (node.left == null) sumNumbers(node.right, number);
    else if (node.right == null) sumNumbers(node.left, number);
    else sumNumbers(node.left, number).sumNumbers(node.right, number);
    return this;
  }
}
