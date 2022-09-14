package codeforce.div2.r705;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 06/03/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int total = (n - k) + (k / 2);
        System.out.println(total);
        for(int i = n; i >= n - total; i--) {
          if(i != k) System.out.print(i + " ");
        }
        if(total > 0) System.out.println();
      }
    }
  }
}