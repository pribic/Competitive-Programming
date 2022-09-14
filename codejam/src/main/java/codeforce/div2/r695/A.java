package codeforce.div2.r695;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 08/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] ans = new int[n];
        if (n == 1)
          System.out.print(9);
        else {
          System.out.print(98);
          n -= 2;
          int num = 9;
          for (int i = 0; i < n; i++) {
            System.out.print(num);
            num = (num + 1) % 10;
          }
        }
        System.out.println();
      }
      //989890
      //987678

    }
  }
}