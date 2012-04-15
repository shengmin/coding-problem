/**
 * @author ShengMin Zhang
 * @revision 2.0
 * - for any given n and B, count how many m's are there
 * @revision 1.0
 * - brute force naive solution to make it work for the small input size
 */
 
import java.io.*;
import java.util.*;

public class C {
	int countMs(int n, int B, int multiple) {
		int count = 0;
		
		for(int m = n;;) {
			m = (m / 10) + (m % 10) * multiple;
			
			if(m <= B && m > n) count++;
			if(m == n) break;
		}
		
		return count;
	}
	
	int solve(int A, int B) {
		int count = 0;
		int multiple = 1;
		
		for(; multiple <= A; multiple *= 10) ;
		
		multiple /= 10;
		
		for(int n = A; n < B; n++) {
			if(n / multiple >= 10) multiple *= 10;
			count += countMs(n, B, multiple);
		}
		return count;
	}
		
	void run(BufferedReader rd) throws Exception {
		int T = Integer.parseInt(rd.readLine());
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// compute the sum table
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
		new C().run(rd);
	}
}
