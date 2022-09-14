package atcoder.dp;

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
 * @see <a href="https://atcoder.jp/contests/dp/tasks/dp_d" target="_top">https://atcoder.jp/contests/dp/tasks/dp_d</a>
 * @since 17/07/21 12:50 PM
 */
public class Knapsack {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int w = sc.nextInt();
        Pair[] pairs = new Pair[n + 1];
        for (int i = 1; i <= n; i++) {
          pairs[i] = new Pair(sc.nextInt(), sc.nextInt());
        }
        long[][] dp = new long[n + 1][w + 1];
        //dp[1][1] = max value we get when we take items till 1 with weight 1
        dp[1][pairs[1].w] = pairs[1].v;
        for (int i = 1; i <= n; i++) {
          for (int j = 1; j < dp[0].length; j++) {
            dp[i][j] = dp[i - 1][j];
            if (j - pairs[i].w >= 0)
              dp[i][j] = Math.max(dp[i][j], pairs[i].v + dp[i - 1][j - pairs[i].w]);
          }
        }
        long max = -1;
        for (int i = 1; i < dp[0].length; i++)
          max = Math.max(max, dp[n][i]);
        System.out.println(max);
      }
    }
  }

  static class Pair {
    int w, v;

    public Pair(int w, int v) {
      this.w = w;
      this.v = v;
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