/**
 * Maybe I should start writing down my thought process
 * PS: Java-style documentation is more convenient :P
 * 
 * @author ShengMin Zhang
 * @problem Quadrant Queries
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

	private sealed class Segment {
		public Segment(int start, int end) {
			this.Start = start;
			this.End = end;
			this.Dirty = false;
			this.Type = QueryType.None;
		}

		private int[] count = new int[4];

		public int[] Count {
			get { return this.count; }
		}
		public QueryType Type { get; set; }
		public bool Dirty { get; set; }
		public int Start { get; private set; }
		public int End { get; private set; }
		public int this[Quadrant q] {
			get { return count[(int)q]; }
			set { count[(int)q] = value; }
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
		}

		public void Transform(QueryType t) {
			if (t == QueryType.X) {
				switch (Type) {
					case QueryType.X: Type = QueryType.None; break;
					case QueryType.Y: Type = QueryType.XY; break;
					case QueryType.XY: Type = QueryType.Y; break;
					case QueryType.None: Type = QueryType.X; break;
				}
			} else if(t == QueryType.Y){
				switch (Type) {
					case QueryType.X: Type = QueryType.XY; break;
					case QueryType.Y: Type = QueryType.None; break;
					case QueryType.XY: Type = QueryType.X; break;
					case QueryType.None: Type = QueryType.Y; break;
				}
			}

		}

		public override string ToString() {
			 return string.Format("{6} : {0} - {1} : {2} {3} {4} {5} : {7}", Start, End, count[0], count[1], count[2], count[3], Type, Dirty);
		}
	}

	

	private sealed class SegmentTree {
		public delegate void Count(int[] count, int index);

		public SegmentTree(int N, Count fn){
			int size = (int)Math.Ceiling(2 * Math.Pow(2, Math.Log(N, 2) + 1));
			segments = new Segment[size];
			this.fn = fn;
			Initialize(0, 1, N);
		}

		private Segment[] segments;
		private Count fn;

		private int GetRightChildIndex(int i){ return 2 * i + 1; }
		private int GetLeftChildIndex(int i){ return 2 * i + 2; }

		public void Update(int start, int end, QueryType type) {
			Update(0, start, end, type);
		}

		private void Update(int index, int start, int end, QueryType type) {
			var seg = segments[index];
			int leftIdx = GetLeftChildIndex(index);
			int rightIdx = GetRightChildIndex(index);
			var left = leftIdx < segments.Length ? segments[leftIdx] : null;
			var right = rightIdx < segments.Length ? segments[rightIdx] : null;

			if (seg.Start == start && seg.End == end) {
				seg.Transform(type);
			}

			if (seg.Dirty || seg.Start == start && seg.End == end) {
				// push update to its children
				seg.Dirty = false;

				if (left != null) {
					left.Dirty = true;
					left.Transform(seg.Type);
				}

				if (right != null) {
					right.Dirty = true;
					right.Transform(seg.Type);
				}
			}

			if (seg.Start == start && seg.End == end) {
				seg.Transform();
				seg.Type = QueryType.None;

				return;
			}

			if (start <= left.End) Update(leftIdx, start, left.End, type);
			if (end >= right.Start) Update(rightIdx, right.Start, end, type);

			for (Quadrant q = Quadrant.First; q <= Quadrant.Forth; q++) {
				seg[q] = left[q] + right[q];
			}

			seg.Type = QueryType.None;
		}

		public int[] Query(int start, int end) {
			int[] count = new int[4];
			Query(0, start, end, count);
			return count;
		}

		private void Query(int index, int start, int end, int[] count) {
			Segment seg = segments[index];
			if (seg.Dirty) {
				Update(index, start, end, QueryType.None);
			}

			if (seg.Start == start && seg.End == end) {
				for (Quadrant q = Quadrant.First; q <= Quadrant.Forth; q++) {
					count[(int)q] += seg[q];
				}
				return;
			}

			int leftIdx = GetLeftChildIndex(index);
			int rightIdx = GetRightChildIndex(index);
			var left = segments[leftIdx];
			var right = segments[rightIdx];

			if (start <= left.End) Query(leftIdx, start, left.End, count);
			if (end >= right.Start) Query(rightIdx, right.Start, end, count);
		}

		private void Initialize(int index, int start, int end){
			Segment seg = new Segment(start, end);
			segments[index] = seg;
			if(start == end){
				fn(seg.Count, start);
				return;
			}

			int leftIdx = GetLeftChildIndex(index);
			int rightIdx = GetRightChildIndex(index);
			Initialize(leftIdx, start, (start + end) / 2);
			Initialize(rightIdx, (start + end) / 2 + 1, end);

			Segment left = segments[leftIdx];
			Segment right = segments[rightIdx];
			for(Quadrant q = Quadrant.First; q <= Quadrant.Forth; q++){
				seg[q] = left[q] + right[q];
			}
		}
	}

	private Coordinate[] points;
	private int N = 0;

	private Quadrant FindQuadrant(Coordinate coord) {
		int x = coord.X;
		int y = coord.Y;

		if (x > 0 && y > 0) return Quadrant.First;
		if (x < 0 && y < 0) return Quadrant.Third;
		if (x > 0) return Quadrant.Forth;

		return Quadrant.Second;
	}

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

	private SegmentTree tree;

	private void Solve(Query query) {
		int start = query.Start;
		int end = query.End;
		QueryType type = query.Type;

		if (type == QueryType.C) {

			int[] count = tree.Query(start, end);
			Console.WriteLine(string.Format("{0} {1} {2} {3}", count[0], count[1], count[2], count[3]));

		} else {
			tree.Update(start, end, type);
		}
	}

	private void Count(int[] count, int index){
		count[(int)FindQuadrant(points[index])] = 1;
	}

	private void Run(TextReader rd) {
		// start
		N = int.Parse(rd.ReadLine());
		points = new Coordinate[N + 1];

		for (int i = 1; i <= N; i++) {
			points[i] = ParseCoordinate(rd);
		}

		tree = new SegmentTree(N, Count);

		int Q = int.Parse(rd.ReadLine());
		for (; Q > 0; Q--) {
			Solve(ParseQuery(rd));
		}
		int a = 0;
		//Console.ReadKey();
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Solution().Run(rd);
	}
}


