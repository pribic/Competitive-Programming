package codeforce.edu.digitdp;

import java.util.Scanner;

public class Practice {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        long l = sc.nextLong();
        long r = sc.nextLong();
        int d = sc.nextInt();
        long start = System.currentTimeMillis();
        System.out.println(bruteforce(l, r));
        System.out.println("bruteforce " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println(sumOfDigits(d, new long[d + 1]));
        System.out.println("digit dp " + (System.currentTimeMillis() - start));
      }
    }
  }

  private static long sumOfDigits(int d, long[] memo) {
    if(d == 0)
      return 0;
    if(memo[d] != 0)
      return memo[d];
    long ans = 0;
    for (int i = 1; i <= 9; i++) {
      ans += (i * Math.pow(10, d - 1) + sumOfDigits(d - 1, memo));
    }
    return memo[d] = ans;
  }
  
  private static int bruteforce(long l, long r) {
    int ans = 0;
    for (long num = l; num <= r; num++) {
      //find all digit and sum them
      long d = num;
      while (d > 0) {
        ans += d % 10;
        d /= 10;
      }
    }
    return ans;
  }


}
