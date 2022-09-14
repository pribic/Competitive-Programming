package kickstart.Y2020.round1H.problemB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {

  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        long l = sc.nextLong();
        long r = sc.nextLong();
        System.out.println(solve(r) - solve(l - 1));
      }
    }

  }

  //10 12 14
  private static long solve(long num) {
    String s = num + "";
    int[][] dp = new int[s.length()][2];
    dp[0][0]++;
    for (int dig = 1; dig < get(s, 0); dig += 2) // all the odd digits < first digits are considered
      dp[0][0]++;
    if (get(s, 0) % 2 == 1) //if first digit is odd, we can use it for tight
      dp[0][1]++;
    for (int i = 1; i < s.length(); i++) {
      if (i % 2 == 0) { // we can have odd digits
        for (int dig = 1; dig < get(s, i); dig += 2) {
          dp[i][0] += dp[i - 1][0] + dp[i - 1][1];
        }
        if (get(s, i) % 2 == 1)
          dp[i][1]++;
      } else { // we can have even digits
        for (int dig = 2; dig < get(s, i); dig += 2) {
          dp[i][0] += dp[i - 1][0] + dp[i - 1][1];
        }
        if (get(s, i) % 2 == 0)
          dp[i][1]++;
      }
    }
    return dp[s.length() - 1][0] + dp[s.length() - 1][1] - 1;
  }

  static int get(String s, int i) {
    return s.charAt(i) - '0';
  }

}
