public class Gen {
	public static void main(String[] args){
		int N = Integer.parseInt(args[0]);
		int K = Integer.parseInt(args[1]);
		
		System.out.printf("%d %d", N, K);
		System.out.println();
		
		for(int i = 0; i < N; i++){
			System.out.print(i);
			System.out.print(' ');
		}
	}
}
