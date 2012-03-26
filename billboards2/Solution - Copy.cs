/**
 * @author ShengMin Zhang
 * @revision 3.0
 * - dynamic programming with optimizations
 * @revision 2.0
 * - dynamic programming
 * @revision 1.0
 * - greedy
 */
 
 using System;
 using System.IO;
 using System.Collections.Generic;
 

 
 public class Solution {
	int N, K;
	int[] boards;
	long[][] cols = new long[2][];
	
	const int FULL = 2;
	const int ZERO = 0;
	const int NOT_FULL = 1;
 
	void Swap() {
		long[] a = cols[0];
		cols[0] = cols[1];
		cols[1] = a;
	}
	
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		K = int.Parse(ln[1]);
		
		boards = new int[N + 1];
		long[] col = cols[0] = new long[3];
		cols[1] = new long[3];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}
		
		for(int c = ZERO; c <= FULL; c++) {
			if(c == FULL) col[c] = 0;
			else col[c] = boards[N];
		}
		
		for(int j = N - 1; j > 0; j--) {
			long[] pre = cols[0];
			long[] cur = cols[1];
			int board = boards[j];
		
			for(int i = FULL; i >= ZERO; i--) {
				switch(i) {
					case ZERO:
						cur[i] = Math.Max(board + pre[NOT_FULL], Math.Max(pre[ZERO], board + pre[FULL]));
						break;
					case NOT_FULL:
						cur[i] = Math.Max(board + pre[FULL], Math.Max(pre[NOT_FULL], board + pre[NOT_FULL]));
						break;
					case FULL:
						cur[i] = pre[ZERO];
						break;
				}
			}
			
			Swap();
		}
	
		Console.WriteLine(cols[0][ZERO]);
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }