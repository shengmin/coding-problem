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
	long[] best;
	
	long Sum(int start, int end) {
		long sum = 0L;
		for(int i = start; i <= end && i <= N; i++) sum += boards[i];
		return sum;
	}
	
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		K = int.Parse(ln[1]);
		
		boards = new int[N + 1];
		best = new long[K + 1];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}

		
		for (int j = K + 1; ; j += K + 1) {
			long localSum = Sum(j - K, j);

			for (int i = K; i >= 0; i--) {
				long localBest = (i < K) ? best[i + 1] : int.MinValue;
				long partialSum = localSum;
				for (int k = i; k >= 0; k--) {
					int iBoard = j - K + k;
					partialSum -= ((iBoard <= N) ? boards[iBoard] : 0);
					long sum = partialSum + best[k];
					if (sum > localBest) localBest = sum;
				}
				best[i] = localBest;
			}

			if (j >= N) break;
		}
		
	
		Console.WriteLine(best[0]);
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }