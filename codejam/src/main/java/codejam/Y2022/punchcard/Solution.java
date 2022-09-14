package codejam.Y2022.punchcard;

import java.util.Scanner;

/**
 * @author pdoshi
 * @since 02/04/22
 */
public class Solution {


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      System.out.print("Case #" + tt + ":\n");
      int r = sc.nextInt();
      int c = sc.nextInt();
      solve(r, c);
    }
  }

  private static void solve(int r, int c) {
    char[][] grid = new char[2 * r + 1][2 * c + 1];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (i % 2 == 0 && j % 2 == 0) {
          grid[i][j] = '+';
        } else if (i % 2 == 0) {
          grid[i][j] = '-';
        } else if (j % 2 == 0) {
          grid[i][j] = '|';
        } else {
          grid[i][j] = '.';
        }
      }
    }
    grid[0][0] = grid[0][1] = grid[1][0] = grid[1][1] = '.';
    for (char[] row : grid) {
      for (char col : row) {
        System.out.print(col);
      }
      System.out.println();
    }
  }

}
