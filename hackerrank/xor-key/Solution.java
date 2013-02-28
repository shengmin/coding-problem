/**
 * @author ShengMin Zhang
 * @problem XOR Key
 *
 * @revision 1.0
 * - sort the numbers using counting sort
 * - construct a special balanced binary tree where left child at height i stores the range of numbers whose ith digit(count from left) is 0
 * - may need to use binary search to find the range
 * - this tree will be at most 15 level deep for this problem
 * - in order to maximize the value, we need a number whose ith digit is different from ith digit of a 
 *   keep traversing down the tree until we hit a node whose range is single number or we reach the leaf, in which case all numbers in that range is equal
 */

import java.io.*;
import java.util.*;

public class Solution {
	static final int MAX_NUM = 32768;
	
	static class Node {
		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}	
		
		int start, end;
		
		boolean intersects(int s, int e) {
			if(start < s && end >= s) return true;
			if(start >= s && start <= e) return true;
			return false;
		}
	}

	static class BinaryTree {
		Node[] nodes; 
		int[] nums;
		
		BinaryTree(int[] nums) {
			this.nums = nums;
			nodes = new Node[100000]; // should be enough for this problem
			initialize(nums, 0, 0, nums.length - 1, MAX_NUM);
		}
		
		int getLeftChildIndex(int i) { return 2 * i + 1; }
		int getRightChildIndex(int i) { return 2 * i + 2; }
		
		void initialize(int[] nums, int index, int start, int end, int mask) {
			nodes[index] = new Node(start, end);
			
			if(mask == 0) return;
			
			int leftIndex = getLeftChildIndex(index);
			int rightIndex = getRightChildIndex(index);
			
			// if the first number's current digit is 1, then left child doesn't exist
			if((mask & nums[start]) == mask) {
				initialize(nums, rightIndex, start, end, mask >>> 1);
				return;
			}
			
			// find the split point
			int i = start;
			for(; i <= end; i++) {
				if((mask & nums[i]) == mask) {
					// this digit is 1
					break;
				}
			}
			
			initialize(nums, leftIndex, start, i - 1, mask >>> 1);
			
			if(i <= end) {
				// right child
				initialize(nums, rightIndex, i, end, mask >>> 1);
			}
		}
		
		int query(int A, int start, int end) {
			return query(A, start, end, 0, MAX_NUM);
		}
		
		int query(int A, int start, int end, int index, int mask) {
			if(start == end) return nums[start];
			if(mask == 0) return nums[start];
			
			int leftIndex = getLeftChildIndex(index);
			int rightIndex = getRightChildIndex(index);
			
			Node left = nodes[leftIndex];
			Node right = nodes[rightIndex];
			
			if((A & mask) == mask) {
			    // current digit is 1
				// go to the right child if intersects the range
			    if(left != null && left.intersects(start, end)) return query(A, start, Math.min(end, left.end), leftIndex, mask >>> 1);
                else return query(A, Math.max(start, right.start), end, rightIndex, mask >>> 1);
			} else {
				if(right != null && right.intersects(start, end)) return query(A, Math.max(start, right.start), end, rightIndex, mask >>> 1);
                else return query(A, start, Math.min(end, left.end), leftIndex, mask >>> 1);
			}
		}
		
		void print(){
			for(Node node: nodes) {
				if(node == null) continue;
				System.out.printf("%d - %d", node.start, node.end);
				System.out.println();
			}
		}
	}

	
	void sort(int[] nums) {
		int count[] = new int[MAX_NUM + 1];
		for(int i = nums.length - 1; i >= 0; i--) {
			count[nums[i]]++;
		}
		
		for(int i = 0, j = 0; j <= MAX_NUM; j++) {
			while(count[j] != 0) {
				nums[i++] = j;
				count[j]--;
			}
		}
	}

	void solve(BufferedReader rd, int Q, int[] nums) throws Exception {
		sort(nums);
		BinaryTree tree = new BinaryTree(nums);
		//tree.print();
	
		for(; Q > 0; Q--) {
			StringTokenizer st = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int n = tree.query(a, p - 1, q - 1);
			int max = n ^ a;
//			for(int i = p - 1; i < q; i++){ 
//				int xor = nums[i] ^ a;
//				if(xor > max) max = xor;
//				//System.out.printf("%4d ^ %4d = %4d : %10s ^ %10s = %10s", nums[i], a, xor, Integer.toBinaryString(nums[i]), Integer.toBinaryString(a), Integer.toBinaryString(xor));
//				//System.out.println();
//			}
			
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
		new Solution().run(new BufferedReader(rd));
	}
}
