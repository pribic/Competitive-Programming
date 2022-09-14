package codeforce.div4.r2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/01/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt(), b = sc.nextInt();
        boolean flag = false;
        outer: for (int c = 1; c <= a; c++) {
          for (int d = 1; d <= b; d++) {
            if ((c ^ d) > (a ^ b)) {
              flag = true;
              //System.out.println(c + " " + d);
              break outer;
            }
          }
        }
        System.out.println(flag ? "Yes" : "No");

      }
    }
  }
}