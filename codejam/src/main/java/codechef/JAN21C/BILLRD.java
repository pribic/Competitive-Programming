package codechef.JAN21C;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/01/21
 */
public class BILLRD {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        if (x == y)
          System.out.println(n + " " + n);
        else {
          int a = Math.min(x, y);
          int b = Math.max(x, y);
          b -= a;
          int[][] points = new int[][]{{0, b}, {n - b, n}, {n, n - b}, {b, 0}};
          BiFunction<Integer, Integer, Integer> fun = (xx, yy) -> xx > yy ? 1 : 0;
          System.out.println(points[k % 4][1 - fun.apply(x, y)] + " " + points[k % 4][fun.apply(x, y)]);
        }
      }
    }
  }

}