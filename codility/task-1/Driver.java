
class Solution {

  public int solution (String S) {
    int length = S.length();
    // Such index only exists if the length of the string is odd
    if (length % 2 == 0) {
      return -1;
    }

    int middle = length / 2;
    for (int i = middle - 1, j = middle + 1; i >= 0 && j < length; i--, j++) {
      if (S.charAt(i) != S.charAt(j)) {
        return -1;
      }
    }

    return middle;
  }
}

/**
 * @author shengmin
 */
public class Driver {
  public static void main(String[] args) {
    Solution sol = new Solution();
    System.out.println(sol.solution(""));
    System.out.println(sol.solution("a"));
    System.out.println(sol.solution("aa"));
    System.out.println(sol.solution("aba"));
    System.out.println(sol.solution("abc"));
    System.out.println(sol.solution("racecar"));

  }
}
