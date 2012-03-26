import java.io.*;
import java.util.*;

public class Gen {
	public static void main(String[] args) throws Exception {
		PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int N = Integer.parseInt(args[0]);
		int K = Integer.parseInt(args[1]);
		
		Random rand = new Random();
		
		writer.printf("%d %d", N, K);
		writer.println();
		
		for(int i = 0; i < N; i++) {
			writer.println(rand.nextInt(20));
		}
		writer.close();
	}
}	