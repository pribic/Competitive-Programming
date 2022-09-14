package codeforce.div2.r720;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 07/05/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long a = sc.nextInt();
        long b = sc.nextInt();
        if (b == 1 || a == b || a % b == 0)
          System.out.println("NO");
        else if (b == 2) {
          System.out.println("YES");
          System.out.println(3 * a + " " + a + " " + 4 * a);
        }
        else{
          System.out.println("YES");
          System.out.println(a * (b - 1) + " " + a + " " + a * b);
        }
      }
    }
  }
}