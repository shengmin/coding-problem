public class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println(s.romanToInt("CCVII"));
    System.out.println(s.romanToInt("MLXVI"));
  }

  public int romanToInt(String s) {
    int n = 0;

    for (int i = 0, len = s.length(); i < len; i++) {
      char next = (i + 1 < len) ? s.charAt(i + 1) : ' ';

      switch (s.charAt(i)) {
        case 'I':
          if (next == 'V') { n += 4; i++; }
          else if (next == 'X') { n += 9; i++; }
          else n += 1;
          break;
        case 'V':
          n += 5;
          break;
        case 'X':
          if (next == 'L') { n += 40; i++; }
          else if (next == 'C') {n += 90; i++; }
          else n += 10;
          break;
        case 'L':
          n += 50;
          break;
        case 'C':
          if (next == 'D') { n += 400; i++; }
          else if (next == 'M') { n += 900; i++; }
          else n += 100;
          break;
        case 'D':
          n += 500;
          break;
        case 'M':
          n += 1000;
          break;
      }
    }

    return n;
  }
}
