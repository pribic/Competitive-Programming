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
import java.util.function.BiFunction;
import java.util.function.Function;
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
 * @see <a href="https://atcoder.jp/contests/dp/tasks/dp_s" target="_top">https://atcoder.jp/contests/dp/tasks/dp_s</a>
 * @since 16/11/21 10:27 PM
 */
public class S_Digit_Sum {
  static FastScanner sc = new FastScanner(System.in);
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String k = sc.next();
        int d = sc.nextInt();
        int[][][] dp = new int[k.length()][d][2]; // 0 means < , 1 means same prefix - 0 means relax, 1 means tight
        for (int dig = 0; dig < get(k, 0); dig++)
          dp[0][dig % d][0]++;
        dp[0][get(k, 0) % d][1] = 1;

        BiFunction<Integer, Integer, Integer> calc = (modulo, dig) -> (modulo - dig % d + d) % d;

        for (int i = 1; i < k.length(); i++) {
          for (int modulo = 0; modulo < d; modulo++) {
            for (int dig = 0; dig < 10; dig++) {
              int sum_prev = calc.apply(modulo, dig);
              dp[i][modulo][0] += dp[i - 1][sum_prev][0];
              dp[i][modulo][0] %= mod;
              if (dig < get(k, i)) {
                dp[i][modulo][0] += dp[i - 1][sum_prev][1];
                dp[i][modulo][0] %= mod;
              }
            }
            int sum_prev = calc.apply(modulo, get(k, i));
            dp[i][modulo][1] += dp[i - 1][sum_prev][1];
            dp[i][modulo][1] %= mod;
          }
        }
        System.out.println((dp[k.length() - 1][0][0] + dp[k.length() - 1][0][1] - 1 + mod) % mod);
      }
    }
  }

  static int get(String k, int i) {
    return k.charAt(i) - '0';
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