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
 * @see <a href="https://www.codechef.com/ICPCIN19/problems/SHUFGRID" target="_top">https://www.codechef.com/ICPCIN19/problems/SHUFGRID</a>
 * @since 14/11/21 3:05 PM
 */
public class SHUFGRID {
  static int mod = 998244353;
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[] dpRow = new int[k + 1]; // probability that cell will remain on same row after ith iteration
        int[] dpCol = new int[k + 1]; // probability that cell will remain on same column after ith iteration
        int half = div(1, 2);
        int colSelect = div(1, 2l * n);
        int cellInCol = div(1, n);
        dpRow[1] = half;
        dpCol[1] = half;

        for (int i = 2; i <= k; i++) {
          int sameRowBefore = mul(dpRow[i - 1], half);
          int diffRowBefore = sub(1, dpRow[i - 1]) * colSelect * cellInCol;
          dpRow[i] = add(sameRowBefore, diffRowBefore);
          dpCol[i] = dpRow[i];
        }
        System.out.println(div(dpRow[k], k));
      }
    }
  }

  static int add(int a, int b) {
    return (a + b) % mod;
  }

  static int sub(int a, int b) {
    return (a - b + mod) % mod;
  }

  static int mul(long a, long b) {
    return (int) ((a * b) % mod);
  }

  static int div(long a, long b) {
    return (int) ((a * modInv(b)) % mod);
  }

  static private int modInv(long n) {
    return fastExpo(n, mod - 2);
  }

  private static int fastExpo(long n, int power) {
    if (power == 0)
      return 1;
    int res = fastExpo(n, power / 2) % mod;
    int resDouble = mul(res, res) % mod;
    if (power % 2 == 0) {
      return resDouble;
    } else {
      return mul(n, res);
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