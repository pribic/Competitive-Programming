package codeforce.goodbye2021;

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
 * @see <a href="https://codeforces.com/contest/1616/problem/C" target="_top">https://codeforces.com/contest/1616/problem/C</a>
 * @since 29/12/21 10:22 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        if (n < 3) {
          System.out.println(0);
          continue;
        }
        int mincost = n;
        for (int i = 0; i < n; i++) {
          for (int j = i + 1; j < n; j++) {
            long[] delta = delta(arr[j] - arr[i], j - i); // p / q
            int cost = 0;
            for (int k = 0; k < n; k++) {
              if (k == i)
                continue;
              if ((delta[1] * arr[k] + delta[0] * (i - k)) != delta[1] * arr[i]) //arr[1] * arr[k] + arr[0] * (i - k)) != arr[1] * arr[i]
                cost++;
            }
            mincost = Math.min(mincost, cost);
          }
        }
        System.out.println(mincost);

      }
    }
  }

  private static long[] delta(int a, int b) {
    return new long[]{a / gcd(a, b), b / gcd(a, b)};
  }

  static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
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