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
    Set<Element> elements = new LinkedHashSet<Element>();
    int sum = 0;

    Elements add(Element element) {
      elements.add(element);
      sum += element.value;
      return this;
    }

    boolean hasDuplicates(Elements other) {
      for (Element element: other.elements) {
        if (elements.contains(element)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String toString() {
      String text = sum + " = ";
      for (Element element: elements) {
        text += " " + element;
      }
      return text;
    }
  }

  public ArrayList<ArrayList<Integer>> fourSum(int[] nums, int target) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    Map<Integer, List<Elements>> twoSums = new LinkedHashMap<Integer, List<Elements>>();

    for (int i = 0; i < nums.length; i++) {
      int a = nums[i];
      Element aElement = new Element(i, nums[i]);
      for (int j = i + 1; j < nums.length; j++) {
        int b = nums[j];
        int sum = a + b;
        List<Elements> list = twoSums.get(sum);
        if (list == null) {
          list = new ArrayList<Elements>();
        }
        list.add(new Elements()
          .add(aElement)
          .add(new Element(j, b)));
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
              for (Element e: left.elements) {
                newResult.add(e.value);
              }
              for (Element e: right.elements) {
                newResult.add(e.value);
              }
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
