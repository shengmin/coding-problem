class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
    next = null;
  }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode it1 = head;
        ListNode it2 = head;

        while (true) {
          if (it2 == null) return false;

          it2 = it2.next;
          if (it2 == null) return false;
          if (it2 == it1) return true;

          it2 = it2.next;
          if (it2 == null) return false;
          if (it2 == it1) return true;

          it1 = it1.next;
        }

    }
}
