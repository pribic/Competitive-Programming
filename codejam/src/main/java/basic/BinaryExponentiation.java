package basic;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 2020-07-28
 */
public class BinaryExponentiation {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println("Integer.toBinaryString(x) = " + Integer.toBinaryString(x));
        System.out.println("Integer.toBinaryString(y) = " + Integer.toBinaryString(y));
        System.out.println(power(x, y));
      }
    }
  }

  private static long power(int x, int y) {
    System.out.println("x = " + x);
    System.out.println("y = " + y);
    if( y == 0)
      return 1;
    if (y == 1)
      return x;
    long ans = power(x, y / 2);
    return ans * ans;
  }
}