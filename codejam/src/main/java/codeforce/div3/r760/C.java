package codeforce.div3.r760;

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
 * @see <a href="https://codeforces.com/contest/1618/problem/C" target="_top">https://codeforces.com/contest/1618/problem/C</a>
 * @since 14/12/21 8:34 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
        }
        long d = 0;
        for (int st1 = 0, st2 = 1; st1 < 2; st1++, st2--) {
          List<Long> first = new ArrayList<>();
          List<Long> second = new ArrayList<>();
          for (int i = st1; i < n; i += 2)
            first.add(arr[i]);
          for (int i = st2; i < n; i += 2)
            second.add(arr[i]);
          long gcd1 = gcd(first);
          boolean anydivide = second.stream().anyMatch(num -> num % gcd1 == 0);
          if (!anydivide) {
            d = gcd1;
            break;
          }
        }
        System.out.println(d);
      }
    }
  }

  private static long gcd(List<Long> first) {
    long gcd = 0;
    for (long f : first)
      gcd = gcd(gcd, f);
    return gcd;
  }

  private static long gcd(long a, long b) {
    if (b == 0)
      return a;
    return gcd(b, a % b);
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