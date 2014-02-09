public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);

    n1.next = n5;
    n2.next = n3;
    n3.next = n4;
    print(s.mergeTwoLists(n1, n2));
  }

  private static void print(ListNode list) {
    if (list != null) {
      System.out.printf("%d ", list.val);
      print(list.next);
    } else {
      System.out.println();
    }
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    ListNode list = null;
    ListNode i1 = l1;
    ListNode i2 = l2;

    if (i1.val <= i2.val) {
      list = i1;
      i1 = i1.next;
    } else {
      list = i2;
      i2 = i2.next;
    }

    ListNode i = list;

    while (i1 != null || i2 != null) {
      if (i1 == null) {
        i.next = i2;
        break;
      }

      if (i2 == null) {
        i.next = i1;
        break;
      }

      if (i1.val <= i2.val) {
        ListNode next = i1.next;
        i.next = i1;
        i1 = next;
      } else {
        ListNode next = i2.next;
        i.next = i2;
        i2 = next;
      }

      i = i.next;
    }

    return list;
  }
}

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
  }
}
