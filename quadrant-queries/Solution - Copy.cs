/**
 * Maybe I should start writing down my thought process
 * PS: Java-style documentation is more convenient :P
 * 
 * @author ShengMin Zhang
 * @problem Quadrant Queries
 * 
 * @revision 2.0
 * - dp and each block tracks which column is corresponding to which quadrant
 * 
 * @revision 1.2
 * - new solution didn't work out, perform pretty bad on sparse data set, revert back to first solution with merge enabled
 * 
 * @revision 1.1
 * - should group the block by quadrant instead of type of transformation, which is much faster for C query
 * 
 * @revision 1.0
 * A few observations: 
 *	- naive solution would definitely time out on large data set, so need to do partition
 *	- simplify the operation everytime it's being performed, so X X -> nothing, X Y X -> Y
 *	- might need to merge any two adjacent blocks if they have the same transformation
 */

using System;
using System.Collections.Generic;
using System.IO;

public class Solution {
	private enum Quadrant {
		First = 0,
		Second,
		Third,
		Forth
	}

	private enum QueryType {
		X = 0, // reflection about x-axis
		Y, // reflection about y-axis,
		XY,
		None,
		C
	}

	private struct Coordinate {
		public Coordinate(int x, int y)
			: this() {
			this.X = x;
			this.Y = y;
		}

		public int X { get; private set; }
		public int Y { get; private set; }
	}

	private struct Query {
		public Query(QueryType type, int start, int end) : this() {
			this.Type = type;
			this.Start = start;
			this.End = end;
		}

		public QueryType Type { get; private set; }
		public int Start { get; private set; }
		public int End { get; private set; }
	}

	private class Block {
		public Block(int start, int end)
			: this(start, end, new Quadrant[] { Quadrant.First, Quadrant.Second, Quadrant.Third, Quadrant.Forth }, QueryType.None) {

		}

		public Block(int start, int end, Quadrant[] quads, QueryType type) {
			this.Start = start;
			this.End = end;
			this.Type = type;
			mapping = quads;
		}

		private Quadrant[] mapping;

		public QueryType Type { get; private set; }
		public int Start { get; set; }
		public int End { get; set; }
		public Quadrant[] CloneMapping() {
			Quadrant[] clone = new Quadrant[4];
			mapping.CopyTo(clone, 0);
			return clone;
		}


		private void Swap(Quadrant i, Quadrant j) {
			Quadrant x = mapping[(int)i];
			mapping[(int)i] = mapping[(int)j];
			mapping[(int)j] = x;
		}

		public void Transform(QueryType t) {
			if (t == QueryType.X) {
				// 1 <-> 4, 2 <-> 3
				Swap(Quadrant.First, Quadrant.Forth);
				Swap(Quadrant.Second, Quadrant.Third);

				switch (Type) {
					case QueryType.X: Type = QueryType.None; break;
					case QueryType.Y: Type = QueryType.XY; break;
					case QueryType.XY: Type = QueryType.Y; break;
					case QueryType.None: Type = QueryType.X; break;
				}

			} else {
				// 1 <-> 2, 3 <-> 4
				Swap(Quadrant.First, Quadrant.Second);
				Swap(Quadrant.Third, Quadrant.Forth);

				switch (Type) {
					case QueryType.X: Type = QueryType.XY; break;
					case QueryType.Y: Type = QueryType.None; break;
					case QueryType.XY: Type = QueryType.X; break;
					case QueryType.None: Type = QueryType.Y; break;
				}
			}

		}

		public Quadrant FindQuadrant(Quadrant quad) {
			for (int i = 3; i >= 0; i--) {
				if (mapping[i] == quad) return mapping[i];
			}
			return 0;
		}

		public Quadrant Map(int i) {
			return mapping[i];
		}

		public override string ToString() {
			return string.Format("{0} - {1}: {2} {3} {4} {5}", Start, End, mapping[0], mapping[1], mapping[2], mapping[3]);
		}
	}

	private LinkedList<Block> blocks = new LinkedList<Block>();
	//private Quadrant[,] tb = new Quadrant[4, 2];
	private int[,] dp; // dp[i, j] = cummulative frequency from 1 to i for quadrant j + 1
	private Coordinate[] points;
	private int N = 0;

	private void Partition(ref LinkedListNode<Block> node, ref int start, int end, QueryType trans) {
		Block block = node.Value;
		if (start > block.Start) {
			Block topBlock = new Block(block.Start, start - 1, block.CloneMapping(), block.Type);
			block.Start = start;
			node.List.AddBefore(node, topBlock);
		}

		if (end < block.End) {
			Block bottomBlock = new Block(end + 1, block.End, block.CloneMapping(), block.Type);
			block.End = end;
			node = node.List.AddAfter(node, bottomBlock);
		}

		block.Transform(trans);
		start = node.Value.End + 1;
	}

