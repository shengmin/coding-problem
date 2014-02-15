object Driver {
  def main(a: Array[String]) {
    val s = new Solution
    val n1 = new RandomListNode(1)
    val n2 = new RandomListNode(2)
    val n3 = new RandomListNode(3)

    n1.next = n2
    n2.next = n3
    n3.random = n1
    s.copyRandomList(n1)
    s.copyRandomList(n2)
    s.copyRandomList(n3)
  }
}
