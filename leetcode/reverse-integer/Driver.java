public class Driver {
  public static void main(String[] args) {
    Solution s = new Solution();

    for (int i = -20; i <= 20; i++) {
      int reversed = s.reverse(i);
      System.out.printf("%d %d\n", i, reversed);
    }
  }
}
