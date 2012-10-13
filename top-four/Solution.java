/**
 * @author ShengMin Zhang
 * @problem Top Four
 * @revision 1.0
 * - Keep top four in a priority queue
 * - For each element e in the list, add e to the queue
 * - Pop the smallest from the queue
 * - Running time is O(n + nlog(4)) = O(n)
 */
 
import java.io.*;
import java.util.*;

public class Solution {
  static final int TOP = 4;
  
  static void print(Queue<Integer> queue) {
    Integer head = queue.poll();
    if(head != null) {
      print(queue);
      System.out.println(head);
    }
  }
  
  public static void main(String[] args) throws Exception {
    BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(rd.readLine());
    Queue<Integer> topFour = new PriorityQueue<Integer>();
    int i = 0;
    
    for(; i < TOP; i++) {
      topFour.add(Integer.parseInt(rd.readLine()));
    }
    
    for(; i < N; i++) {
      topFour.add(Integer.parseInt(rd.readLine()));
      topFour.poll();
    }
    rd.close();
    print(topFour);
  }
}
