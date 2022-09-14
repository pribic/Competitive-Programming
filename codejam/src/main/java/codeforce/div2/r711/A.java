package codeforce.div2.r711;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/03/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        while (gcd(n, digitSum(n)) == 1) n++;
        System.out.println(n);
      }
    }
  }

  static long gcd(long x, long y) {
    long r = 0, a, b;
    a = Math.max(x, y); // a is greater number  
    b = Math.min(x, y); // b is smaller number  
    r = b;
    while (a % b != 0) {
      r = a % b;
      a = b;
      b = r;
    }
    return r;
  }

  private static long digitSum(long n) {
    long ans = 0;
    while (n > 0) {
      ans += n % 10;
      n /= 10;
    }
    return ans;
  }
}