public class Driver {
  public static void main(String[] arguments) {
    BaseConversion conversion = new BaseConversion();
    for (int i = 0; i <= 100; i++) {
      for (int j = 2; j <= 36; j++) {
        for (int k = 2; k <= 36; k++) {
          String fromNumber = Integer.toString(i, j);
          String toNumber = conversion.convert(fromNumber, j, k);
          String expectedToNumber = Integer.toString(i, k);
          System.out.printf(
            "Input: (%s, %d, %d) Expected: %s Actual: %s",
            fromNumber,
            j,
            k,
            expectedToNumber,
            toNumber
          );
          System.out.println();
          
          if (!expectedToNumber.equals(toNumber)) {
            System.exit(0);
          }
        }
      }
    }

    System.out.println("All passed");
  }
}
