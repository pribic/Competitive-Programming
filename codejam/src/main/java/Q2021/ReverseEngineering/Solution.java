package Q2021.ReverseEngineering;

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
        int n = sc.nextInt();
        int c = sc.nextInt();
        int min = n - 1;
        int max = (n * (n + 1))/2 - 1;
        if(c < min || c > max) {
          System.out.println("IMPOSSIBLE");
        } else {
          int[] series = getSeries(n, c, 1);
          for(int num : series) System.out.print(num + " ");
          System.out.println();
        }
      }
    }
  }

  private static int[] getSeries(int n, int c, int base) {
    if(2 * (n - 1) >= c) { //7 6
      int[] ans = new int[n];
      int cnt = 0;
      for(int i = c - n + 1; i >= 0; i--)
        ans[i] = base + cnt++;
      for(int i = c - n + 2; i < n; i++)
        ans[i] = base + cnt++;
      return ans;
    } else {
      int[] ans = new int[n];
      int idxA = n - 1;
      ans[idxA--] = base;
      int[] subSeries = getSeries(n - 1, c - n, base + 1);
      int idx = 0;
      for(int j = subSeries.length - 1; j >= 0; j--) {
        ans[idxA--] = subSeries[idx++];
      }
      return ans;
    }
  }

}