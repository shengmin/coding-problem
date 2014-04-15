import java.util.*;

public class Solution {
  class Element {
    int index, value;
    Element(int index, int value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public int hashCode() {
      return index;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Element) {
        return index == ((Element) other).index;
      }
      return false;
    }

    @Override
    public String toString() {
      return "(" + index + ", " + value + ")";
    }
  }

  class Elements {
    Element first, second;
    int sum;

    Elements(Element first, Element second) {
      this.first = first;
      this.second = second;
      sum = first.value + second.value;
    }

    boolean hasDuplicates(Elements other) {
      return first.equals(other.first)
        || first.equals(other.second)
        || second.equals(other.first)
        || second.equals(other.second);
    }

    @Override
    public String toString() {
      return sum + " = " + first + " " + second;
    }
  }

  public ArrayList<ArrayList<Integer>> fourSum(int[] nums, int target) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    Map<Integer, List<Elements>> twoSums = new HashMap<Integer, List<Elements>>();
    Element[] elements = new Element[nums.length];

    for (int i = 0; i < nums.length; i++) {
      elements[i] = new Element(i, nums[i]);
    }

    for (int i = 0; i < nums.length; i++) {
      int a = nums[i];
      Element aElement = elements[i];
      for (int j = i + 1; j < nums.length; j++) {
        int b = nums[j];
        int sum = a + b;
        List<Elements> list = twoSums.get(sum);
        if (list == null) {
          list = new LinkedList<Elements>();
        }
        list.add(new Elements(aElement, elements[j]));
        twoSums.put(sum, list);
      }
    }

    Set<String> visited = new HashSet<String>();
    StringBuilder builder = new StringBuilder();

    for (Map.Entry<Integer, List<Elements>> entry: twoSums.entrySet()) {
      for (Elements left: entry.getValue()) {
        List<Elements> rightList = twoSums.get(target - left.sum);
        if (rightList != null) {
          for (Elements right: rightList) {
            if (!left.hasDuplicates(right)) {
              ArrayList<Integer> newResult = new ArrayList<Integer>(4);
              newResult.add(left.first.value);
              newResult.add(left.second.value);
              newResult.add(right.first.value);
              newResult.add(right.second.value);
              Collections.sort(newResult);
              builder.setLength(0);
              for (int i: newResult) {
                builder.append(i);
                builder.append(' ');
              }

              String text = builder.toString();
              if (!visited.contains(text)) {
                result.add(newResult);
                visited.add(text);
              }

            }
          }
        }
      }
    }

    return result;
  }
}
