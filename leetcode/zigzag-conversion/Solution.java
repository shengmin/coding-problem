import java.util.*;

public class Solution {
  public String convert(String text, int rowCount) {
    if (rowCount == 1) return text;

    StringBuilder[] builders = new StringBuilder[rowCount];
    for (int i = 0; i < rowCount; i++) {
      builders[i] = new StringBuilder(text.length() / 2);
    }

    for (int i = 0, iRow = -2; i < text.length();) {
      // Increment first, then decrement
      for (iRow += 2; iRow < rowCount && i < text.length(); i++, iRow++) {
        builders[iRow].append(text.charAt(i));
      }

      for (iRow -= 2; iRow >= 0 && i < text.length(); i++, iRow--) {
        builders[iRow].append(text.charAt(i));
      }
    }

    StringBuilder combiner = new StringBuilder(text.length());
    for (StringBuilder builder: builders) {
      combiner.append(builder.toString());
    }
    return combiner.toString();
  }
}
