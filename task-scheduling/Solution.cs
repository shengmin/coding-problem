using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

public class Solution {
	private class Slot {
		public int Time { get; set; }
		public int Task { get; set; }
		public int Difference { get; set; }

		public Slot(int task, int time, int diff) {
			this.Time = time;
			this.Task = task;
			this.Difference = diff;
		}

		public override string ToString() {
			return string.Format("{0}: Task #{1}, {2} minutes over", Time, Task, Difference);
		}
	}

	private int max = 0;
	private LinkedList<Slot> slots = new LinkedList<Slot>();
	private LinkedListNode<Slot> maxNode = null;
	//private long count = 0;

	private void ParseLine(string ln, out int D, out int M) {
		string[] parts = ln.Split(' ');
		D = int.Parse(parts[0]);
		M = int.Parse(parts[1]);
	}

	private void Clean() {
		// any slots before max slot can be removed
		LinkedListNode<Slot> first = slots.First;
		LinkedListNode<Slot> node = maxNode.Previous;

		while (node != null && node != first) {
			LinkedListNode<Slot> lastNode = node.Previous;
			slots.Remove(node);
			node = lastNode;
		}
	}

	private void PostProcess(LinkedListNode<Slot> slotNode, int diff, int T, int M) {
		Slot slot = slotNode.Value;
		LinkedListNode<Slot> newNode = new LinkedListNode<Slot>(new Slot(T, slot.Time + M, diff));
		slots.AddAfter(slotNode, newNode);
		if (diff > max) {
			max = diff;
			maxNode = newNode;
		}
	}

	private int FindMax(int T, int D, int M) {

		LinkedListNode<Slot> slotNode = slots.Last;

		for (; ; ) {
			LinkedListNode<Slot> lastSlotNode = slotNode.Previous;
			Slot slot = slotNode.Value;
			int diffOfStay = slot.Time + M - D;

			if (lastSlotNode == null) {
				// on first slot
				PostProcess(slotNode, diffOfStay, T, M);
				break;
			}

			Slot lastSlot = lastSlotNode.Value;
			int diffOfMoveUp = lastSlot.Time + M - D;
			int maxOfMoveUp = Math.Max(diffOfMoveUp, slot.Difference + M);
			int maxOfStay = Math.Max(diffOfStay, slot.Difference);

			if (maxOfMoveUp < maxOfStay) {
				// we need to move up current minute task
				slot.Difference += M;
				slot.Time += M;
				if (slot.Difference > max) {
					max = slot.Difference;
					maxNode = slotNode;
				}
				slotNode = lastSlotNode;
			} else {
				PostProcess(slotNode, diffOfStay, T, M);
				break;
			}
		}

		Clean();
		return max;
	}

	private void Print() {
		foreach (var slot in slots) {
			Console.WriteLine(slot);
		}
	}

	private void Run(TextReader rd) {
		int T = int.Parse(rd.ReadLine());
		int D, M;

		slots.AddFirst(new Slot(0, 0, 0));
		maxNode = slots.First;

		for (int i = 1; i <= T; i++) {
			ParseLine(rd.ReadLine(), out D, out M);
			Console.WriteLine(FindMax(i, D, M));
		}
		//Console.WriteLine(count);
		//Print();
		//Console.ReadKey();
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Solution().Run(rd);
	}

}