public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6}, 5));
    System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6}, 2));
    System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6}, 7));
    System.out.println(s.searchInsert(new int[] { 1, 3, 5, 6}, 0));
  }

  public int searchInsert(int[] A, int target) {
    int index = -1;
    for (int low = 0, high = A.length; low < high;) {
      int midIndex = low + (high - low) / 2;
      int mid = A[midIndex];
      if (mid == target) return midIndex;
      if (target < mid) {
        index = midIndex;
        high = midIndex;
      } else {
        index = midIndex + 1;
        low = midIndex + 1;
      }
    }

    return index;
  }
}
