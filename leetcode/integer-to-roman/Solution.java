import java.util.*;

public class Solution {
  void append(StringBuilder builder, int count, char c) {
    for (int i = 0; i < count; i++) {
      builder.append(c);
    }
  }

  public String intToRoman(int n) {
    StringBuilder builder = new StringBuilder();

    while (n > 0) {
      if (n >= 1000) {
        append(builder, n / 1000, 'M');
        n = n % 1000;
      } else if (n >= 900) {
        builder.append("CM");
        n = n % 900;
      } else if (n >= 500) {
        append(builder, n / 500, 'D');
        n = n % 500;
      } else if (n >= 400) {
        builder.append("CD");
        n = n % 400;
      } else if (n >= 100) {
        append(builder, n / 100, 'C');
        n = n % 100;
      } else if (n >= 90) {
        builder.append("XC");
        n = n % 90;
      } else if (n >= 50) {
        append(builder, n / 50, 'L');
        n = n % 50;
      } else if (n >= 40) {
        builder.append("XL");
        n = n % 40;
      } else if (n >= 10) {
        append(builder, n / 10, 'X');
        n = n % 10;
      } else if (n >= 9) {
        builder.append("IX");
        n = n % 9;
      } else if (n >= 5) {
        append(builder, n / 5, 'V');
        n = n % 5;
      } else if (n >= 4) {
        builder.append("IV");
        n = n % 4;
      } else if (n >= 1) {
        append(builder, n, 'I');
        n = 0;
      }
    }

    return builder.toString();
  }
}
