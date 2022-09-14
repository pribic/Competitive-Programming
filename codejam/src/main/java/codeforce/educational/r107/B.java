package codeforce.educational.r107;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/04/21
 */
public class B {

  public static void main(String[] args) {
    /*for (int a = 1; a <= 9; a++) {
      for (int b = a; b <= 9; b++) {
        for (int c = 1; c <= Math.min(a, b); c++) {
          //System.out.println(a + " " + b + " " + c);
          long[] ans = solve(b, a, c);
          long gcd = gcd(ans[0], ans[1]);
          if(digits(ans[0]) != a || digits(ans[1]) != b || digits(gcd) != c) {
            System.out.println("error " + a + " " + b + " " + c);
          }
        }
      }
    } */
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        long[] ans = solve(a, b, c);
        System.out.println(ans[0] + " " + ans[1]);
        //long gcd = gcd(ans[0], ans[1]);
        //if(digits(gcd) != c || digits(ans[0]) != a || digits([1]))
      }
    }
  }

  private static long gcd(long a, long b) {
    if(b==0) return a;
    return gcd(b, a%b);
  }
  private static int digits(long num) {
    int cnt = 0;
    while (num > 0) {
      cnt++;
      num /= 10;
    }
    return cnt;
  }

  private static long[] solve(int a, int b, int c) {
    boolean isSwapped = false;
    if (a > b) {
      int t = a;
      a = b;
      b = t;
      isSwapped = true;
    }
    long l = (long) (1L * Math.pow(10, a - 1));
    long r = (long) (1L * Math.pow(10, b - 1));

    long gcd = (long) (5L * Math.pow(10, c - 1));
    return isSwapped ? new long[]{r + gcd, l} : new long[]{l, r + gcd};
  }
}