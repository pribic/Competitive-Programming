package codeforce.div2.r696;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/01/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String bb = sc.next();
        int[] b = new int[n];
        int index = 0;
        for (char x : bb.toCharArray())
          b[index++] = (x - '0');
        int[] a = new int[n];
        a[0] = 1;
        int[] d = new int[n];
        d[0] = a[0] + b[0];
        for (int i = 1; i < n; i++) {
          switch (d[i - 1]) {
            case 0:
              a[i] = 1;
              break;
            case 1:
              a[i] = b[i];
              break;
            case 2:
              a[i] = 1 - b[i];
              break;
          }
          d[i] = a[i] + b[i];
        }
        for (int x : a)
          System.out.print(x);
        System.out.println();
      }
    }
  }
}
//011
//110
//1