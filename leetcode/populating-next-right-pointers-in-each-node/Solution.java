public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    TreeLinkNode n1 = new TreeLinkNode(1);
    TreeLinkNode n2 = new TreeLinkNode(2);
    TreeLinkNode n3 = new TreeLinkNode(3);
    TreeLinkNode n4 = new TreeLinkNode(4);
    TreeLinkNode n5 = new TreeLinkNode(5);
    TreeLinkNode n6 = new TreeLinkNode(6);
    TreeLinkNode n7 = new TreeLinkNode(7);

    n1.left = n2;
    n1.right = n3;
    n2.left = n4;
    n2.right = n5;
    n3.left = n6;
    n3.right = n7;

    s.connect(n1);
    print(n1);
  }

  private static void print(TreeLinkNode node) {
    if (node == null) return;
    System.out.printf("%d->%d\n", node.val, node.next == null ? 0 : node.next.val);
    print(node.left);
    print(node.right);
  }

  public void connect(TreeLinkNode root) {
    if (root == null) return;

    TreeLinkNode left = root.left;
    TreeLinkNode right = root.right;
    connect(left);
    connect(right);
    connect(left, right);
    if (left != null && right != null) {
      left.next = right;
    }
  }

  private void connect(TreeLinkNode left, TreeLinkNode right) {
    if (left == null || right == null) return;
    left.next = right;
    connect(left.right, right.left);
  }
}

class TreeLinkNode {
  int val;
  TreeLinkNode left, right, next;
  TreeLinkNode(int x) {
    this.val = x;
  }
}
