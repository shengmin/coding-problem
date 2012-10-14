/**
 * Maybe I should start writing down my thought process
 * PS: Java-style documentation is more convenient :P
 * 
 * @author ShengMin Zhang
 * @problem Quadrant Queries
 * @revision 1.0
 * A few observations: 
 *	- naive solution would definitely time out on large data set, so need to do partition
 *	- simplify the operation everytime it's being performed, so X X -> nothing, X Y X -> Y
 *	- might need to merge any two adjacent blocks if they have the same transformation
 */

using System;
using System.Collections.Generic;
using System.IO;

public class Sample {
	private enum Transformation {
		None = 0, // no transformation is needed
		X, // reflection about x-axis
		Y, // reflection about y-axis
		XY // reflection about y = x line
	}

	private struct Coordinate {
		public Coordinate(int x, int y): this() {
			this.X = x;
			this.Y = y;
		}

		public int X { get; private set; }
		public int Y { get; private set; }
	}

	private class Block {
		public Block(int start, int end, Transformation trans) {
			this.Start = start;
			this.End = end;
			this.Transformation = trans;
		}
		public Transformation Transformation { get; set; }
		public int Start { get; set; }
		public int End { get; set; }

		public bool Intersect(int start, int end) {
			if (start < this.Start && end >= this.Start) return true;
			if (start >= this.Start && start <= this.End) return true;
			return false;
		}

		public override string ToString() {
			return string.Format("{0} - {1} : {2}", Start, End, Transformation);
		}
	}

	private Transformation[,] transtable = new Transformation[4,4];
	private LinkedList<Block> blocks = new LinkedList<Block>();
	private Coordinate[] coords = null;

	private Transformation Simplify(Transformation t1, Transformation t2) {
		return transtable[(int)t1, (int)t2];
	}

	private void Partition(ref LinkedListNode<Block> node, ref int start, int end, Transformation trans) {
		Block part = node.Value;
		if (start >= part.Start && start <= part.End) {
			if (start != part.Start) {
				Block topPart = new Block(part.Start, start - 1, part.Transformation);
				node.List.AddBefore(node, topPart);
			}

			part.Start = start;

			if (end < part.End) {
				LinkedListNode<Block> bottomPartNode = new LinkedListNode<Block>(new Block(end + 1, part.End, part.Transformation));
				node.List.AddAfter(node, bottomPartNode);
				node = bottomPartNode;
				part.End = end;
			} else {
				start = part.End + 1;
			}

			part.Transformation = Simplify(part.Transformation, trans);

		}
	}

	private int FindQuadrant(Block block, Coordinate coord) {
		int x = coord.X;
		int y = coord.Y;

		switch (block.Transformation) {
			case Transformation.X: 
				y *= -1; 
				break;
			case Transformation.Y: 
				x *= -1;
				break;
			case Transformation.XY:
				x *= -1;
				y *= -1;
				break;
		}

		if (x > 0 && y > 0) return 0;
		else if (x < 0 && y < 0) return 2;
		else if (x > 0) return 3;
		else return 1;

	}

	private void Merge() {
		// merge any two adjacent blocks that have the same transformation
		LinkedListNode<Block> node = blocks.First;
		while (node != null) {
			LinkedListNode<Block> nextNode = node.Next;
			if(nextNode == null) break;
			if (nextNode.Value.Transformation == node.Value.Transformation) {
				node.Value.End = nextNode.Value.End;
				blocks.Remove(nextNode);
			} else {
				node = nextNode;
			}

		}
	}

	private void Solve(string type, int start, int end) {
		LinkedListNode<Block> blockNode = blocks.First;
		while (blockNode != null) {
			// fast forward to the first block that intersects the point
			if (blockNode.Value.Intersect(start, end)) break;
			blockNode = blockNode.Next;
		}

		if (type == "C") {
			int[] count = new int[4];
			for (int i = start; i <= end && blockNode != null; ) {
				if (i <= blockNode.Value.End) {
					// still intersecting
					count[FindQuadrant(blockNode.Value, coords[i++])]++;
				} else {
					blockNode = blockNode.Next;
					continue;
				}
			}

			Console.WriteLine(string.Format("{0} {1} {2} {3}", count[0], count[1], count[2], count[3]));
		} else {
			Transformation trans = type == "X" ? Transformation.X : Transformation.Y;
			for (int i = start; i <= end && blockNode != null; ) {
				Partition(ref blockNode, ref i, end, trans);
				blockNode = blockNode.Next;
			}
			//Merge();
		}
	}

	private void Run(TextReader rd) {
		// fill the transformation table
		for (int i = 0; i < 4; i++) {
			transtable[0, i] = transtable[i, 0] = (Transformation)i;
		}

		for (int i = 0; i < 4; i++) {
			transtable[i, i] = Transformation.None;
		}

		transtable[(int)Transformation.X, (int)Transformation.Y] = transtable[(int)Transformation.Y, (int)Transformation.X] = Transformation.XY;
		transtable[(int)Transformation.XY, (int)Transformation.Y] = transtable[(int)Transformation.Y, (int)Transformation.XY] = Transformation.X;
		transtable[(int)Transformation.XY, (int)Transformation.X] = transtable[(int)Transformation.X, (int)Transformation.XY] = Transformation.Y;

		// start
		int N = int.Parse(rd.ReadLine());
		coords = new Coordinate[N+1];
		for (int i = 1; i <= N; i++) {
			string[] ln = rd.ReadLine().Split(' ');
			coords[i] = new Coordinate(int.Parse(ln[0]), int.Parse(ln[1]));
		}

		blocks.AddFirst(new Block(1, N, Transformation.None));

		int Q = int.Parse(rd.ReadLine());
		for (; Q > 0; Q--) {
			string[] ln = rd.ReadLine().Split(' ');
			Solve(ln[0], int.Parse(ln[1]), int.Parse(ln[2]));
		}

		//Console.ReadKey();
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Sample().Run(rd);
	}
}
