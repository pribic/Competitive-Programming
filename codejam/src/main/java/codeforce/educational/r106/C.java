package codeforce.educational.r106;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 18/03/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
          cost[i] = sc.nextInt();
        }
        long ans = Integer.MAX_VALUE;
        long[] prefixSum = new long[n];
        long curSum = 0;
        for (int i = 0; i < n; i += 2) {
          curSum += cost[i];
          prefixSum[i] = curSum;
        }
        curSum = 0;
        for (int i = 1; i < n; i += 2) {
          curSum += cost[i];
          prefixSum[i] = curSum;
        }

        long[] minNum = new long[n];
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i += 2) {
          min = Math.min(min, cost[i]);
          minNum[i] = min;
        }
        min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i += 2) {
          min = Math.min(min, cost[i]);
          minNum[i] = min;
        }
        for (int i = 1; i < n; i++) {
          if (i % 2 == 0) {
            //11 - 6 numbers we need
            //5 number
            //min out of 5 = min1
            //sum of 5 num == 10
            //4 1s and 
            ans = Math.min(ans, (i / 2 + 1L) * minNum[i] + prefixSum[i] - cost[i]);
          } else {
            ans = Math.min(ans, ((i + 1) / 2 + 1L) * minNum[i] + prefixSum[i] - cost[i] 
            );
          }
        }
        System.out.println(ans);
      }
    }
  }
}