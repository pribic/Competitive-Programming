package atcoder.beginnercontest.r172;

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
 * @see <a href="https://atcoder.jp/contests/abc172/tasks/abc172_e" target="_top">https://atcoder.jp/contests/abc172/tasks/abc172_e</a>
 * @since 21/08/21 3:14 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    InverseofNumber(MOD);
    InverseofFactorial(MOD);
    factorial(MOD);
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sign = 1;
        int ways = 0;
        for (int r = 1; r <= n; r++, sign = -1 * sign) {
          long ans = nCr(n, r) % MOD;
          ans = (ans * nPr(m, r)) % MOD;
          ans = (ans * nPr(m - r, n - r)) % MOD;
          ans = (ans * nPr(m - r, n - r)) % MOD;
          if(sign == 1)
            ways = (int) ((ways + ans) % MOD);
          else
            ways = (int) ((ways - ans + MOD) % MOD);
        }
        System.out.println((((nPr(m, n) * nPr(m, n)) % MOD + MOD) % MOD - ways % MOD) % MOD);
      }
    }
  }

  static int MOD = (int) 1e9 + 7;

  static int N = 10000001;

  // Array to store inverse of 1 to N
  static int[] factorialNumInverse = new int[N + 1];

  // Array to precompute inverse of 1! to N!
  static int[] naturalNumInverse = new int[N + 1];

  // Array to store factorial of first N numbers
  static int[] fact = new int[N + 1];

  // Function to precompute inverse of numbers
  public static void InverseofNumber(int p) {
    naturalNumInverse[0] = naturalNumInverse[1] = 1;

    for (int i = 2; i <= N; i++)
      naturalNumInverse[i] = naturalNumInverse[p % i] * (p - p / i) % p;
  }

  // Function to precompute inverse of factorials
  public static void InverseofFactorial(int p) {
    factorialNumInverse[0] = factorialNumInverse[1] = 1;

    // Precompute inverse of natural numbers
    for (int i = 2; i <= N; i++)
      factorialNumInverse[i] = (naturalNumInverse[i] * factorialNumInverse[i - 1]) % p;
  }

  // Function to calculate factorial of 1 to N
  public static void factorial(int p) {
    fact[0] = 1;

    // Precompute factorials
    for (int i = 1; i <= N; i++) {
      fact[i] = (fact[i - 1] * i) % p;
    }
  }

  // Function to return nCr % p in O(1) time
  public static int nCr(int n, int r) {

    // n C r = n!*inverse(r!)*inverse((n-r)!)
    int ans = (int) (((fact[n] * factorialNumInverse[r]) % MOD * factorialNumInverse[n - r]) % MOD);
    return ans;
  }

  public static int nPr(int n, int r) {
    // n C r = n!*inverse(r!)*inverse((n-r)!)
    int ans = (int) (((fact[n]) % MOD * factorialNumInverse[n - r]) % MOD);
    return ans;
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