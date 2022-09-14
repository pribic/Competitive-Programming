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
 * @see <a href="https://codeforces.com/problemset/problem/245/H" target="_top">https://codeforces.com/problemset/problem/245/H</a>
 * @since 17/07/21 5:18 PM
 */
public class p245H {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        char[] str = sc.next().toCharArray();
        int len = str.length;
        int[][] isPal = new int[len][len];
        for (int k = 1; k <= len; k++) {
          for (int st = 0; st < len; st++) {
            int end = st + k - 1;
            if (end >= len) continue;
            if (st == end || (end - st == 1 && str[st] == str[end]) || (str[st] == str[end] && isPal[st + 1][end - 1] == 1))
              isPal[st][end] = 1;
          }
        }
        int[][] dp = new int[len][len];
        for (int k = 1; k <= len; k++) {
          for (int st = 0; st < len; st++) {
            int end = st + k - 1;
            if (end >= len) continue;
            if (st == end)
              dp[st][end] = 1;
            else if (end - st == 1) {
              dp[st][end] = 2 + isPal[st][end];
            } else {
              dp[st][end] = dp[st + 1][end] + dp[st][end - 1] - dp[st + 1][end - 1] + isPal[st][end];
            }
          }
        }
        int q = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt() - 1;
          ans.append(dp[l][r]).append("\n");
        }
        System.out.println(ans);
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