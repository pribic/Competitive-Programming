package codeforce.div2.r701;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/02/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt(), q = sc.nextInt(), k = sc.nextInt();
        int[] raw = new int[n];
        for (int i = 0; i < n; i++) {
          raw[i] = sc.nextInt();
        }
        int[] ways = new int[n];
        for (int i = 0; i < n; i++) {
          if (i == 0) {
            ways[i] = (n == 1 ? k - 1 : (raw[i + 1] - 2));
          } else if (i == n - 1) {
            ways[i] = k - raw[i - 1] - 1;
          } else {
            ways[i] = raw[i + 1] - raw[i - 1] - 2;
          }
        }
        long[] prefixSum = new long[n];
        long curSum = 0;
        for (int i = 0; i < n; i++) {
          prefixSum[i] = curSum + ways[i];
          curSum += ways[i];
        }
        //System.out.println("ways = " + Arrays.toString(ways));
        //System.out.println("prefix = " + Arrays.toString(prefixSum));
        while (q-- > 0) {
          int l = sc.nextInt() - 1, r = sc.nextInt() - 1;
          if (l == r) {
            //ways[l] = 10
            //k = 20 -> 20 - 1 - 1 = 18
            //k = 10 | 8 -> 10 - 1 |
            System.out.println(k - 1);
          } else {
            long ans = Math.max(0, raw[l + 1] - 2);
            ans += (k - raw[r - 1] - 1);
            if (r > l + 1)
              ans += (prefixSum[r - 1] - prefixSum[l]); //sum of [l + 1, r - 1]
            System.out.println(ans);
          }
        }
      }
    }
  }
}