package codeforce.div2.r723;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1526/problem/B" target="_top">https://codeforces.com/contest/1526/problem/B</a>
 * @since 28/05/21 8:15 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {

    try (PrintWriter out = new PrintWriter(System.out)) {
      //int[] dp = prebuildBfs();
      int[] dp = prebuildDfs();
      // Arrays.fill(dp, -1);
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        //System.out.println(isValid(n, dp) == 1 ? "YES" : "NO");
        System.out.println(n >= dp.length || dp[n] == 1 ? "YES" : "NO");
      }
    }
  }

  private static boolean[] prebuild() {
    boolean[] dp = new boolean[1100];
    dp[0] = true;
    for (int i = 0; i < dp.length; i++) {
      if (dp[i]) {
        if (i + 11 < dp.length)
          dp[i + 11] = true;
        if (i + 111 < dp.length)
          dp[i + 111] = true;
      }
    }
    return dp;
  }

  private static int[] prebuildBfs() {
    int[] dp = new int[1100];
    Arrays.fill(dp, -1);
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    queue.addLast(0);
    while (!queue.isEmpty()) {
      int n = queue.removeFirst();
      if (n < dp.length && dp[n] == -1) {
        //out.println(n);
        dp[n] = 1;
        queue.addLast(n + 11);
        queue.addLast(n + 111);
      }
    }
    return dp;//Arrays.stream(dp).mapToObj(num -> num == 1 ? new Boolean(true) : new Boolean(false)).toArray(Boolean[]::new);
  }

  private static int[] prebuildDfs() {
    int[] dp = new int[1100];
    Arrays.fill(dp, -1);
    dfs(0, dp);
    return dp;
  }

  private static void dfs(int num, int[] dp) {
    if (num >= dp.length || dp[num] != -1)
      return;
    dp[num] = 1;
    dfs(num + 11, dp);
    dfs(num + 111, dp);
  }

  private static int isValid(int n, int[] dp) {
    if (n < 0)
      return 0;
    if (n == 0 || n >= dp.length)
      return 1;
    if (dp[n] != -1)
      return dp[n];
    return dp[n] = (isValid(n - 11, dp) + isValid(n - 111, dp) > 0) ? 1 : 0;
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