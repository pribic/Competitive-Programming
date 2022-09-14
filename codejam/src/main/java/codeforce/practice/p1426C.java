package codeforce.practice;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/03/21
 */
public class p1426C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int sqrt = (int)Math.sqrt(n);
        System.out.println(sqrt - 1 + (n - 1) / sqrt);
      }
    }
  }
}