	private Quadrant FindQuadrant(Coordinate coord) {
		int x = coord.X;
		int y = coord.Y;

		if (x > 0 && y > 0) return Quadrant.First;
		if (x < 0 && y < 0) return Quadrant.Third;
		if (x > 0) return Quadrant.Forth;

		return Quadrant.Second;
	}

	private Quadrant FindQuadrant(Coordinate coord, QueryType type) {
		int x = coord.X;
		int y = coord.Y;

		switch (type) {
			case QueryType.X:
				y *= -1;
				break;
			case QueryType.Y:
				x *= -1;
				break;
			case QueryType.XY:
				x *= -1;
				y *= -1;
				break;
		}

		return FindQuadrant(new Coordinate(x, y));
	}


	//private void Merge() {
	//    // merge any two adjacent blocks that have the same transformation
	//    LinkedListNode<Block> node = blocks.First;
	//    while (node != null) {
	//        LinkedListNode<Block> nextNode = node.Next;
	//        if(nextNode == null) break;
	//        if (nextNode.Value.Transformation == node.Value.Transformation) {
	//            node.Value.End = nextNode.Value.End;
	//            blocks.Remove(nextNode);
	//        } else {
	//            node = nextNode;
	//        }

	//    }
	//}

	private Query ParseQuery(TextReader rd) {
		string[] ln = rd.ReadLine().Split(' ');
		string t = ln[0];
		QueryType type = QueryType.C;
		if (t == "X") type = QueryType.X;
		else if (t == "Y") type = QueryType.Y;

		return new Query(type, int.Parse(ln[1]), int.Parse(ln[2]));
	}

	private Coordinate ParseCoordinate(TextReader rd) {
		string[] parts = rd.ReadLine().Split(' ');
		return new Coordinate(int.Parse(parts[0]), int.Parse(parts[1]));
	}

	private void Solve(Query query) {
		int start = query.Start;
		int end = query.End;
		QueryType type = query.Type;

		LinkedListNode<Block> blockNode = null;
		if (start <= N) {
			blockNode = blocks.First;
			for (; blockNode != null; blockNode = blockNode.Next) {
				// fast forward to the first block that intersects the point
				if (start >= blockNode.Value.Start && start <= blockNode.Value.End) break;
			}
		} else {
			for (blockNode = blocks.Last; blockNode != null; blockNode = blockNode.Next) {
				if (start >= blockNode.Value.Start && start <= blockNode.Value.End) break;
			}
		}

		if (type == QueryType.C) {
			int[] count = new int[4];
			for (int i = start; i <= end && blockNode != null; blockNode = blockNode.Next) {
				var block = blockNode.Value;
				int startRow = i - 1;
				if (i == block.Start) {
					count[(int)FindQuadrant(points[i], block.Type)]++;
					startRow = i;
				}
				int endRow = Math.Min(end, block.End);
				for (int j = 0; j < 4; j++ ) {
					var q = block.Map(j);
					count[(int)q] += Math.Max(0, dp[endRow, j] - dp[startRow, j]);
				}
				i = block.End + 1;
			}

			Console.WriteLine(string.Format("{0} {1} {2} {3}", count[0], count[1], count[2], count[3]));

		} else {
			for (int i = start; i <= end && blockNode != null; blockNode = blockNode.Next) {
				Partition(ref blockNode, ref i, end, type);
			}
			//Merge();
		}
	}

	private void Run(TextReader rd) {
		// fill the transformation table
		//tb[(int)Quadrant.First, (int)QueryType.X] = tb[(int)Quadrant.Third, (int)QueryType.Y] = Quadrant.Forth;
		//tb[(int)Quadrant.Second, (int)QueryType.X] = tb[(int)Quadrant.Forth, (int)QueryType.Y] = Quadrant.Third;
		//tb[(int)Quadrant.Third, (int)QueryType.X] = tb[(int)Quadrant.First, (int)QueryType.Y] = Quadrant.Second;
		//tb[(int)Quadrant.Forth, (int)QueryType.X] = tb[(int)Quadrant.Second, (int)QueryType.Y] = Quadrant.First;

		// start
		N = int.Parse(rd.ReadLine());
		dp = new int[N + 1, 4];
		points = new Coordinate[N + 1];
		blocks.AddFirst(new Block(1, N)); // initially it's one entire block

		for (int i = 1; i <= N; i++) {
			var coord = ParseCoordinate(rd);
			Quadrant currentQuad = FindQuadrant(coord);
			points[i] = coord;
			for (Quadrant q = Quadrant.First; q <= Quadrant.Forth; q++) {
				int qint = (int)q;
				if (q == currentQuad) dp[i, qint] = dp[i - 1, qint] + 1;
				else dp[i, qint] = dp[i - 1, qint];
			}
		}

		int Q = int.Parse(rd.ReadLine());
		for (; Q > 0; Q--) {
			Solve(ParseQuery(rd));
		}

		//Console.ReadKey();
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Solution().Run(rd);
	}
}


