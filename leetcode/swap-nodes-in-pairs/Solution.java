import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();

    print(s.swapPairs(newList(4)));
    print(s.swapPairs(newList(3)));
  }

  private static ListNode newList(int n) {
    ListNode list = new ListNode(1);
    ListNode last = list;
    for (int i = 2; i <= n; i++) {
      ListNode node = new ListNode(i);
      last.next = node;
      last = node;
    }
    return list;
  }

  private static void print(ListNode list) {
    for (ListNode node = list; node != null; node = node.next) {
      System.out.printf("%d ", node.val);
    }
    System.out.println();
  }

  public ListNode swapPairs(ListNode head) {
    if (head == null) return null;

    ListNode next = head.next;
    if (next == null) return head;

    ListNode nextNext = next.next;

    next.next = head;
    head.next = swapPairs(nextNext);

    return next;
  }
}

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
  }
}
