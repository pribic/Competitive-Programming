package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * @see <a href="https://codeforces.com/problemset/problem/1294/C" target="_top">https://codeforces.com/problemset/problem/1294/C</a>
 * @since 15/06/21 3:01 PM
 */
public class p1294C {
  static FastScanner sc = new FastScanner(System.in);

  static int limit = (int) 100000;

  public static void main(String[] args) {
    boolean[] isPrime = new boolean[limit];
    Arrays.fill(isPrime, true);
    List<Integer> primes = new ArrayList<>();
    int[] spf = new int[limit];
    for (int i = 2; i < limit; i++) {
      if (spf[i] == 0)
        spf[i] = i;
      if (isPrime[i]) {
        primes.add(i);
        if ((long) i * i < limit) {
          for (int j = i * i; j < limit; j += i) {
            isPrime[j] = false;
            if (spf[j] == 0)
              spf[j] = i;
          }
        }
      }
    }
    out.println(Arrays.toString(primes.toArray()));

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        Map<Integer, Integer> primeFactorization = new HashMap<>();
        //36
        while (n > 1) {
          int spf1 = spf[n];
          int cnt1 = 0;
          while (n % spf1 == 0) {
            n /= spf1;
            cnt1++;
          }
          primeFactorization.put(spf1, cnt1);
        }
        System.out.println(primeFactorization);

      }
    }
  }

  private static List<Integer> factors(int n) {
    List<Integer> factors = new ArrayList<>();
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        factors.add(i);
        if (i != n / i)
          factors.add(n / i);
      }
    }
    return factors;
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