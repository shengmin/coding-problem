class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);

    n1.next = n2;
    n2.next = n3;

    print(s.insertionSortList(n1));
    print(s.insertionSortList(n3));

    n1.next = null;
    n2.next = n1;
    n3.next = n2;
    print(s.insertionSortList(n3));
  }

  private static void print(ListNode list) {
    if (list != null) {
      System.out.println(list.val);
      print(list.next);
    } else {
      System.out.println("------");
    }
  }

  public ListNode insertionSortList(ListNode head) {
    ListNode node = head;
    ListNode list = null;
    while (node != null) {
      ListNode next = node.next;
      list = insert(list, node);
      node = next;
    }
    return list;
  }

  private ListNode insert(ListNode list, ListNode node) {
    node.next = null;
    if (list == null) {
      return node;
    }

    if (node.val <= list.val) {
      node.next = list;
      return node;
    }

    for (ListNode it = list; ;) {
      ListNode next = it.next;
      if (next == null) {
        it.next = node;
        break;
      }

      if (it.val <= node.val && node.val <= next.val) {
        it.next = node;
        node.next = next;
        break;
      }

      it = next;
    }

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
