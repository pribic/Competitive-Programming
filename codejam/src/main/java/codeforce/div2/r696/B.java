package codeforce.div2.r696;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/01/21
 */
public class B {

  public static void main(String[] args) {
    //precalculate all primes
    boolean[] prime1 = sieve(21000);
    int[] primes = new int[2360];
    int index = 0;
    for (int i = 2; i <= 21000; i++) {
      if (prime1[i]) {
        //System.out.print(i + " ");
        primes[index++] = i;
      }
    }
    try (
      Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int d = sc.nextInt();
        if(d==1) {
          System.out.println(6);
          continue;
        }
        int d1 = findLeastMaxThan(1 + d, primes);
        int d2 = findLeastMaxThan(d1 + d, primes);
        System.out.println(d1 * d2);
      }
    }

  }

  /**
   * 1 3 4 5 6 7 10 13 17
   * f f f f f f t t t
   * x = 10
   *
   * @param i
   * @param primes
   * @return
   */
  private static int findLeastMaxThan(int i, int[] primes) {
    int l = 0;
    int r = primes.length-1;
    while (r > l + 1) {
      int mid = (l + r) / 2;
      if (primes[mid] >= i) {
        r = mid;
      } else {
        l = mid;
      }
    }
    return primes[r];
  }

  private static boolean[] sieve(int n) {
    boolean prime[] = new boolean[n + 1];
    for (int i = 0; i < n; i++)
      prime[i] = true;

    for (int p = 2; p * p <= n; p++) {
      if (prime[p]) {
        for (int i = p * p; i <= n; i += p)
          prime[i] = false;
      }
    }
    return prime;
  }

  private static Set<Integer> numOfDivisor(int n, int d) {
    Set<Integer> divisors = new TreeSet<>();
    int prev = -1;
    for (int i = 1; i <= (int) Math.sqrt(n); i++) {
      if (n % i == 0) {
        divisors.add(i);
        divisors.add(n / i);
      }
    }
    boolean flag = false;
    for (int xx : divisors) {
      if (!flag) {
        flag = true;
        prev = xx;
      } else {
        int diff = xx - prev;
        if (diff < d)
          return new TreeSet<>();
        prev = xx;
      }

    }
    return divisors;
  }
}