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
	IDictionary<long, IDictionary<int, long>> mem; // mem[i][j] = max sum for ith billboard and when there are jth consecutive billboards

	
	long Dfs(int iBoard, long sum) {
		if(iBoard > N) return sum;
		IDictionary<int, long> row;
		mem.TryGetValue(sum, out row);

		if (row != null) {
			if (row.ContainsKey(iBoard)) return row[iBoard];
		} else {
			row = new Dictionary<int, long>();
			mem[sum] = row;
		}

		sum -= boards[iBoard];
		long best = 0L;
		for(int i = iBoard + 1, end = i + K; i <= end; i++) {
			long ans = Dfs(i, sum);
			if(ans > best) best = ans;
		}
		
		row[iBoard] = best;
		return best;
	}

 
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		K = int.Parse(ln[1]);
		boards = new int[N + 1];
		mem = new Dictionary<long, IDictionary<int, long>>();
		long sum = 0L;
		
		for(int i = 1; i <= N; i++) {
			int n = int.Parse(rd.ReadLine());
			sum += n;
			boards[i] = n;
		}
		
		long max = Dfs(0, sum);
		Console.WriteLine(max);
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }