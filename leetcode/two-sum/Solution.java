import java.util.*;

public class Solution {
  class Element {
    int index, value;
    public Element(int index, int value) {
      this.index = index;
      this.value = value;
    }
  }

  public int[] twoSum(int[] numbers, int target) {
    Map<Integer, Element> values = new HashMap<Integer, Element>();
    int[] answer = new int[0];
    for (int i = 0; i < numbers.length; i++) {
      int number = numbers[i];
      Element other = values.get(target - number);
      if (other != null) {
        answer = new int[] { i + 1, other.index };
        break;
      }
      values.put(number, new Element(i + 1, number));
    }

    Arrays.sort(answer);
    return answer;
  }

  public static void main(String[] _) {
    Solution s = new Solution();
    print(s.twoSum(new int[] { 2, 7, 11, 15 }, 9));
  }

  static void print(int[] ints) {
    for (int i: ints) {
      System.out.print(i);
      System.out.print(' ');
    }
    System.out.println();
  }
}
