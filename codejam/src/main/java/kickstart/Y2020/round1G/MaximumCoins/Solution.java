package kickstart.Y2020.round1G.MaximumCoins;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

  // 0 0 0 0
  // 0 0 0 0
  // 0 0 0 0
  // 0 0 0 0
  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        long ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            grid[i][j] = sc.nextInt();
          }
        }
        int times = n;
        for (int i = 0; i < n; i++) {
          int tx = 0;
          int ty = i;
          long curSum = 0;
          for (int k = 0; k < times; k++) {
            curSum += grid[tx][ty];
            tx++;
            ty++;
          }
          times--;
          ans = Math.max(ans, curSum);
        }
        times = n - 1;
        for (int i = 1; i < n; i++) {
          int tx = i;
          int ty = 0;
          long curSum = 0;
          for (int k = 0; k < times; k++) {
            curSum += grid[tx][ty];
            tx++;
            ty++;
          }
          ans = Math.max(ans, curSum);
          times--;
        }
        
        System.out.println(ans);
      }
    }

  }

}
