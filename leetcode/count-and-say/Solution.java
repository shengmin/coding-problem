public class Solution {
  public String countAndSay(int n) {
    String last = "1";
    if (n == 1) return last;

    StringBuilder builder = new StringBuilder();

    for (int i = 2; i <= n; i++) {
      builder.setLength(0);
      char lastChar = last.charAt(0);
      int count = 1;
      for (int j = 1; j < last.length(); j++) {
        char c = last.charAt(j);
        if (c == lastChar) {
          count++;
        } else {
          builder.append(count);
          builder.append(lastChar);
          lastChar = c;
          count = 1;
        }
      }

      if (count > 0) {
        builder.append(count);
        builder.append(lastChar);
      }

      last = builder.toString();
    }

    return last;
  }
}
