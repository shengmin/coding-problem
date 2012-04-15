public class Gen {
	public static void main(String[] args) {
		int T = 50;
		if(args.length > 0) T = Integer.parseInt(args[0]);
		System.out.println(T);
		for(int i = 0; i < T; i++) {
			System.out.printf("%d %d", 1, 2000000);
			System.out.println();
		}
	}
}