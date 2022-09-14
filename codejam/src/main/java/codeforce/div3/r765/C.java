package codeforce.div3.r765;

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
 * @see <a href="https://codeforces.com/contest/1625/problem/C" target="_top">https://codeforces.com/contest/1625/problem/C</a>
 * @since 12/01/22 7:04 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static long maxx = Long.MAX_VALUE / 2;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;// sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        // dp[i][k] = min time taken to reach ith poll, with exactly k poll removal
        int n = sc.nextInt();
        int l = sc.nextInt();
        int k = sc.nextInt();
        long[] locations = new long[n + 1];
        for (int i = 0; i < n; i++) {
          locations[i] = sc.nextInt();
        }
        locations[n] = l;
        long[] speedLimits = new long[n + 1];
        for (int i = 0; i < n; i++) {
          speedLimits[i] = sc.nextInt();
        }
        // ans is min of dp[n][0...k]
        long[][] dp = new long[n + 1][k + 1]; 
        for (long[] row : dp)
          Arrays.fill(row, maxx);
        dp[0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
          for (int kk = 0; kk <= k; kk++) {
            for (int last = i - 1; last >= 0; last--) {
              int left_k = kk - (i - last - 1);
              if (left_k >= 0)
                dp[i][kk] = Math.min(dp[i][kk], dp[last][left_k] + (locations[i] - locations[last]) * speedLimits[last]);
            }
          }
        }
        //System.out.println(Arrays.toString(dp[n]));
        System.out.println(Arrays.stream(dp[n]).min().getAsLong());
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
