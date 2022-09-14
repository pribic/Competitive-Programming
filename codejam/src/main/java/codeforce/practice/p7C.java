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
 * @see <a href="https://codeforces.com/problemset/problem/7/C" target="_top">https://codeforces.com/problemset/problem/7/C</a>
 * @since 19/06/21 3:29 PM
 */
public class p7C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();

        if (a == 0) {
          //bx = -c
          if (c % b == 0) {
            System.out.println(-c / b);
          } else {
            System.out.println(-1);
          }
        } else {
          long[] ans = extended_generic_gcd(a, b, -c);
          System.out.println(ans[1] + " " + ans[2]);
        }
      }
    }
  }
  /*
  
  
  gcd(N/x, x) = 1 -> phi(n/x)
  
  x=x0+k⋅bg
 k = (x - x0)g/b
 k = (y0 - y)g/a
 
y=y0−k⋅ag
   */

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