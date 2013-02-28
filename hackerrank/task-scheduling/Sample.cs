using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

public class Solution {
	private LinkedList<int> overshoot = new LinkedList<int>();
	private int max = int.MinValue;

	private void ParseLine(string ln, out int D, out int M) {
		string[] parts = ln.Split(' ');
		D = int.Parse(parts[0]);
		M = int.Parse(parts[1]);
	}

	private int FindMax(int D, int M) {
		for (; M > 0; M--) {
			// place each minute at a proper slot so to minimize overshoot
			LinkedListNode<int> slot = overshoot.Last;
			for (int over = overshoot.Count + 1 - D; ; over--) {
				if (slot == null) {
					overshoot.AddFirst(over);
					break;
				}

				if (over - 1 >= slot.Value + 1) {
					// we can move up the current minute
					slot.Value++;
					if (slot.Value > max) {
						max = slot.Value;
					}
				} else {
					overshoot.AddAfter(slot, over);
					if (over > max) {
						max = over;
					}
					break;
				}

				slot = slot.Previous;
			}
		}
		return max;
	}

	private void Run(TextReader rd) {
		int T = int.Parse(rd.ReadLine());
		int D, M;

		//// base case
		//ParseLine(rd.ReadLine(), out D, out M);
		//for (int i = 1; i <= M; i++) {
		//    overshoot.AddLast(max = i - D);
		//}
		//Console.WriteLine(Math.Max(0, max));

		// rest of lines
		for (; T > 0; T--) {
			ParseLine(rd.ReadLine(), out D, out M);
			Console.WriteLine(Math.Max(0, FindMax(D, M)));
		}

		//Console.ReadKey();

	}

	public static void Main(string[] args) {
		//new Solution().Run(args.Length == 0 ? Console.In : new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read)));
		new Solution().Run(Console.In);
	}

}
