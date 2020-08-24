package hackercup.Y2020.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemD {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(new File("/Users/pdoshi/Downloads/running_on_fumes_chapter_1_input.txt"))) {
      File f = new File("/Users/pdoshi/hackercup/q2020/problemD" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] cost = new long[n];
        for (int i = 0; i < n; i++) {
          cost[i] = sc.nextLong();
        }

        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE / 2 - 1);
        dp[0] = 0;
        for (int i = 1; i <= m; i++) {
          if(i < n)
            dp[i] = Math.min(dp[i], cost[i]);
        }
        //handle rest of the moves
        for (int i = 0; i < n; i++) {
          if (cost[i] == 0)
            continue;
          for (int jump = 1; jump <= m; jump++) {
            int at = i + jump;
            if (at < n && cost[at] > 0)
              dp[at] = Math.min(dp[at], dp[i] + cost[at]);
          }
        }

        FileWriter fw = new FileWriter(f, true);

       // System.out.println("last element " + dp[n-1]);
        String ans;
        if(dp[n-1] == Long.MAX_VALUE/2 -1) 
          ans = "Case #" + tt + ": -1";
        else
          ans = "Case #" + tt + ": " + ( dp[n - 1] - cost[n - 1]);
        System.out.println(ans);
        fw.write(ans + "\n");
        fw.close();
      }
    }
  }

  private static void solve(int at, int m, long[] cost, long[] dp) {
    if (at >= cost.length)
      return;
    long min = Integer.MAX_VALUE;
    for (int i = 1; i + m < cost.length; i++) {
      solve(at + i, m, cost, dp);
      min = Math.min(min, dp[at + i]);
    }
    dp[at] = min;
  }

  private static void print(int j, int start) {
    System.out.println("marking false i=" + j + ", j=" + start);
  }
}
