package spoj.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.spoj.com/problems/GONE/" target="_top">https://www.spoj.com/problems/GONE/</a>
 * @since 17/11/21 5:39 PM
 */
public class GONE {
  static FastScanner sc = new FastScanner(System.in);
  static int[][][] dp;
  static boolean[] isPrime;

  public static void main(String[] args) {
    isPrime = new boolean[200];
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i < 15; i++) {
      if (isPrime[i]) {
        for (int j = 2 * i; j < 200; j += i)
          isPrime[j] = false;
      }
    }

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String l = (sc.nextInt() - 1) + "";
        String r = sc.next();
        System.out.println(solve(r) - solve(l));
      }
    }
  }

  private static int solve(String n) {
    dp = new int[n.length()][163][2];
    for (int dig = 0; dig < get(n, 0); dig++)
      dp[0][dig][0]++;
    dp[0][get(n, 0)][1]++;
    for (int i = 1; i < n.length(); i++) {
      for (int j = 0; j < 163; j++) {
        for (int dig = 0; dig < 10; dig++) {
          if (j - dig >= 0)
            dp[i][j][0] += dp[i - 1][j - dig][0];
          if (dig < get(n, i) && j - dig >= 0)
            dp[i][j][0] += dp[i - 1][j - dig][1];
        }
        if (j - get(n, i) >= 0)
          dp[i][j][1] += dp[i - 1][j - get(n, i)][1];
      }
    }
    //now we know all the buckets
    int cnt = 0;
    for (int i = 0; i < 163; i++)
      if (isPrime[i])
        cnt += dp[n.length() - 1][i][0] + dp[n.length() - 1][i][1];
   // System.out.println(n + " = " + cnt);
    return cnt;
  }

  // 2 3 5 7 11 12 14 16
  static int get(String s, int i) {
    return s.charAt(i) - '0';
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