package codeforce.div2.r688;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class B {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }

        long diff = 0;
        diff = getDiff(arr);
        long ans = diff;
        for (int i = 0; i < n; i++) {
          if (i == 0) {
            //make it same as next
            long diff1 = Math.abs(arr[0] - arr[1]);
            long newDiff = diff - diff1;
            ans = Math.min(ans, newDiff);
          } else if (i == n - 1) {
            long diff1 = Math.abs(arr[n - 1] - arr[n - 2]);
            long newDiff = diff - diff1;
            ans = Math.min(ans, newDiff);
          } else {
            //try making it same as next val;
            long newDiff = diff - Math.abs(arr[i] - arr[i-1]) - Math.abs(arr[i] - arr[i+1]) + Math.abs(arr[i+1] - arr[i-1]);
            ans = Math.min(ans, newDiff);
          }
        }
        System.out.println(ans);
      }
    }
  }

  private static long getDiff(int[] arr) {
    long diff = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      diff += Math.abs((arr[i + 1] - arr[i]));
    }
    return diff;
  }
}
