package codeforce.div3.r697;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/01/21
 */
public class A {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        System.out.println(Long.bitCount(n) != 1 ? "YES" : "NO");
      }
    }
  }
}