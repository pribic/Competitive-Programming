package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/problems/GCDFUN" target="_top">https://www.codechef.com/problems/GCDFUN</a>
 * @since 20/06/21 9:58 AM
 */
public class GCDFUN {
  static FastScanner sc = new FastScanner(System.in);

  static int MOD = (int) 1e9 + 7;

  /*
  first toy generates 1 to N - x
  second toy generates gcd(N, x)
  since we have gcd of N, ans has to divide N. hence ans or say gcd has to be a factor of N , doesn't need to be a prime factor
  so for all factor of N, we need how many times d is a gcd then probability is sum (cnt/N * d) 
  
  36 -> 1 2 3 4 6 9 12 18 36
  gcd(N, x) = d | gcd(N, 6*1) = 6 , gcd(N, 6*5) = 6
  gcd(9*4, 9*1) = 9 , gcd(9*4, 9*2) = 9
  gcd(N/d, x) = 1 -> phi(N/d) 
  
   */
  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        Map<Integer, Integer> primeFactors = primeFactorize((int) n);
        System.out.println("primeFactors = " + primeFactors);
        List<Integer> pf = new ArrayList<>(primeFactors.keySet());
        System.out.println("pf = " + pf);
        int len = pf.size();
        long expectedVal = 0;
        for (int i = 1; i < (1 << len); i++) {
          long ans = 1;
          long times = 1;
          long upar = 1;
          long niche = 1;
          for (int j = 0; j < len; j++) {
            if (((i >> j) & 1) == 1) {
              //jth bit is set, take jth prime factor
              System.out.println("pf.get(j) = " + pf.get(j));
              int p = pf.get(j);
              
              times *= primeFactors.get(p);
              System.out.println("times = " + times);
              upar *= p - 1;
              System.out.println("upar = " + upar);
              niche *= p;
              System.out.println("niche = " + niche);
            }
          }
          expectedVal += times * upar  ;//* //modInv(niche);
        }
        System.out.println(expectedVal);
      }
    }
  }



  /**
   * if n is prime, then return n - 1
   * if a and b are co prime then etf(ab) = etf(a) * etf(b)
   * if a number is represented as p^k, this means this number has only one factor which is p.
   * so we want to find how many numbers <= n which doesn't have p as a factor. If a number doesn't
   * have p as a factor, then to find gcd of n and that number, we need to find all the common prime factors
   * to both of those numbers. But if we find numbers that don't have p as a factor, then we can be sure that
   * that number will be a co prime with n. for given number x, all multiples of x will have x as a factor, so all we need to worry about is
   * find out how many numbers less than or equal to n have x as a multiple. turns out we have exactly n/p such numbers.
   * n = 20, p = 2, then 10
   * n = 20, p = 3, then 6 , consider integer division
   * <p>
   * so for such numbers we have n - n/p coprimes
   * <p>
   * if n = p^k, then etf(n) = n - n / p = p^k - p^k / p = p^k - p^(k-1)
   * <p>
   * <p>
   * 180 = p^a * q ^ b * r ^ c = (p^a - p^a-1)(q^a - q^a - 1)(r^c - r^c - 1)
   * =p^a(1 - 1/p)q^a(1 - 1/q)r^c(1 - 1/r)
   * =n(1 - 1/p)(1 - 1/q)(1 - 1/r)
   */
  public static int euler_totient_function(int n) {
    Map<Integer, Integer> primeFactors = primeFactorize(n);
    int ans = n;
    for (int factor : primeFactors.keySet())
      ans -= ans / factor;
    return ans;
  }

  /**
   * nloglogn
   */
  public static int[] euler_totient_function_all(int n) {
    int[] etf = new int[n + 1];
    etf[0] = 0;
    etf[1] = 1;
    for (int i = 2; i <= n; i++)
      etf[i] = i;
    for (int i = 2; i <= n; i++) {
      if (etf[i] == i) { // this proves that i is prime.
        for (int j = i; j <= n; j += i)
          etf[j] -= etf[j] / i;
      }
    }
    return etf;
  }

  /**
   * performs prime factorization without using any build up or spf.
   * <p>
   * Uses sqrt(n) approach, traversing through all numbers till root n
   */
  public static Map<Integer, Integer> primeFactorizeSqrt(int n) {
    Map<Integer, Integer> primeFactors = new HashMap<>();
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) { // this will indirectly mean i is prime
        int cnt = 0;
        while (n % i == 0) {
          n /= i;
          cnt++;
        }
        primeFactors.put(i, cnt);
      }
    }
    if (n > 1)
      primeFactors.put(n, 1);
    return primeFactors;
  }

  public static Map<Integer, Integer> primeFactorize(int n) {
    int[] spf = buildSpf(1000000);
    Map<Integer, Integer> primeFactors = new HashMap<>();
    while (n > 1) {
      int x = spf[n];
      int cnt = 0;
      while (n % x == 0) {
        n /= x;
        cnt++;
      }
      primeFactors.put(x, cnt);
    }
    return primeFactors;
  }

  public static int[] buildSpf(int n) {
    int[] spf = new int[n + 1];
    spf[1] = 1;
    for (int i = 2; i <= n; i++) {
      if (spf[i] == 0)
        spf[i] = i;
      if ((long) i * i <= n) {
        for (int j = i * i; j <= n; j += i) {
          if (spf[j] == 0)
            spf[j] = i;
        }
      }
    }
    return spf;
  }


  /**
   * returns one integral solution for ax + by = c
   */
  public static long[] extended_generic_gcd(long a, long b, long c) {
    long[] ans = extended_gcd(a, b);
    long gcd = ans[0];
    long xnew = c * ans[1] / gcd;
    long ynew = c * ans[2] / gcd;
    return new long[]{gcd, xnew, ynew};
  }

  /**
   * int[] {gcd, x, y}
   *
   * @return
   */
  public static long[] extended_gcd(long a, long b) {
    if (b == 0) {
      return new long[]{a, 1, 0};
    } else {
      long[] ans = extended_gcd(b, a % b);
      long xnew = ans[2];
      long ynew = ans[1] - (a / b) * ans[2];
      return new long[]{ans[0], xnew, ynew};
    }
  }

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}