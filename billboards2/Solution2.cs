/**
 * @author ShengMin Zhang
 * @revision 3.0
 * - recursion with memorization
 * @revision 2.0
 * - dynamic programming + segment tree
 * @revision 1.0
 * - greedy
 */
 
 using System;
 using System.IO;
 using System.Collections.Generic;
 
 public class Solution {
	int N, K;
	int[] boards;
	IDictionary<int, long> mem; // mem[i][j] = max sum for ith billboard and when there are jth consecutive billboards
	
	
	long Dfs(int iBoard, int nBoard) {
		if(iBoard > N) return 0L;
		if(mem.ContainsKey(iBoard)) return mem[iBoard];
		
		long without = Dfs(iBoard + 1, 0);
		long with = 0L;
		
		if(nBoard < K) {
			with = boards[iBoard] + Dfs(iBoard + 1, nBoard + 1);
		}
		
		long best = Math.Max(without, with);
		mem[iBoard] = best;
		
		return best;
		
	}
 
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		K = int.Parse(ln[1]);
		boards = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}
		
		mem = new Dictionary<int, long>((int)(N * 1.5)); 
		
		long max = Dfs(1, 0);
		Console.WriteLine(max);
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }