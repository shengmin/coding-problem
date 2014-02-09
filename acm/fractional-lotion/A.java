import java.io.*;
import java.util.*;

public class A {

  public static void main(String[] args) throws Exception {
    BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    for (String ln = rd.readLine(); ln != null; ln = rd.readLine()) {
      int n = Integer.parseInt(ln.substring(ln.indexOf('/') + 1));
      int count = 0;
      int upper = n * 2;

      for (int x = 1; x <= upper; x++) {
        int top = n * x;
        int bottom = x - n;
        if (bottom == 0) continue;
        if (top % bottom == 0 && (top / bottom > 0)) count++;
      }

      pw.println(count);
    }

    pw.flush();
    pw.close();
    rd.close();
  }
}
