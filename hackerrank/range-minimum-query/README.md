Range Minimum Query (RMQ) is a set of problems which deals with finding a property (here minimum) of a range. Segment Tree can be very helpful when solving with such problems. A segment tree is a tree like data structure which is used to store the information about intervals. Here’s the [(wiki link)] of it.

You are given a array of N integers, arr[0], arr[1], .., arr[(N-1)]. And you are given a list of ranges. For each range, (l, r) you have to find the minimum value between range arr[l], arr[l+1], arr[l+2], .., arr[r].

Input
First line will contain two integers, N M, length of array and number of queries. Then in next line, there are N space separated integers which represent the array, arr[0], arr[1], .., arr[N-1]. Then M line follows. Each M line will contain two integers, l r, representing a range.

Output
For each range, (l, r), you have to print the minimum integer in subarray arr[l], arr[l+1], .., arr[r] in separate line.

Constraints
1 <= N, M <= 105
-105 <= arr[i] <= 105 , where 0 <= i < N
0 <= l <= r < N

Sample Input

10 5
10 20 30 40 11 22 33 44 15 5
0 5
1 2
8 9
0 9
4 6
Sample Output

10
20
5
5
11
Explanation

For range (0, 5), subarray will be [10, 20, 30, 40, 11, 22]. So minimum value will be 10.
For range (1, 2), subarray will be [20, 30]. Minimum value = 20.
For range (8, 9), subarray is [15, 5]. Minimum value = 5.
For range (0, 9), Here we have to find the minimum (5) of the whole array.
For range (3, 5), subarray is [40, 11, 22]. Minimum value = 11.
