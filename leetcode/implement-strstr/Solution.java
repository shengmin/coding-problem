public class Solution {

  public String strStr(String text, String pattern) {
    if (text == null || pattern == null) return null;
    if (text.length() < pattern.length()) return null;

LOOP:
    for (int i = 0, end = text.length() - pattern.length(); i <= end; i++) {
      for (int j = i, k = 0; k < pattern.length(); j++, k++) {
        if (text.charAt(j) != pattern.charAt(k)) continue LOOP;
      }
      return text.substring(i);
    }

    return null;
  }
}
