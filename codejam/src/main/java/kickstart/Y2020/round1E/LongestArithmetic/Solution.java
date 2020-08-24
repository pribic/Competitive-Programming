package kickstart.Y2020.round1E.LongestArithmetic;

import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 1; tt <= t; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int ans = 2;
        int curDiff = arr[1] - arr[0];
        int curLen = 2;
        for (int i = 2; i < n; i++) {
          if(arr[i] - arr[i - 1] == curDiff) {
            curLen++;
          } else {
            ans = Math.max(ans, curLen);
            curDiff = arr[i] - arr[i-1];
            curLen = 2;
          }
        }
        ans = Math.max(ans, curLen);
        System.out.println(ans);
      }
    }
  }
}
