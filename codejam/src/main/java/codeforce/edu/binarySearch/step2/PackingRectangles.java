package codeforce.edu.binarySearch.step2;

import java.util.Scanner;

public class PackingRectangles {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      long w, h, n;
      w = sc.nextInt();
      h = sc.nextInt();
      n = sc.nextInt();
      long l = 1;
      long r = 1;
      while (!f(r, w, h, n)) r *= 2;
      
      while (l + 1 < r) {
        long mid = l + (r - l) / 2;
        if(f(mid, w, h, n))
          r = mid;
        else
          l = mid;
      }
      System.out.println(r);
    }
  }

  private static boolean f(long mid, long w, long h, long n) {
    return ( mid / w) * (mid / h) >= n;
  }
}
