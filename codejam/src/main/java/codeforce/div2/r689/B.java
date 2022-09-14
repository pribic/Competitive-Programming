package codeforce.div2.r689;

import java.util.Scanner;

public class B {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
          String s = sc.next();
          grid[i] = s.toCharArray();
        }
        int[][] memo = new int[n][m];
        long ans = 0;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] == '*') {
              int h = findHeightOfSpurTree(grid, i, j, memo);
              memo[i][j] = h;
              ans += h;
            }
          }
        }
        System.out.println(ans);
      }
    }
  }

  private static int findHeightOfSpurTree(char[][] grid, int x, int y, int[][] memo) {
    int startX = x;
    int startY = y;
    int row = x;
    int expectedLen = 1;
    int extra = 0;
    if (x > 0 && memo[x - 1][y] > 2) {
      startX += memo[x - 1][y] - 1;
      startY -= memo[x - 1][y] - 1;
      expectedLen = 2 * memo[x - 1][y] - 1;
      extra += memo[x - 1][y] - 1;
    }
    for (; startX < grid.length && startY >= 0; row++, expectedLen += 2) {
      boolean allStar = true;
      for (int k = 0; k < expectedLen; k++) {
        //all this points should be *
        if (startY + k >= grid[0].length || grid[startX][startY + k] != '*') {
          allStar = false;
          break;
        }
      }
      if (!allStar)
        break;
      startX++;
      startY--;
    }
    return row - x + extra;
  }
}