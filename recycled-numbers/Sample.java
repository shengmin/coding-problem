/**
 * @author ShengMin Zhang
 * @revision 1.0
 * - brute force naive solution to make it work for the small input size
 */
 
import java.io.*;
import java.util.*;

public class Sample {
	boolean isRecycledPair(int n, int m) {
		String sn = Integer.toString(n);
		String sm = Integer.toString(m);
		
		if(sn.length() != sm.length()) return false;
		
		for(int i = 1; i < sn.length(); i++) {
			
			String newString = sn.substring(i) + sn.substring(0, i);
			if(newString.equals(sm)) return true;
		}
		
		return false;
	}

	int solve (int A, int B) {
		int count = 0;
		for(int n = A; n < B; n++) {
			for(int m = n + 1; m <= B; m++) {
				if(isRecycledPair(n, m)) count++;
			}
		}
		
		return count;
	}

	void run(BufferedReader rd) throws Exception {
		int T = Integer.parseInt(rd.readLine());
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		for(int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			writer.printf("Case #%d: %d", i, solve(A, B));
			writer.println();
		}
		
		rd.close();
		writer.close();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		new Sample().run(rd);
	}
}
