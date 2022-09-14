package codeforce.edu.binarySearch.step5.A;

import java.util.Scanner;

public class KthNumberintheUnionofSegments {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int k = sc.nextInt();
      int[] li = new int[n];
      int[] ri = new int[n];
      for (int i = 0; i < n; i++) {
        li[i] = sc.nextInt();
        ri[i] = sc.nextInt();
      }

      long l = Long.MIN_VALUE/2 + 1;
      long r = Long.MAX_VALUE/2 - 1;

      while (r > l + 1) {
        long mid = l + (r - l) / 2;
        //System.out.println(l + " " + mid + " " + r);
        if (f(mid, li, ri, k)) {
          l = mid;
        } else {
          r = mid;
        }
      }
      System.out.println(l);
    }
  }

  private static boolean f(long mid, int[] li, int[] ri, long k) {
    long cnt = 0;
    for (int i = 0; i < li.length; i++) {
      if (li[i] < mid)
        cnt += (Math.min(ri[i], mid-1) - li[i] + 1);
    }
    //System.out.println("---" + cnt + " " + mid + " " + k);
    return cnt <= k;
  }
}
