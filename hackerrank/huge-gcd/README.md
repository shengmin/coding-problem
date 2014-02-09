Gayasen has received a homework assignment to compute the greatest common divisor of the two positive integers A and B. Since the numbers are quite large, the professor provided him with N smaller integers whose product is A, and M integers with product B. He would like to verify result, so he has asked you to write a program to solve his problem. But instead of printing complete answer you have to print answer modulo 109+7.

Input
First line of input contains the positive integer N (1 <= N <= 1000).
Second line of input contains N space-separated positive integers not greater than 104, whose product is the number A.
Third line of input contains the positive integer M (1 <= M <= 1000).
Fourth line of input contains M space-separated positive integers not greater than 104, whose product is the number B.

OUTPUT
Print the greatest common divisor of numbers A and B modulo 1000000007.

Constraints
1 <= N, M <= 1000
1 <= element of list <= 10000

Sample Input #00

5
2 2 3 3 25
4
8 1 6 170
Sample Output #00

60
Sample Input #01

13
1 2 4 8 32 64 128 256 512 1024 2048 4096 8192
9
1 3 9 27 81 243 729 2187 6561
Sample Output #01

1
Sample Input #02

3
2 3 5
2
4 5
Sample Output #02

10
Explanation
Sample Case #00: Here A = 2×2×3×3×25 = 900, while B = 8×1×6×170 = 8160. Greatest common divisor of 900 and 8160 is 60.
Sample Case #01: In first list all number are of form 2^a and in second they are of form 3^a. As these two list doesn’t share any factor, their gcd is 1.
Sample Case #02: The greatest common divisor of numbers A(=30) and B(=20) equals 10.
