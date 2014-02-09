import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.evalRPN(new String[] { "2", "1", "+", "3", "*" }));
    System.out.println(s.evalRPN(new String[] { "4", "13", "5", "/", "+" }));
    System.out.println(s.evalRPN(new String[] { "4","3","-" }));
  }

  public int evalRPN(String[] tokens) {
    LinkedList<Integer> stack = new LinkedList<Integer>();
    for (String token: tokens) {
      if (token.equals("+")) {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a + b);
      } else if (token.equals("-")) {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(b - a);
      } else if (token.equals("*")) {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a * b);
      } else if (token.equals("/")) {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(b / a);
      } else {
        stack.push(Integer.parseInt(token));
      }
    }

    return stack.pop();
  }
}
