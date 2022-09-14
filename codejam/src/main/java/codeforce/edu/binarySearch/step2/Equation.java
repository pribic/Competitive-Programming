package codeforce.edu.binarySearch.step2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 03/09/20
 */
public class Equation {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      double c = sc.nextDouble();
      double l = 1;
      double r = 10_000_000_000d / 2;
      while (r - l > (1d / 1000000)) {
        double mid = l + (r - l) / 2;
        if (f(mid) > c) {
          r = mid;
        } else
          l = mid;
      }
      System.out.println(r);
    }
  }

  private static double f(double mid) {
    return mid * mid + Math.sqrt(mid);
  }

  private static boolean good(long mid, int[] t, int[] z, int[] y, int m, int n) {
    long ans = 0; // total balloons we can inflate in mid time
    for (int i = 0; i < n; i++) {
      if (t[i] >= mid) {
        ans += z[i] * mid / (t[i] * z[i] + y[i]);  // - 1 cycle
      }
    }
    return ans >= m;
  }

}

