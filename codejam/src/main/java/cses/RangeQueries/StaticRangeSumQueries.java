package cses.RangeQueries;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 20/04/21
 */
public class StaticRangeSumQueries {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        sc.nextLine();
        int[] numbers = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
          prefixSum[i + 1] = prefixSum[i] + numbers[i];
        }
        while (q-- > 0) {
          int a = sc.nextInt();
          int b = sc.nextInt();
          System.out.println(prefixSum[b] - prefixSum[a - 1]);
        }
      }
    }
  }
}