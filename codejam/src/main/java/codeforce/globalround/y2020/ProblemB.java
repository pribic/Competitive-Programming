package codeforce.globalround.y2020;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            matrix[i][j] = sc.nextInt();
          }
        }
        matrix[0][0] = Math.max(2, matrix[0][0]);
        matrix[0][m - 1] = Math.max(2, matrix[0][m - 1]);
        matrix[n - 1][0] = Math.max(2, matrix[n - 1][0]);
        matrix[n - 1][m - 1] = Math.max(2, matrix[n - 1][m - 1]);
        for (int i = 1; i < m - 1; i++) {
          matrix[0][i] = Math.max(3, matrix[0][i]);
          matrix[n - 1][i] = Math.max(matrix[n - 1][i], 3);
        }
        for (int i = 1; i < n - 1; i++) {
          matrix[i][0] = Math.max(matrix[i][0], 3);
          matrix[i][m - 1] = Math.max(matrix[i][m - 1], 3);
        }

        for (int i = 1; i < n - 1; i++) {
          for (int j = 1; j < m - 1; j++) {
            matrix[i][j] = Math.max(matrix[i][j], 4);
          }
        }

        //check invalid
        boolean invalid = false;
        for (int i = 0; i < n && !invalid; i++) {
          for (int j = 0; j < m && !invalid; j++) {
            if (invalidNeighbors(matrix, i, j))
              invalid = true;
          }
        }
        if (invalid) {
          System.out.println("NO");
        } else {
          System.out.println("YES");
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
          }
        }
      }
    }
  }

  private static boolean invalidNeighbors(int[][] matrix, int i, int j) {
    int cnt = 0;
    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};
    for (int k = 0; k < 4; k++) {
      try {
        int x = matrix[i + dx[k]][j + dy[k]];
        cnt++;
      } catch (Exception e) {

      }
    }
    return matrix[i][j] != cnt;
  }
}
