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
	long[] lastBest;
	int[] bestIndices;

	void Swap() {
		long[] x = best;
		best = lastBest;
		lastBest = x;
	}

	long Sum(int start, int end) {
		long sum = 0L;
		for(int i = start; i <= end && i <= N; i++) {
			sum += boards[i];
		}
		return sum;
	}
	
	long FindMax(int start, int end) {
		long max = -1;
		for(int i = start; i <= end; i++) {
			if (lastBest[i] > max) max = lastBest[i];
		}
		return max;
	}
	
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		K = int.Parse(ln[1]);
		
		boards = new int[N + 1];
		best = new long[K + 1];
		lastBest = new long[K + 1];
		bestIndices = new int[K + 1];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}

		
		for (int j = 0, jj = K + 1; j < N ; j = jj, jj += K + 1) {
			long blockSum = Sum(j + 1, jj);
			int iBoard = jj;
			bestIndices[K] = K;
			best[K] = lastBest[K] + blockSum - ((iBoard <= N) ? boards[iBoard] : 0);

			for(int i = K - 1; i >= 0; i--) {
				iBoard = j + i + 1;
				long localSum = blockSum - ((iBoard <= N) ? boards[iBoard] : 0);
				int localBestIndex = bestIndices[i + 1];
				if (lastBest[i] > lastBest[localBestIndex]) localBestIndex = i;
				best[i] = lastBest[localBestIndex] + localSum;
				bestIndices[i] = localBestIndex;
			}

			Swap();
		}
		Console.WriteLine(FindMax(0, K));
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }