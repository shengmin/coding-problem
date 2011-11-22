using System;

public class Gen {
	public static void Main(string[] args) {
		int N = int.Parse(args[0]);
		Random rand = new Random();
		int MIN  = -100000;
		int MAX = 100000;
		Console.WriteLine(N);
		for(int i = 0; i < N; i++){
			Console.WriteLine(string.Format("{0} {1}", rand.Next(MIN, MAX), rand.Next(MIN, MAX)));
		}
	}
}