package kickstart.Y2021.round1B.IncreasingSubstring;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/04/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ":");
        int n = sc.nextInt();
        String str = sc.next();
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
          dp[i] = 1;
          if(str.charAt(i) > str.charAt(i - 1))
            dp[i] += dp[i-1];
        }
        for(int len : dp)
          System.out.print(" " + len);
        System.out.println();
      }
    }
  }
}