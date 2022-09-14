package codeforce.edu.binarySearch.step2;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 03/09/20
 */
public class VeryEasyTask {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int x = sc.nextInt();
      int y = sc.nextInt();

      int l = Math.min(x, y) - 1; // max bad number possibly
      int r = Math.max(x, y) * n;//min good number possibly
      
      while (!good(r, x, y, n)) r *= 2;
      
      while (r > l + 1) {
        int mid = l + (r - l) / 2;
        if (good(mid, x, y, n)) {
          r = mid;
        } else
          l = mid;
      }
      System.out.println(r);
    }
  }

  private static boolean good(int mid, int x, int y, int n) {
    mid -= Math.min(x, y);
    return (mid / x + mid / y) >= n - 1;
  }
}

