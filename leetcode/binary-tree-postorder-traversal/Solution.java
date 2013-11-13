import java.io.*;
import java.util.*;

enum State {
  VisitLeft, VisitRight, Add
}

class Frame {
  TreeNode node;
  State state = State.VisitLeft;

  Frame(TreeNode node) {
    this.node = node;
  }
}

public class Solution {
  public ArrayList<Integer> postorderTraversal(TreeNode root) {
    LinkedList<Frame> stack = new LinkedList<Frame>();
    ArrayList<Integer> values = new ArrayList<Integer>();

    stack.push(new Frame(root));
    while (stack.size() > 0) {
      Frame frame = stack.peek();
      TreeNode node = frame.node;
      if (node == null) {
        stack.pop();
        continue;
      }

      switch (frame.state) {
        case VisitLeft:
          frame.state = State.VisitRight;
          stack.push(new Frame(node.left));
          break;
        case VisitRight:
          frame.state = State.Add;
          stack.push(new Frame(node.right));
          break;
        default:
          values.add(node.val);
          stack.pop();
      }
    }

    return values;
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
