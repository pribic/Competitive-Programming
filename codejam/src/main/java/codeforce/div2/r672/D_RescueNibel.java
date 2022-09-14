package codeforce.div2.r672;

import java.util.Arrays;
import java.util.Scanner;

public class D_RescueNibel {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] l = new int[n];
      int[] r = new int[n];
      for (int i = 0; i < n; i++) {
        l[i] = sc.nextInt();
        r[i] = sc.nextInt();
      }
      int[] moments = new int[15];
      for (int i = 0; i < n; i++) {
        for (int j = l[i]; j <= r[i]; j++) {
          moments[j]++;
        }
      }
      System.out.println("Arrays.toString(moments) = " + Arrays.toString(moments));
      long ans = 0;
      for (int i = 0; i < 15; i++) {
        if (moments[i] >= k) {
          ans += nCr(moments[i], k);
        }
      }
      System.out.println(ans);
    }
  }

  private static long nCr(int n, int r) {
    long ans = 1;
    for (int i = (n - r) + 1; i <= n; i++) {
      ans *= i;
    }
    for (int i = 1; i <= r; i++) {
      ans /= i;
    }
    return ans;
  }
}

