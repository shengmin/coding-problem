using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

public class Solution {
	private enum Player {
		Alice,
		Bob
	}

	private IDictionary<string, bool> mem = new Dictionary<string, bool>(100000);

	private bool isIncreasing(string ln) {
		for (int i = ln.Length - 1; i > 0; i--) {
			if (ln[i] < ln[i - 1]) return false;
		}
		return true;
	}

	private Player Solve(string ln, Player currentPlayer, Player otherPlayer) {
		bool canWin;
		if(mem.TryGetValue(ln, out canWin)){
			return canWin ? currentPlayer : otherPlayer;
		}

		if (isIncreasing(ln)) {
			// current player lost
			mem[ln] = false;
			return otherPlayer;
		}

		for (int i = ln.Length - 1; i >= 0; i--) {
			// try all other possiblities
			string newln = ln.Remove(i, 1);
			Player winner = Solve(newln, otherPlayer, currentPlayer);
			if (winner == currentPlayer) {
				//mem[ln] = true;
				return currentPlayer;
			}
		}

		mem[ln] = false;
		return otherPlayer;
	}

	private void Run(TextReader rd) {
		int T = int.Parse(rd.ReadLine());
		for (; T > 0; T--) {
			int N = int.Parse(rd.ReadLine());
			string[] ln = rd.ReadLine().Split(' ');
			char[] chars = new char[N];
			for (int i = 0; i < N; i++) {
				chars[i] = (char)(int.Parse(ln[i]) + 'a');
			}
			Console.WriteLine(Solve(new string(chars), Player.Alice, Player.Bob));
		}
		//Console.ReadKey();
	}

	public static void Main(string[] args) {
		TextReader rd = Console.In;
		if (args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Solution().Run(rd);
	}
}
