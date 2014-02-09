public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(1);
    ListNode n3 = new ListNode(2);
    ListNode n4 = new ListNode(3);
    ListNode n5 = new ListNode(3);


    print(s.deleteDuplicates(new ListNode(1)));

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;

    print(s.deleteDuplicates(n1));
  }

  private static void print(ListNode list) {
    if (list == null) {
      System.out.println("------------");
    } else {
      System.out.printf("%d ", list.val);
      print(list.next);
    }
  }

  public ListNode deleteDuplicates(ListNode head) {
    ListNode list = head;
    ListNode last = head;

    for (ListNode it = head; it != null; it = it.next) {
      if (it.val != last.val) {
        last.next = it;
        last = it;
      }
    }

    if (last != null) last.next = null;
    return list;
  }
}

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    this.val = x;
    this.next = null;
  }
}
