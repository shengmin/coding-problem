/**
 * Maybe I should start writing down my thought process
 * PS: Java-style documentation is more convenient :P
 * 
 * @author ShengMin Zhang
 * @problem Quadrant Queries
 * 
 * @revision 3.2
 * - optimization
 * 
 * @revision 3.1
 * - wow, corrected a silly mistake :S
 * 
 * @revision 3.0
 * Special thanks to Hanson Wang who introduced segment tree to me :)
 * - segment tree with lazy propagation
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

	private sealed class Segment {
		public Segment(int start, int end) {
			this.Start = start;
			this.End = end;
			this.Type = QueryType.None;
		}

		private int[] count = new int[4];

		public int[] Count {
			get { return this.count; }
		}
		public QueryType Type { get; set; }
		public int Start { get; private set; }
		public int End { get; private set; }

		public int this[int i] {
			get { return count[i]; }
			set { count[i] = value; }
		}

		private void Swap(Quadrant i, Quadrant j) {
			int x = count[(int)i];
			count[(int)i] = count[(int)j];
			count[(int)j] = x;
		}

		public void Transform() {
			switch (Type) {
				case QueryType.X:
					Swap(Quadrant.First, Quadrant.Forth);
					Swap(Quadrant.Second, Quadrant.Third);
					break;
				case QueryType.Y:
					Swap(Quadrant.First, Quadrant.Second);
					Swap(Quadrant.Third, Quadrant.Forth);
					break;
				case QueryType.XY:
					Swap(Quadrant.First, Quadrant.Third);
					Swap(Quadrant.Second, Quadrant.Forth);
					break;
			}
			Type = QueryType.None;
		}

		private static QueryType[,] tb = new QueryType[4,4];

		static Segment() {
			for (QueryType t1 = QueryType.X; t1 <= QueryType.None; t1++) {
				for (QueryType t2 = QueryType.X; t2 <= QueryType.None; t2++) {
					tb[(int)t1, (int)t2] = Transform(t1, t2);
				}
			}
		}

		public void Transform(QueryType type) {
			Type = tb[(int)type, (int)Type];
		}

		private static QueryType Transform(QueryType t1, QueryType t2) {
			if (t1 == QueryType.X) {
				switch (t2) {
					case QueryType.X: return QueryType.None; 
					case QueryType.Y: return QueryType.XY; 
					case QueryType.XY: return QueryType.Y; 
					case QueryType.None: return QueryType.X; 
				}
			} else if (t1 == QueryType.Y) {
				switch (t2) {
					case QueryType.X: return QueryType.XY; 
					case QueryType.Y: return QueryType.None; 
					case QueryType.XY: return QueryType.X; 
					case QueryType.None: return QueryType.Y; 
				}
			} else if (t1 == QueryType.XY) {
				switch (t2) {
					case QueryType.X: return QueryType.Y; 
					case QueryType.Y: return QueryType.X; 
					case QueryType.XY: return QueryType.None; 
					case QueryType.None: return QueryType.XY; 
				}
			}

			return t2;
		}

		public override string ToString() {
			return string.Format("{0} - {1} : {2} {3} {4} {5} : {6}", Start, End, count[0], count[1], count[2], count[3], Type);
		}
	}

	private sealed class SegmentTree {
		public delegate void Count(int[] count, int index);
		private Segment[] segments;
		private Count fn;

		public SegmentTree(int N, Count fn) {
			segments = new Segment[270000]; // for this particular question, it can't go beyond this size
			this.fn = fn;
			Initialize(0, 1, N);
		}
		private int GetRightChildIndex(int i) { return 2 * i + 1; }
		private int GetLeftChildIndex(int i) { return 2 * i + 2; }

		public void Update(int start, int end, QueryType type) {
			Update(0, start, end, type);
		}

		private void Update(int index, int start, int end, QueryType type) {
			var seg = segments[index];

			if (seg.Start == seg.End) {
				// elementary segment, at leaf
				seg.Transform(type);
				seg.Transform();
				return;
			}

			int leftIdx = GetLeftChildIndex(index);
			int rightIdx = GetRightChildIndex(index);
			var left = segments[leftIdx];
			var right = segments[rightIdx];

			if (seg.Start == start && seg.End == end) {
				// current segment covers the exact range, stops here, push update to both children
				seg.Transform(type);

				left.Transform(seg.Type);
				right.Transform(seg.Type);

				seg.Transform();

				return;
			}

			// propagate the transformation to both children
			left.Transform(seg.Type);
			right.Transform(seg.Type);
			seg.Type = QueryType.None;

			if (start <= left.End) {
				Update(leftIdx, start, Math.Min(end, left.End), type);
			} else {
				Update(leftIdx, left.Start, left.End, QueryType.None);
			}

			if (end >= right.Start) {
				Update(rightIdx, Math.Max(start, right.Start), end, type);
			} else {
				Update(rightIdx, right.Start, right.End, QueryType.None);
			}

			for (int i = 0; i < 4; i++ ) {
				seg[i] = left[i] + right[i];
			}
		}

		public int[] Query(int start, int end) {
			int[] count = new int[4];
			Query(0, start, end, count);
			return count;
		}

		private void Query(int index, int start, int end, int[] count) {
			Segment seg = segments[index];
			if (seg.Type != QueryType.None) {
				Update(index, start, end, QueryType.None);
			}

			if (seg.Start == start && seg.End == end) {
				for (int i = 0; i < 4; i++ ) {
					count[i] += seg[i];
				}
				return;
			}

			int leftIdx = GetLeftChildIndex(index);
			int rightIdx = GetRightChildIndex(index);
			var left = segments[leftIdx];
			var right = segments[rightIdx];

			if (start <= left.End) Query(leftIdx, start, Math.Min(end, left.End), count);
			if (end >= right.Start) Query(rightIdx, Math.Max(start, right.Start), end, count);
		}

		private void Initialize(int index, int start, int end) {
			Segment seg = new Segment(start, end);
			segments[index] = seg;
			if (start == end) {
				fn(seg.Count, start);
				return;
			}

			int leftIdx = GetLeftChildIndex(index);
			int rightIdx = GetRightChildIndex(index);
			Initialize(leftIdx, start, (start + end) / 2);
			Initialize(rightIdx, (start + end) / 2 + 1, end);

			Segment left = segments[leftIdx];
			Segment right = segments[rightIdx];
			for (int i = 0; i < 4; i++ ) {
				seg[i] = left[i] + right[i];
			}
		}
	}

	private Quadrant FindQuadrant(int x, int y) {
		if (x > 0 && y > 0) return Quadrant.First;
		if (x < 0 && y < 0) return Quadrant.Third;
		if (x > 0) return Quadrant.Forth;

		return Quadrant.Second;
	}

	private void Solve(int start, int end, QueryType type) {
		if (type == QueryType.C) {
			int[] count = tree.Query(start, end);
			Console.WriteLine(string.Format("{0} {1} {2} {3}", count[0], count[1], count[2], count[3]));
		} else {
			tree.Update(start, end, type);
		}
	}

	private void Count(int[] count, int index) {
		string[] ln = rd.ReadLine().Split(' ');
		Quadrant q = FindQuadrant(int.Parse(ln[0]), int.Parse(ln[1]));
		count[(int)q] = 1;
	}

	private TextReader rd;
	private SegmentTree tree;
	private int N = 0;

	private void Run(TextReader rd) {
		N = int.Parse(rd.ReadLine());
		this.rd = rd;
		tree = new SegmentTree(N, Count);

		int Q = int.Parse(rd.ReadLine());
		for (; Q > 0; Q--) {
			string[] ln = rd.ReadLine().Split(' ');
			var t = ln[0];
			QueryType type = QueryType.C;
			if (t == "X") type = QueryType.X;
			else if (t == "Y") type = QueryType.Y;
			Solve(int.Parse(ln[1]), int.Parse(ln[2]), type);
		}
		//Console.ReadKey();
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Solution().Run(rd);
	}
}

