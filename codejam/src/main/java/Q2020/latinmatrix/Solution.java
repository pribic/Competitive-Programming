package Q2020.latinmatrix;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 27/02/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.print("Case #" + tt + ": ");
        if (k < n || k > n * n) {
          System.out.println("IMPOSSIBLE");
        } else {
          int a, b = 0, c = 0;
          boolean found = false;
          outer:
          for (a = 0; a <= n; a++) {
            for (b = 0; b <= n; b++) {
              for (c = 0; c <= n; c++) {
                if ((a + (b == 0 ? 0 : 1) + c == n) && a + b + c * n == k) {
                  found = true;
                  break outer;
                }
              }
            }
          }
          if (!found) {
            System.out.println("IMPOSSIBLE");
          } else {
            int[][] validLatin = new int[n][n];

            for (int i = 0; i < n; i++) {
              int num = 1;
              for (int j = i; j < n + i; j++) validLatin[i][j % n] = num++;
            }
            int[] diagonal = new int[n];
            for (int i = 0; i < a; i++) diagonal[i] = 1; //first a 1s
            if(b > 0)
              diagonal[a] = b; // 1 b
            for (int i = 0; i < c; i++) diagonal[n - 1 - i] = n; // last c ns
            boolean isValid = true;
            for (int i = 0; i < n; i++) {
              if(!fixMe(i, diagonal, validLatin)) {
                isValid = false;
                break;
              }
            }
            if(!isValid) {
              System.out.println("IMPOSSIBLE");
            } else {
              System.out.println("POSSIBLE");
              printMatrix(validLatin);
            }
          }
        }
      }
    }
  }

  private static boolean fixMe(int index, int[] diagonal, int[][] validLatin) {
    if (validLatin[index][index] != diagonal[index]) {
      //find in same row
      int expected = diagonal[index];
      int ans = -1;
      for (int col = index + 1; col < validLatin.length; col++) {
        if (validLatin[index][col] == expected) ans = col;
      }

      //find in same col
      if (ans != -1) {
        //swap index with ans col
        for (int i = 0; i < validLatin.length; i++) {
          int t = validLatin[i][index];
          validLatin[i][index] = validLatin[i][ans];
          validLatin[i][ans] = t;
        }

      } else {
        for (int row = index + 1; row < validLatin.length; row++) {
          if (validLatin[row][index] == expected) ans = row;
        }
        if(ans == -1)
          return false;
        for (int i = 0; i < validLatin.length; i++) {
          int t = validLatin[index][i];
          validLatin[index][i] = validLatin[ans][i];
          validLatin[ans][i] = t;
        }
      }
    }
    return true;
  }

  private static void printMatrix(int[][] matrix) {
    for (int[] row : matrix) {
      for (int col : row)
        System.out.print(col + " ");
      System.out.println();
    }
  }
}