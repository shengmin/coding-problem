public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.isPalindrome("1a2"));
    System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println(s.isPalindrome("race a car"));
  }

  boolean isValid(char c) {
    return (c >= '0' && c <= '9')
      || (c >= 'a' && c <= 'z')
      || (c >= 'A' && c <= 'Z');
  }

  public boolean isPalindrome(String text) {
    for (int i = 0, j = text.length() - 1; i <= j;) {
      while (i <= j && !isValid(text.charAt(i))) i++;
      while (i <= j && !isValid(text.charAt(j))) j--;

      if (i <= j) {
        if (Character.toLowerCase(text.charAt(i++)) != Character.toLowerCase(text.charAt(j--))) return false;
      }
    }
    return true;
  }
}
