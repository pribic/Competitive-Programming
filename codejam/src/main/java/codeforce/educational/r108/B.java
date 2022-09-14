package codeforce.educational.r108;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextInt();
        long m = sc.nextInt();
        long k = sc.nextInt();
        System.out.println(k == (m - 1 + (n - 1) * m) ? "YES" : "NO");
      }
    }
  }
}