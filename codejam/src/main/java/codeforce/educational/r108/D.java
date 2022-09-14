package codeforce.educational.r108;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/04/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] one = new long[n];
        for (int i = 0; i < n; i++) {
          one[i] = sc.nextLong();
        }
        long[] two = new long[n];
        for (int i = 0; i < n; i++) {
          two[i] = sc.nextLong();
        }
        long[] prefixSumMul = new long[n];
        long cur = 0L;
        for (int i = 0; i < n; i++) {
          cur += one[i] * two[i];
          prefixSumMul[i] = cur;
        }
        long[][] dp = new long[n + 1][n + 1];
        for (int i = 0; i < n; i++)
          dp[i][i] = one[i] * two[i]; //all one size
        for (int i = 0; i < n - 1; i++) {
          dp[i][i + 1] = one[i] * two[i + 1] + one[i + 1] * two[i]; //all two size
        }
        for (int len = 3; len <= n; len++)
          for (int start = 0; start < n - 2; start++) {
            //len means length of sub array
            int end = start + len - 1;
            if (end < n)
              dp[start][end] = dp[start + 1][end - 1] + one[start] * two[end] + one[end] * two[start]; // all > 2 size sub array
          }
        long ans = prefixSumMul[n - 1];
        for (int i = 0; i < n - 1; i++) {
          for (int j = i + 1; j < n; j++) {
            //suppose we reverse one[i....j], then what is the new prefixSumMul in o(1)
            long subArray = dp[i][j];
            //for (int k = i; k <= j; k++)
            //subArray += one[j - (k - i)] * two[k];
            ans = Math.max(ans, subArray + (i > 0 ? prefixSumMul[i - 1] : 0L) + (j < n - 1 ? prefixSumMul[n - 1] - prefixSumMul[j] : 0L));
          }
        }
        System.out.println(ans);
      }
    }
  }
}
/*
  a b
  c d  ->de+cf+bg+ah
  e f g h

  b a c d
  e f g h

  a c b d->ae+cf+bg+dh
  e f g h

  a d c b->ae+df+cg+bh
  e f g h
  
 */