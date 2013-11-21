import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.addBinary("11", "1"));
    System.out.println(s.addBinary("11", "11"));
    System.out.println(s.addBinary("10101", "0"));
  }

  public String addBinary(String A, String B) {
    StringBuilder builder = new StringBuilder();

    int carry = 0;
    for (int i = A.length() - 1, j = B.length() - 1; i >= 0 || j >= 0; i--, j--) {
      int a = i >= 0 ? A.charAt(i) - '0' : 0;
      int b = j >= 0 ? B.charAt(j) - '0' : 0;

      int sum = a + b + carry;
      switch (sum) {
        case 0:
          builder.insert(0, '0');
          carry = 0;
          break;
        case 1:
          builder.insert(0, '1');
          carry = 0;
          break;
        case 2:
          builder.insert(0, '0');
          carry = 1;
          break;
        case 3:
          builder.insert(0, '1');
          carry = 1;
          break;
      }
    }
    if (carry > 0) builder.insert(0, '1');
    return builder.toString();
  }
}
