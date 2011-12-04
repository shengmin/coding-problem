import java.io.*;
import java.util.*;

public class Sample {

	void solve(BufferedReader rd, int Q, int[] nums) throws Exception {
		for(; Q > 0; Q--) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			int max = Integer.MIN_VALUE;
			for(int i = p - 1; i < q; i++){ 
				int xor = nums[i] ^ a;
				if(xor > max) max = xor;
				// System.out.printf("%4d ^ %4d = %4d : %10s ^ %10s = %10s", nums[i], a, xor, Integer.toBinaryString(nums[i]), Integer.toBinaryString(a), Integer.toBinaryString(xor));
				// System.out.println();
			}
			
			System.out.println(max);
		}
	}

	void run(BufferedReader rd) throws Exception {
		int T = Integer.parseInt(rd.readLine());
		for(; T > 0; T--) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int[] nums = new int[N];
			
			st = new StringTokenizer(rd.readLine());
			for(int i = 0; i < N; i++){
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			solve(rd, Q, nums);
		}
	}

	public static void main(String[] args) throws Exception {
		Reader rd = (args.length == 0) ? new InputStreamReader(System.in) : new FileReader(args[0]);
		new Sample().run(new BufferedReader(rd));
	}
}
