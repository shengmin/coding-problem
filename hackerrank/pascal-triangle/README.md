Pascal's Triangle
For a given integer N, print the first N rows of Pascal’s Triangle. Print each row with values separated by spaces. The value at row i column j of the triangle is equal to (i-1)!/((j-1)!*(i-j+1))!. These values are the binomial coefficients.

The Pascal Triangle

1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
….

Input Format
A Single Integer, N.

Constraints

2<=N<=10
And, you need to accomplish this without directly defining any local variables. For example, var and val have been blocked in Scala; def and defn are blocked in Clojure.

Output Format

The first N rows of the Pascal Triangle.

Sample Input

4
Sample Output

1
1 1
1 2 1
1 3 3 1
