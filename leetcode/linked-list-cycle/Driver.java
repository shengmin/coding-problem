public class Driver {
  public static void main(String[] args) {
    Solution s = new Solution();

    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);

    ListNode[] nodes = new ListNode[] { n1, n2, n3, n4, n5 };

    for (ListNode node: nodes) {
      System.out.println(s.hasCycle(node));
    }

    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;

    for (ListNode node: nodes) {
      System.out.println(s.hasCycle(node));
    }

    n5.next = n1;

    for (ListNode node: nodes) {
      System.out.println(s.hasCycle(node));
    }


    n1.next = n1;
    for (ListNode node: nodes) {
      System.out.println(s.hasCycle(node));
    }
  }
}
