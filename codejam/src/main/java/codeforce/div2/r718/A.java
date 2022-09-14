package codeforce.div2.r718;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 23/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        if (n % 2050 != 0)
          System.out.println(-1);
        else {
          long factor = n / 2050L;
          long ans = 0;
          while (factor > 0) {
            ans += factor % 10;
            factor /= 10;
          }
          System.out.println(ans);
        }
      }
    }
  }
}