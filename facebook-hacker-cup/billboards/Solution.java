/**
 * binary search
 */

import java.io.*;
import java.util.*;

public class Solution {
	int W, H;
	List<String> words;
	
	void printErr(String format, Object ... args) {
		System.err.printf(format, args);
		System.err.println();
	}
	
	boolean isPossible(int fontSize) {
		//printErr("fontSize: %d", fontSize);
		boolean possible = true;
		
		for(int iWord = 0, iCol = 0, iRow = fontSize - 1, nWord = words.size(); iWord < nWord; ){
			if(iRow > H) return false;
			int wordLength = words.get(iWord).length();
			iCol = (iCol == 0) ? wordLength * fontSize : iCol + wordLength * fontSize + fontSize; // first word will not place a space in front
			if(iCol > W) {
				// try to place it on a new row
				iCol = 0;
				iRow += fontSize;
			} else {
				iWord++;
			}
		}
		
		return possible;
	}
	
	int solve() {
		int maxFontSize = Math.min(W, H);
		int minFontSize = 1;
		int fontSize = 0;
		
		while(minFontSize <= maxFontSize) {
			int mid = minFontSize + (maxFontSize - minFontSize) / 2;
			if(isPossible(mid)) {
				fontSize = mid;
				minFontSize = mid + 1;
			} else {
				maxFontSize = mid - 1;
			}
		}
		
		return fontSize;
	}

	void run() throws Exception {
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(rd.readLine());
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			words = new ArrayList<String>();
			while(st.hasMoreTokens()){
				words.add(st.nextToken());
			}
			
			int ans = solve();
			System.out.printf("Case #%d: %d", i, ans);
			System.out.println();
		}
		
		
		rd.close();
	}

	public static void main(String[] args) throws Exception {
		new Solution().run();
	}
}
