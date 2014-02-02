import java.util.*;

public class Solution {
  static void print(ArrayList<ArrayList<String>> lists) {
    for (ArrayList<String> list: lists) {
      for (String text: list) {
        System.out.printf("%s ", text);
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    print(s.partition("cdd"));
    print(s.partition("ab"));
    print(s.partition("aa"));
    print(s.partition("aaa"));
    print(s.partition("aaaa"));
    print(s.partition("aab"));
  }

  ArrayList<ArrayList<String>> lists;
  LinkedList<String> workingList;
  String text;

  public ArrayList<ArrayList<String>> partition(String text) {
    lists = new ArrayList<ArrayList<String>>();
    workingList = new LinkedList<String>();
    this.text = text;
    find(0, text.length());
    return lists;
  }

  void find(int start, int end) {
    if (start == end && workingList.size() > 0) {
      lists.add(new ArrayList<String>(workingList));
      return;
    }
    if (end - start == 1) {
      workingList.addLast(text.substring(start, end));
      ArrayList<String> newList = new ArrayList<String>(workingList);
      workingList.removeLast();
      lists.add(newList);
      return;
    }

    for (int i = start + 1; i <= end; i++) {
      if (isPalindrome(start, i)) {
        workingList.addLast(text.substring(start, i));
        find(i, end);
        workingList.removeLast();
      }
    }
  }

  boolean isPalindrome(int start, int end) {
    for (int i = start, j = end - 1; i < j; i++, j--) {
      if (text.charAt(i) != text.charAt(j)) return false;
    }
    return true;
  }
}
