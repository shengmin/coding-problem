using System;

public class Solution {
	public static void Main(string[] args){
		Random rand = new Random();
		int N = int.Parse(args[0]);
		int Q = int.Parse(args[1]);
		
		Console.WriteLine(N);
		for(int i = 0; i < N;){
			int x = rand.Next(int.MinValue, int.MaxValue);
			int y = rand.Next(int.MinValue, int.MaxValue);
			if(x == 0 || y == 0) continue;
			i++;
			Console.WriteLine(string.Format("{0} {1}", x, y));
		}
		int M = Q / 100;
		
		Console.WriteLine(Q);
		for(int i = 0; i < Q; i++){
			string type = "";
			if(i % M == 0) type = "C";
			else if(i % 2 == 0) type = "X";
			else type = "Y";
			
			int left = rand.Next(1, N + 1);
			int right = rand.Next(left, N + 1);
			
			Console.WriteLine(string.Format("{0} {1} {2}", type, left, right));
		}
	}
}
