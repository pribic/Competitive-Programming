package cses.mathematics;

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
 * @see <a href="https://cses.fi/problemset/task/1713" target="_top">https://cses.fi/problemset/task/1713</a>
 * @since 05/06/21 3:29 PM
 */
public class CountingDivisors {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    int[] spf = new int[1000000 + 1];
    for (int i = 2; i < spf.length; i++) {
      if (spf[i] == 0)
        spf[i] = i;
      if((long) i * i < spf.length) {
        for (int j = i * i; j < spf.length; j += i) {
          if (spf[j] == 0)
            spf[j] = i;
        }
      }
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int x = sc.nextInt();
        int cnt = 1;
        while (x > 1) {
          int spf1 = spf[x];
          int cnt1 = 1;
          while (x % spf1 == 0) {
            cnt1++;
            x /= spf1;
          }
          cnt *= cnt1;
        }
        System.out.println(cnt);
      }
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