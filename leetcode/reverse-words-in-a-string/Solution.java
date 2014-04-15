import java.util.*;

public class Solution {
  public String reverseWords(String text) {
    StringBuilder builder = new StringBuilder(text.length());
    StringBuilder wordBuilder = new StringBuilder();

    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (Character.isWhitespace(c)) {
        if (wordBuilder.length() > 0) {
          // We have a word
          if (builder.length() > 0) {
            // We have some other words in the buffer, insert a space
            wordBuilder.append(' ');
          }
          builder.insert(0, wordBuilder.toString());
          wordBuilder.setLength(0);
        }
      } else {
        wordBuilder.append(c);
      }
    }

    if (wordBuilder.length() > 0) {
      if (builder.length() > 0) {
        wordBuilder.append(' ');
      }
      builder.insert(0, wordBuilder.toString());
    }

    return builder.toString();
  }
}
