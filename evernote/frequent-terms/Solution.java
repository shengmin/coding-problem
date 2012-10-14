/**
 * @author ShengMin Zhang
 * @problem Frequent Terms
 * @revision 1.0
 * - Use Map to store the count
 * - Add the terms to a priority queue
 * - Pop the queue K times
 */
 
import java.util.*;
import java.io.*;

public class Solution {
  static class TermCount {
    String term;
    int count;
    
    TermCount(String term, int count) {
      this.term = term;
      this.count = count;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(rd.readLine());
    Map<String, Integer> terms = new LinkedHashMap<String, Integer>(N * 2);
    
    for(int i = 0; i < N; i++) {
      String term = rd.readLine();
      Integer count = terms.get(term);
      count = (count == null) ? 1 : count + 1;
      terms.put(term, count);
    }
    
    int K = Integer.parseInt(rd.readLine());
    rd.close();
    
    Queue<TermCount> sortedTerms = new PriorityQueue<TermCount>(K, new Comparator<TermCount>() {
      @Override
      public int compare(TermCount x, TermCount y) {
        return (x.count == y.count) ? x.term.compareTo(y.term) : y.count - x.count;
      }
    });
    
    for(Map.Entry<String, Integer> entry: terms.entrySet()) {
      sortedTerms.add(new TermCount(entry.getKey(), entry.getValue()));
    }
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    for(int i = 0; i < K; i++) {
      pw.println(sortedTerms.poll().term);
    }
    pw.close();
  }
}