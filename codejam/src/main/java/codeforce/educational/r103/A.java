package codeforce.educational.r103;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt(), k = sc.nextInt();
        System.out.println(n > k ? (n % k == 0 ? 1: 2) : (k + n - 1) / n);
      }
    }
  }
}
