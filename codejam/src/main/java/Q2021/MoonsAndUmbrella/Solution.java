package Q2021.MoonsAndUmbrella;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 26/03/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        String str = sc.next();
        int[][] dp = new int[str.length() + 1][2];
        for (int i = 0; i < str.length() + 1; i++) {
          Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int ans = minCost(str, 0, '.', x, y, dp);
        System.out.println(ans);
      }
    }
  }

  private static int minCost(String str, int i, char prev, int x, int y, int[][] dp) {
    if (prev != '.' && dp[i][convertToInt(prev)] != Integer.MAX_VALUE)
      return dp[i][convertToInt(prev)];
    if (i >= str.length())
      return 0;
    char cur = str.charAt(i);
    if (cur == 'C' || cur == 'J') {
      int ans = 0;
      if (cur == 'C' && prev == 'J') {
        ans = y + minCost(str, i + 1, cur, x, y, dp);
      } else if (cur == 'J' && prev == 'C') {
        ans = x + minCost(str, i + 1, cur, x, y, dp);
      } else {
        ans = minCost(str, i + 1, cur, x, y, dp);
      }
      return dp[i][convertToInt(prev)] = ans;
    } else {
      int minAns1 = Integer.MAX_VALUE;
      int minAns2 = Integer.MAX_VALUE;
      int minAns3 = Integer.MAX_VALUE;
      if (prev == 'C')
        minAns1 = Math.min(minCost(str, i + 1, 'C', x, y, dp), x + minCost(str, i + 1, 'J', x, y, dp));
      else if (prev == 'J')
        minAns2 = Math.min(y + minCost(str, i + 1, 'C', x, y, dp), minCost(str, i + 1, 'J', x, y, dp));
      else
        minAns3 = Math.min(minCost(str, i + 1, 'C', x, y, dp), minCost(str, i + 1, 'J', x, y, dp));
      return dp[i][convertToInt(prev)] = Math.min(Math.min(minAns1, minAns2), minAns3);
    }
  }

  private static int convertToInt(char prev) {
    return prev == 'C' ? 0 : 1;
  }
}