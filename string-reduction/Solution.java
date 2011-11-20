import java.io.*;
import java.util.*;

public class Solution {
	
	private boolean isSame(String str){
		for(int i = str.length() - 1; i > 0; i--){
			if(str.charAt(i) != str.charAt(i-1)) return false;
		}
		return true;
	}
	
	private int solve(String ln){
		if(isSame(ln)) return ln.length();
		int[] count = new int[3];
		for(int i = ln.length() - 1; i >= 0; i--){
			char c = ln.charAt(i);
			count[c - 'a']++;
		}
		
		if(ln.length() % 2 == 0){
			// even
			for(int i = 0; i < 3; i++){
				if(count[i] % 2 != 0) return 1;
			}
			return 2;
		} else {
			// odd
			for(int i = 0; i < 3; i++){
				if(count[i] % 2 == 0) return 1;
			}
			return 2;
		}
		

	}
	
	private void run(Reader r) throws Exception {
		BufferedReader rd = new BufferedReader(r);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int T = Integer.parseInt(rd.readLine());
		
		for(; T > 0; T--){
			String ln = rd.readLine();
			//pw.printf("%s: %d", ln, solve(ln));
			pw.println(solve(ln));
		}
		
		rd.close();
		pw.close();
	}

	public static void main(String[] args) throws Exception {
		Reader rd = args.length == 0 ? new InputStreamReader(System.in) : new FileReader(args[0]);
		new Solution().run(rd);
	}
}
