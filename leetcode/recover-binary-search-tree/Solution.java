public class Solution {

  class Tuple {
    final TreeNode node1;
    final TreeNode node2;

    Tuple(TreeNode node1, TreeNode node2) {
      this.node1 = node1;
      this.node2 = node2;
    }
  }

  public void recoverTree(TreeNode root) {
    Tuple wrongNodes = find(root, null, null);
    assert(wrongNodes != null);
    recover(root, root, wrongNodes);
  }

  boolean recover(TreeNode root, TreeNode node, Tuple wrongNodes) {
    if (node == null) {
      return false;
    }

    TreeNode node1 = wrongNodes.node1;
    TreeNode node2 = wrongNodes.node2;
    int oldNodeValue = node.val;
    int oldNode1Value = node1.val;
    int oldNode2Value = node2.val;

    // Swap node and node1
    if (node != node1) {
      node.val = oldNode1Value;
      node1.val = oldNodeValue;
      if (isBinarySearchTree(root)) {
        return true;
      }
      node1.val = oldNode1Value;
      node.val = oldNodeValue;
    }

    // Swap node and node2
    if (node != node2) {
      node.val = oldNode2Value;
      node2.val = oldNodeValue;
      if (isBinarySearchTree(root)) {
        return true;
      }
      node2.val = oldNode2Value;
      node.val = oldNodeValue;
    }

    return recover(root, node.left, wrongNodes) || recover(root, node.right, wrongNodes);
  }

  Tuple find(TreeNode node, TreeNode lower, TreeNode upper) {
    if (node == null) {
      return null;
    }

    if (lower != null) {
      if (node.val < lower.val) {
        // Current node or the lower bound node is wrong
        return new Tuple(node, lower);
      }
    }

    if (upper != null) {
      if (node.val > upper.val) {
        // Current node or the upper bound node is wrong
        return new Tuple(node, upper);
      }
    }

    Tuple leftResult = find(node.left, lower, node);
    if (leftResult != null) {
      return leftResult;
    }

    return find(node.right, node, upper);
  }

  boolean isBinarySearchTree(TreeNode node) {
    return isBinarySearchTree(node, null, null);
  }

  boolean isBinarySearchTree(TreeNode node, TreeNode lower, TreeNode upper) {
    if (node == null) {
      return true;
    }

    if (lower != null) {
      if (node.val < lower.val) {
        return false;
      }
    }

    if (upper != null) {
      if (node.val > upper.val) {
        return false;
      }
    }

    return isBinarySearchTree(node.left, lower, node) && isBinarySearchTree(node.right, node, upper);
  }
}
