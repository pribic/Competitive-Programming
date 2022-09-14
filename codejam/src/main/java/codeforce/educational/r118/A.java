package codeforce.educational.r118;

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
 * @see <a href="https://codeforces.com/contest/1613/problem/0" target="_top">https://codeforces.com/contest/1613/problem/0</a>
 * @since 01/12/21 8:06 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long x1 = sc.nextInt();
        int p1 = sc.nextInt();
        long x2 = sc.nextInt();
        int p2 = sc.nextInt();
        int cnt1 = digits(x1);
        int cnt2 = digits(x2);
        int td1 = cnt1 + p1;
        int td2 = cnt2 + p2;
        if (td1 == td2) {
          int minz = Math.min(p1, p2);
          p1 -= minz;
          p2 -= minz;
          for (int i = 0; i < p1; i++)
            x1 *= 10;
          for (int i = 0; i < p2; i++)
            x2 *= 10;
          if (x1 < x2)
            System.out.println("<");
          else if (x1 == x2)
            System.out.println("=");
          else
            System.out.println(">");
        } else if (td1 > td2) {
          System.out.println(">");
        } else {
          System.out.println("<");
        }
      }
    }
  }

  private static int digits(long x1) {
    int cnt = 0;
    while (x1 > 0) {
      cnt++;
      x1 /= 10;
    }
    return cnt;
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