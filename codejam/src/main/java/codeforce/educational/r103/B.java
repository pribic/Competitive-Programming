package codeforce.educational.r103;

import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 29/01/21
 */

//4 10
//100 11 10 12
//101 11 10 12
//101 112  
public class B {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt(), k = sc.nextInt();
        long[] prices = new long[n];
        for (int i = 0; i < n; i++) {
          prices[i] = sc.nextInt();
        }
        for(int i = 1; i < n; i++) prices[i] += prices[i-1];
        long old = prices[0];
        for (int i = 1; i < n; i++) {
          long left = (prices[i] - prices[i-1]) * 100L;
          long right = (long) k * prices[i-1];
          if(left > right) {
             
          }
          while (left > right) {
            //increase 1st price by 1 and recheck
            for(int j = 0; j < n; j++) prices[j]++;
            left = (prices[i] - prices[i-1]) * 100L;
            right = (long)k * prices[i-1];
          }
        }
        System.out.println(prices[0] - old);
        /**
         * 20100 1 202 202
         * 20100 20101 20303 20505
         * 
         * 
         */
      }
    }
  }

  private static long runningPrice(long[] prices, int i) {
    long sum = 0;
    for (int j = 0; j < i; j++) {
      sum += prices[j];
    }
    return sum;
  }
}