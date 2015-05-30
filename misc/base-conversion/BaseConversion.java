public class BaseConversion {
  public String convert(String number, int fromBase, int toBase) {
    int base10 = Integer.parseInt(number, fromBase);
    return Integer.toString(base10, toBase);
  }
}
