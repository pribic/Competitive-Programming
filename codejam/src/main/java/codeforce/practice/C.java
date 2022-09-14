package codeforce.practice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 13/04/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] nums = new int[n];
        int max = -1;
        for (int i = 0; i < n; i++) {
          nums[i] = sc.nextInt();
          max = Math.max(max, nums[i]);
        }

        int SIEVE_LIMIT = max + 1;
        boolean[] presentNums = new boolean[SIEVE_LIMIT];
        boolean[] isPrime = new boolean[SIEVE_LIMIT];

        for(int num : nums) presentNums[num] = true;
        //sieve like code
        long ans = 1;
        Arrays.fill(isPrime, true);
        for (int i = 2; i * i < SIEVE_LIMIT; i++) {
          int cnt = 0;
          if (isPrime[i]) {
            if (presentNums[i])
              cnt++;
            for (int j = i * i; j < SIEVE_LIMIT; j += i) {
              isPrime[j] = false;
              if (presentNums[j])
                cnt++;
            }
          }
          ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
      }
    }
  }


  private static long gcd(long a, long b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }


}