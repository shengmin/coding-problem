import java.io.*;
import java.util.*;

public class Solution {
	char[] LETTERS = "HACKERUP".toCharArray();

	int solve(String ln) {
		int[] counts = new int[26];
		
		for(int i = ln.length() - 1; i >= 0; i--) {
			char c = ln.charAt(i);
			if(c != ' ') counts[c-'A']++;
		}
		
		counts['C' - 'A'] /= 2; // need two 'C' per other char
		
		int min = Integer.MAX_VALUE;
		for(int i = LETTERS.length - 1; i >= 0; i--) {
			int count = counts[LETTERS[i] - 'A'];
			if(count < min) min = count;
		}
		
		return min;
	}

	void run() throws Exception{
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(rd.readLine());
		
		for(int i = 1; i <= N; i++) {
			int ans = solve(rd.readLine());
			System.out.printf("Case #%d: %d", i, ans);
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		new Solution().run();
	}
}
