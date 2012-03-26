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
		long[] col = cols[0] = new long[K + 1];
		cols[1] = new long[K + 1];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}
		
		for(int i = K - 1; i >= 0; i--) {
			col[i] = boards[N];
		}
		
		for(int j = N - 1; j > 0; j--) {
			long[] pre = cols[0];
			long[] cur = cols[1];
			cur[K] = pre[0];
			int board = boards[j];
		
			for(int i = K - 1; i >= 0; i--) {
				cur[i] = Math.Max(pre[0], board + pre[i + 1]);
			}
			
			Swap();
		}
	
		Console.WriteLine(cols[0][0]);
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }