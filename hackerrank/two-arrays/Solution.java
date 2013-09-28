import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author shengmin
 */
public class Solution {
  boolean solve(int[] A, int[] B, int K) {
    Arrays.sort(A);
    Arrays.sort(B);
    int pivot = B.length;
    int capacity = 0;

    for (int i = 0; i < A.length; i++) {
      int a = A[i];
      int newPivot = binarySearch(B, a, K, 0, pivot);
      if (newPivot == -1) {
        if (--capacity < 0) {
          return false;
        }
      } else {
        capacity += pivot - newPivot - 1;
        pivot = newPivot;
      }
    }

    return true;
  }

  int binarySearch(int[] B, int a, int K, int start, int end) {
    int answer = -1;
    while (start < end) {
      int midIndex = start + (end - start) / 2;
      int mid = B[midIndex];
      if (mid + a >= K) {
        answer = midIndex;
        end = midIndex;
      } else {
        start = midIndex + 1;
      }
    }
    return answer;
  }

  void run(BufferedReader rd) throws Exception {
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int T = Integer.parseInt(rd.readLine());
    for (int i = 0; i < T; i++) {
      StringTokenizer st = new StringTokenizer(rd.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[] A = new int[N];
      int[] B = new int[N];
      st = new StringTokenizer(rd.readLine());
      for (int j = 0; j < N; j++) {
        A[j] = Integer.parseInt(st.nextToken());
      }
      st = new StringTokenizer(rd.readLine());
      for (int j = 0; j < N; j++) {
        B[j] = Integer.parseInt(st.nextToken());
      }

      pw.println(solve(A, B, K) ? "YES" : "NO");
    }

    rd.close();
    pw.close();
  }

  public static void main(String[] args) throws Exception {
    BufferedReader rd = args.length > 0
        ? new BufferedReader(new FileReader(args[0]))
        : new BufferedReader(new InputStreamReader(System.in));
    new Solution().run(rd);
  }
}
