import java.util.*;

public class Solution {

  class Element {
    final int index;
    final int value;

    Element(int index, int value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public String toString() {
      return "[" + index + "] -> " + value;
    }
  }

  <T> void print(T[] array) {
    for (T element: array) {
      System.out.print(element);
      System.out.println();
    }
    System.out.println();
  }

  void print(int[] array) {
    for (int i: array) {
      System.out.print(i);
      System.out.println();
    }
    System.out.println();
  }

  public int candy(int[] ratings) {
    int[] candies = new int[ratings.length];
    Element[] ratingElements = new Element[ratings.length];

    for (int i = 0; i < ratings.length; i++) {
      ratingElements[i] = new Element(i, ratings[i]);
    }

    Arrays.sort(ratingElements, new Comparator<Element>() {
      @Override
      public int compare(Element x, Element y) {
        return Integer.compare(x.value, y.value);
      }
    });

    int count = 0;

    for (Element element: ratingElements) {
      int index = element.index;
      int rating = element.value;
      int leftIndex = index - 1;
      int rightIndex = index + 1;
      int candy = 1;

      if (leftIndex >= 0) {
        int leftCandy = candies[leftIndex];
        int leftRating = ratings[leftIndex];
        if (leftCandy > 0) {
          if (rating > leftRating) {
            candy = leftCandy + 1;
          }
        }
      }

      if (rightIndex < ratingElements.length) {
        int rightCandy = candies[rightIndex];
        int rightRating = ratings[rightIndex];
        if (rightCandy > 0) {
          if (rating > rightRating) {
            candy = Math.max(candy, rightCandy + 1);
          }
        }
      }

      count += candy;
      candies[index] = candy;
    }

    // print(ratingElements);
    // print(candies);

    return count;
  }
}
