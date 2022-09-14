package codeforce.assiut.recursion;

import com.sun.corba.se.impl.io.OptionalDataException;

import java.util.Scanner;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 14/05/21
 */
public class NSumofaMatrix {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] a = new int[r][c];
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            a[i][j] = sc.nextInt();
          }
        }
        int[][] b = new int[r][c];
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            b[i][j] = sc.nextInt();
          }
        }
        int[][] ans = new int[r][c];
        matrixSum(a, b, 0, 0, ans);
        for(int[] row : ans) {
          for (int cell : row)
            out.print(cell + " ");
          out.println();
        }
      }
    }
  }

  private static void matrixSum(int[][] a, int[][] b, int x, int y, int[][] ans) {
    if (validRange(x, y, a)) {
      ans[x][y] = a[x][y] + b[x][y];
      if (validRange(x, y + 1, a))
        matrixSum(a, b, x, y + 1, ans);
      else
        matrixSum(a, b, x + 1, 0, ans);
    }
  }

  private static boolean validRange(int x, int y, int[][] a) {
    return x >= 0 && y >= 0 && x < a.length && y < a[0].length;
  }
}