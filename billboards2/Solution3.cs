/**
 * @author ShengMin Zhang
 * @revision 2.0
 * - dynamic programming + segment tree
 * @revision 1.0
 * - greedy
 */
 
 using System;
 using System.IO;
 using System.Collections.Generic;
 
 
 
 public struct Cell {
	public int Count; // number of consecutive billboards
	public long Sum; // max sum so far
	
	public Cell(int c, long s) {
		Count = c;
		Sum = s;
	}
 }
 
 public class Solution {
	int N, K;
	int[] boards;
	Cell[] dp;
 
	KeyValuePair<int, int> findMin(int start, int end) {
		// key is the index of min
		// value is the actual min value
		int min = int.MaxValue;
		int minIndex = -1;
		
		for(int i = start; i <= end; i++) {
			if(boards[i] <= min) {
				min = boards[i];
				minIndex = i;
			}
		}
		
		return new KeyValuePair<int, int>(minIndex, min);
	}
 
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		N = int.Parse(ln[0]);
		K = int.Parse(ln[1]);
		
		boards = new int[N + 1];
		dp = new Cell[N + 1];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}
		
		for(int i = 1; i <= N; i++) {
			Cell pre = dp[i - 1];
			if(pre.Count < K) {
				dp[i] = new Cell(pre.Count + 1, pre.Sum + boards[i]);
			} else {
				var min = findMin(i - K, i);
				dp[i] = new Cell(i - min.Key, pre.Sum + boards[i] - boards[min.Key]);
			}
		}
		
		Console.WriteLine(dp[N].Sum);
	}
 
	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }