package codeforce.div2.r671;

import java.util.Scanner;

public class Stairs {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long x = sc.nextLong(); //total cell we can use

        long l = 1;
        long r = 50;
        while (r > l + 1) {
          long mid = l + (r - l) / 2;
          if (f(mid, x)) {
            l = mid;
          } else {
            r = mid;
          }
        }
        System.out.println(l);
      }
    }
  }
  /**
   * need to check if we can make mid num of nice stars
   * and its sum should be <= x
   *
   * @param mid num of staircase we want to make
   * @param x
   * @return
   */
  private static boolean f(long mid, long x) {
    long sum = 1;
    long add = 2;
    long cur = 1;
   // 2, 4, 8, 16, 32 
    for (int i = 1; i < mid && sum <= x; i++) {
      cur += add;
      add *= 2;
      sum += (cur * (cur + 1)) / 2;
    }
    return sum <= x;
  }
}
