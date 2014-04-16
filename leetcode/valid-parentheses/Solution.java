import java.util.*;

public class Solution {
  public boolean isValid(String text) {
    LinkedList<Character> stack = new LinkedList<Character>();
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      switch (c) {
        case '(':
        case '[':
        case '{':
          stack.push(c);
          break;
        default:
          if (stack.isEmpty()) return false;
          char left = stack.pop();
          char expectedLeft = ' ';
          switch (c) {
            case ']': expectedLeft = '['; break;
            case '}': expectedLeft = '{'; break;
            case ')': expectedLeft = '('; break;
          }
          if (left != expectedLeft) return false;
      }
    }

    return stack.isEmpty();
  }
}
