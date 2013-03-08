import scala.util.control.Breaks._

object Solution {
	val N = readLine().toLong
	val MOD = 10007
	
	def power(base: Int, exponent: Int): Int = {
		if (exponent == 0) return 1;
		if (exponent == 1) return base;
		
		var answer = power(base, exponent / 2);
		answer = (answer * answer)
		if (exponent % 2 == 1) answer = (answer * base)
		return answer % MOD
	}
	
	def main(a: Array[String]) {
		if (N == 1) {
			println(1)
			return
		}
		
		var count = 0
		breakable {
			var i = 1
			while(i.toLong * i <= N) {
				if (N % i == 0) {
					val q = (N / i).toInt
					val toAdd = power(2, (q - 1) * (i - 1)) * (if (i == q) 1 else 2)
					count = (count + toAdd) % MOD
				}
				i = i + 1
			}
		}
		
		println(count)
	}
}