package kickstart.Y2020.round1D.RecordBreaker;

import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
          input[i] = sc.nextInt();
        }
        int localMax = Integer.MIN_VALUE;

        int ans = 0;

        for (int i = 0; i < n - 1; i++) {
          if (input[i] > localMax && input[i] > input[i + 1])
            ans++;
          localMax = Math.max(localMax, input[i]);
        }
        if (input[n - 1] > localMax)
          ans++;
        System.out.println("Case #" + tt + ": " + ans);
      }
    }
  }
}
