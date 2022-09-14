package codeforce.edu.binarySearch.step2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 03/09/20
 */
public class ChildrenHoliday {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int m = sc.nextInt();
      int n = sc.nextInt();
      int[] t = new int[n];
      int[] z = new int[n];
      int[] y = new int[n];
      for (int i = 0; i < n; i++) {
        t[i] = sc.nextInt();
        z[i] = sc.nextInt();
        y[i] = sc.nextInt();
      }

      long l = 0;
      long r = Integer.MAX_VALUE / 2;

      while (r > l + 1) {
        long mid = l + (r - l) / 2;
        if (good(mid, t, z, y, m, n))
          r = mid;
        else
          l = mid;
      }
      System.out.println(r);
    }
  }

  private static boolean good(long mid, int[] t, int[] z, int[] y, int m, int n) {
    long ans = 0; // total balloons we can inflate in mid time
    for (int i = 0; i < n; i++) {
      if(t[i] >= mid) {
        ans += z[i] * mid / (t[i] * z[i] + y[i]);  // - 1 cycle
      }
    }
    return ans >= m;
  }

}

