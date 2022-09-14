package codeforce.div3.r710;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/03/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextInt();
        long m = sc.nextInt();
        long x = sc.nextLong();

        long tx = (x % n == 0) ? n - 1 : (x % n) - 1;
        long ty = (x % n == 0) ? (x / n - 1) : x / n;
        //System.out.println(tx + " " + ty);
        System.out.println(tx * m + ty + 1);
      }
    }
  }
  /**
   * 1 6 11
   * 2 7 12
   * 3 8 13
   * 4 9 14
   * 5 10 15
   */
}
 