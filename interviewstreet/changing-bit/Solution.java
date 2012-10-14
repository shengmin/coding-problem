/**
 * @author ShengMin Zhang
 * @revision 1.0
 * - Java BigInteger
 */
 
 import java.io.*;
 import java.util.*;
 import java.math.*;
 
 public class Solution {
	int N, Q;
	BigInteger A, B;
	
	BigInteger setBit(BigInteger i, int idx, String x) {
		if(x.equals("0")) {
			return i.clearBit(idx);
		} else {
			return i.setBit(idx);
		}
	}
	
	void run(BufferedReader rd) throws Exception {
		StringTokenizer st = new StringTokenizer(rd.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		A = new BigInteger(rd.readLine(), 2);
		B = new BigInteger(rd.readLine(), 2);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		
		for(int i = Q; i > 0; i--) {
			st = new StringTokenizer(rd.readLine());
			String type = st.nextToken();
			if(type.equals("set_a")) {
				int idx = Integer.parseInt(st.nextToken());
				String x = st.nextToken();
				A = setBit(A, idx, x);
			} else if(type.equals("set_b")) {
				int idx = Integer.parseInt(st.nextToken());
				String x = st.nextToken();
				B = setBit(B, idx, x);
			} else {
				int idx = Integer.parseInt(st.nextToken());
				BigInteger C = A.add(B);
				writer.print(C.testBit(idx) ? '1' : '0');
			}
		}
		
		writer.println();
		writer.close();
		rd.close();
	}
 
	public static void main(String[] args) throws Exception {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		new Solution().run(rd);
	}
 }
 