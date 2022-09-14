package codeforce.div2.r718;

import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @author pribic (Priyank Doshi)
 * @since 23/04/21
 */
public class C {

  public static void main(String[] args) {

    int[] dx = new int[]{0, 1};
    int[] dy = new int[]{-1, 0};
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] filipino = new int[n][n];
        for (int i = 0; i < n; i++)
          filipino[i][i] = sc.nextInt();
        Predicate<Integer> valid = idx -> idx >= 0 && idx < n;
        for (int i = 0; i < n; i++) {
          int val = filipino[i][i];
          int dir = 0;
          //try to go left and down
          int curX = i;
          int curY = i;
          while (val > 1) {
            int tx = curX + dx[dir];
            int ty = curY + dy[dir];
            if (valid.test(tx) && valid.test(ty) && filipino[tx][ty] == 0) {
              //means we can go in cur dir and that piece is empty
              filipino[tx][ty] = filipino[i][i];
              val--;
              curX = tx;
              curY = ty;
              dir = 0;
            } else {
              dir = 1 - dir;
            }
          }
        }
        for (int i = 0; i < n; i++) {
          for (int j = 0; j <= i; j++) {
            System.out.print(filipino[i][j] + " ");
          }
          System.out.println();
        }
      }
    }
  }
}