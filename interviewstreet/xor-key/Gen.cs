using System;

public class Gen {
	public static void Main(string[] args) {
		int T = 6;
		int N = int.Parse(args[0]);
		int Q = int.Parse(args[1]);
		
		Random rand = new Random();
		int MAX_VALUE = 32768 + 1;
		
		Console.WriteLine(T);
		
		for(; T > 0; T--){
			Console.WriteLine("{0} {1}", N, Q);
			for(int i = 0; i < N; i++){
				if(i != 0) Console.Write(' ');
				Console.Write(rand.Next(0, MAX_VALUE));
			}
			Console.WriteLine();
			
			for(int i = 0; i < Q; i++){
				int a = rand.Next(0, MAX_VALUE);
				int p = rand.Next(1, N + 1);
				int q = rand.Next(p, N + 1);
				
				Console.WriteLine("{0} {1} {2}", a, p, q);
			}
		}
	}
}
