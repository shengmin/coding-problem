import java.util.*;

public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    TreeNode n1 = new TreeNode(1);
    TreeNode n2 = new TreeNode(2);
    TreeNode n3 = new TreeNode(3);
    n1.right = n2;
    n2.left = n3;
    print(s.inorderTraversal(n1));
  }

  static void print(List<Integer> list) {
    for (int n: list) {
      System.out.printf("%d ", n);
    }
    System.out.println();
  }

  enum State {
    VisitLeft,
    VisitRight,
    Add
  }

  class Frame {
    TreeNode node;
    State state = State.VisitLeft;

    Frame(TreeNode node) {
      this.node = node;
    }
  }

  public ArrayList<Integer> inorderTraversal(TreeNode root) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    LinkedList<Frame> stack = new LinkedList<Frame>();
    stack.push(new Frame(root));

    while (!stack.isEmpty()) {
      Frame frame = stack.peek();
      TreeNode node = frame.node;

      if (node == null) {
        stack.pop();
        continue;
      }


      switch (frame.state) {
        case VisitLeft:
          frame.state = State.Add;
          stack.push(new Frame(node.left));
          break;
        case VisitRight:
          stack.pop();
          stack.push(new Frame(node.right));
          break;
        case Add:
          frame.state = State.VisitRight;
          list.add(node.val);
          break;
      }
    }

    return list;
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
