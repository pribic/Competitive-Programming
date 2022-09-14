package codeforce.div2.r815;

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
 * @see <a href="https://codeforces.com/contest/1720/problem/A" target="_top">https://codeforces.com/contest/1720/problem/A</a>
 * @since 18/08/22 8:10 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        if (a == 0 || c == 0) {
          System.out.println(a == 0 && c == 0 ? 0 : 1);
          continue;
        }
        long gcd1 = gcd(a, b);
        long gcd2 = gcd(c, d);
        if (gcd1 != 0) {
          a /= gcd1;
          b /= gcd1;
        }

        if (gcd2 != 0) {
          c /= gcd2;
          d /= gcd2;
        }
        int cnt = 0;
        if (a == c && b == d) {

        } else if (a == c) {
          // b != d we know
          cnt = b % d == 0 || d % b == 0 ? 1 : 2;
        } else if (b == d) {
          // a != c we know
          cnt = a % c == 0 || c % a == 0 ? 1 : 2;
        } else {
          //now they are different
          
        }
        System.out.println(cnt);
      }
    }
  }
  /*
  100/3 = 25/6
   */

  private static long gcd(long a, long b) {
    if (b == 0) return a;
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