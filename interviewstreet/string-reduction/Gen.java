public class Gen {
	static char[] chars;
	static int N = 5;

	static void fill(int level){
		if(level == N) {
			System.out.println(new String(chars));
			return;
		}
		
		for(char c = 'a'; c <= 'c'; c++){
			chars[level] = c;
			fill(level+1);
		}
		
	}

	public static void main(String[] args){
		N = Integer.parseInt(args[0]);
		chars = new char[N];
		int total  = 1;
		for(int i = 0; i < N; i++){
			total *= 3;
		}
		System.out.println(total);
		fill(0);
	}
}
