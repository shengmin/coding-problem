/**
 * @author ShengMin Zhang
 * @problem Multiply Except Selft
 * @revision 1.0
 * - DP: store the product of all numbers, then outputs[i] = product / inputs[i]
 */
using System;

public class Solution {
  public static void Main(String[] args) {
    int N = int.Parse(Console.ReadLine());
    long[] inputs = new long[N];
    int zeroCount = 0;
    long product = 1;
    
    for(int i = 0; i < N; i++) {
      long input = inputs[i] = long.Parse(Console.ReadLine());
      if(input == 0) {
        zeroCount++;
      } else {
        product *= input;
      }
    }
    
    for(int i = 0; i < N; i++) {
      long input = inputs[i];
      if(input == 0) {
        if(zeroCount == 1) {
          Console.WriteLine(product);
        } else {
          Console.WriteLine(0);
        }
      } else {
        if(zeroCount == 0) {
          Console.WriteLine(product / input);
        } else {
          Console.WriteLine(0);
        }
      }
    }
  }
}
