package cses.dp;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 30/04/21
 */
public class DiceCombinations {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        System.out.println(solve(n, dp));
      }
    }
  }

  private static long solve(int n, long[] dp) {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= 6 && j <= i; j++) {
        dp[i] += dp[i - j];
        dp[i] %= 1000000007;
      }
    }
    return dp[n];
  }
}



