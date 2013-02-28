/**
 * @author ShengMin Zhang
 * @revision 3.1
 * - dynamic programming with blocking
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
using System.Linq;
 
 public class Block {
	 public int Start { get; set; }
	 public int End { get; set; }
	 public long Sum { get; set; }

	 public Block(int start, int end, long sum) {
		 this.Start = start;
		 this.End = end;
		 this.Sum = sum;
	 }
 }
 
 public class Solution {
	
	void Run(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		int N = int.Parse(ln[0]);
		int K = int.Parse(ln[1]);

		int[] boards = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			boards[i] = int.Parse(rd.ReadLine());
		}
		
		LinkedList<Block>[] best = new LinkedList<Block>[N + 1];
		LinkedList<Block> firstColumn = new LinkedList<Block>();
		firstColumn.AddFirst(new Block(0, K, 0));
		best[0] = firstColumn;

		for (int j = 1; j <= N; j++) {
			LinkedList<Block> previousColumn = best[j - 1];
			LinkedList<Block> currentColumn = new LinkedList<Block>();
			currentColumn.AddLast(new Block(K, K, previousColumn.Last.Value.Sum));
			long withoutCurrentBoard = previousColumn.Last.Value.Sum;

			foreach (var block in previousColumn) {
				if (block.End == 0) continue;
				long withCurrentBoard = boards[j] + block.Sum;
				long max = Math.Max(withCurrentBoard, withoutCurrentBoard);
				int start = Math.Max(0, block.Start - 1);
				int end = Math.Max(0, block.End - 1);
				var lastBlock = currentColumn.Last.Value;

				if (max == lastBlock.Sum) {
					lastBlock.Start = start;
				} else {
					lastBlock = new Block(start, end, max);
					currentColumn.AddLast(lastBlock);
				}

			}

			best[j] = currentColumn;
		}

		Console.WriteLine(best[N].Last.Value.Sum);
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length > 0) rd = new StreamReader(File.OpenRead(args[0]));
		new Solution().Run(rd);
	}
 }