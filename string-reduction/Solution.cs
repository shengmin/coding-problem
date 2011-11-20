using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

public class Solution {
	private char[,] mapping = new char[3, 3];
	private IDictionary<string, int> dp = new Dictionary<string, int>();

	private int dfs(string txt) {
		int dprst;
		if (dp.TryGetValue(txt, out dprst)) {
			return dprst;
		}

		int min = txt.Length;
		for (int i = txt.Length - 1; i > 0; i--) {
			var c1 = txt[i];
			var c2 = txt[i - 1];
			if (c1 != c2) {
				char c = mapping[c1 - 'a', c2 - 'a'];
				string newtxt = txt.Substring(0, i - 1) + c + txt.Substring(i + 1);
				int rst = dfs(newtxt);

				if (rst < min) {
					min = rst;
				}
			}
		}

		if(dp.Count < 10000) dp[txt] = min;

		return min;
	}

	private void run(TextReader rd) {
		int T = int.Parse(rd.ReadLine());
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) continue;
				bool[] taken = new bool[3];
				taken[i] = taken[j] = true;
				for (int k = 0; k < 3; k++) {
					if (!taken[k]) {
						mapping[i, j] = mapping[j, i] = (char)('a' + k);
						break;
					}
				}
			}
		}

		for (; T > 0; T--) {
			string ln = rd.ReadLine();
			int min = dfs(ln);
			Console.WriteLine(string.Format("{0}: {1}", ln, min));
		}

	}

	public static void Main(String[] args) {
		TextReader rd = Console.In;
		//if(args.Length != 0) rd = new StreamReader(new FileStream(args[0], FileMode.Open, FileAccess.Read));
		new Solution().run(rd);
	}
}


