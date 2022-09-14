package codeforce.div3.r713;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
          grid[i] = sc.next().toCharArray();
        }
        int[][] points = new int[2][2];
        int idx = 0;
        for (int i = 0; i < n && idx < 2; i++) {
          for (int j = 0; j < n && idx < 2; j++) {
            if (grid[i][j] == '*') {
              points[idx][0] = i;
              points[idx][1] = j;
              idx++;
            }
          }
        }

        //if in same row
        if (points[0][0] == points[1][0]) {
          int row = points[0][0];
          grid[(row + 1) % n][points[0][1]] = '*';
          grid[(row + 1) % n][points[1][1]] = '*';
        } else if (points[0][1] == points[1][1]) { // same column
          int col = points[0][1];
          grid[points[0][0]][(col + 1) % n] = '*';
          grid[points[1][0]][(col + 1) % n] = '*';
        } else {
          grid[points[0][0]][points[1][1]] = '*';
          grid[points[1][0]][points[0][1]] = '*';
        }
        
        for(char[] row : grid) {
          for (char col : row) {
            System.out.print(col);
          }
          System.out.println();
        }
      }
    }
  }
}