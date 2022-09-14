package kickstart.Y2021.round1A.LShapedPlots;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 21/03/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] grid = new int[r][c];
        int ans = 0;
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            grid[i][j] = sc.nextInt();
          }
        }
        for (int times = 0; times < 4; times++) {
          r = grid.length;
          c = grid[0].length;
          int[][] north = new int[r][c];
          int[][] west = new int[r][c];

          for (int i = 0; i < r; i++) {
            for (int j = c - 1; j >= 0; j--) {
              if (grid[i][j] == 1) {
                north[i][j] = 1 + (i - 1 >= 0 ? north[i - 1][j] : 0);
                west[i][j] = 1 + (j + 1 < c ? west[i][j + 1] : 0);
              }
            }
          }
          for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
              if (grid[i][j] == 1
                && validIndex(r, c, i - 1, j) && validIndex(r, c, i, j + 1)
                && grid[i - 1][j] == 1 && grid[i][j + 1] == 1) {
                //get the length of valid segments now in the current directions
                int seg1 = north[i][j];
                int seg2 = west[i][j];
                ans += Math.max(0, Math.min(seg1, seg2 / 2) - 1);
                ans += Math.max(0, Math.min(seg2, seg1 / 2) - 1);
                //System.out.println(i + ":" + j + " " + (seg1 - 1) + " dir " + dir);
              }
            }
          }
          grid = rotate90(grid);
        }
        System.out.println(ans);
      }
    }

  }

  private static int[][] rotate90(int[][] grid) {
    int[][] ans = new int[grid[0].length][grid.length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        ans[j][ans[0].length - 1 - i] = grid[i][j];
      }
    }
    return ans;
  }

  private static boolean inValidIndex(int row, int col, int i, int j) {
    return i < 0 || j < 0 || i >= row || j >= col;
  }

  private static boolean validIndex(int row, int col, int i, int j) {
    return !inValidIndex(row, col, i, j);
  }
}