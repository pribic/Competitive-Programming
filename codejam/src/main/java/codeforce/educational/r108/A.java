package codeforce.educational.r108;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int r = sc.nextInt();
        int b = sc.nextInt();
        int d = sc.nextInt();

        if (r == b) {
          System.out.println("YES");
        } else {
          //we will have r beans
          long maxAllowed = Math.min(r, b) * (d + 1L);
          System.out.println(maxAllowed >= Math.max(r, b) ? "YES" : "NO");
        }
      }
    }
  }
}
