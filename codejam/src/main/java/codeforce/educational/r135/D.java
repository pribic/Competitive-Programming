package codeforce.educational.r135;

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
 * @see <a href="https://codeforces.com/contest/1728/problem/D" target="_top">https://codeforces.com/contest/1728/problem/D</a>
 * @since 09/09/22 4:20 pm
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        int n = str.length();
        int[][] dp = new int[n][n];
        //base dp case of len 2
        for (int i = 0; i < n - 1; i++) {
          dp[i][i + 1] = eval(str, i, i + 1);
        }
        for (int len = 4; len <= n; len += 2) {
          //starting point now
          for (int l = 0; l + len <= n; l++) {
            int r = l + len - 1;
            //we are inspecting str[l..r] substring
            //what if we take str[l] -> next turn can take l+1 or r. next turn will try to find lowest ans
            int v1 = dp[l + 2][r] == 0 ? eval(str, l, l + 1) : dp[l + 2][r];
            int v2 = dp[l + 1][r - 1] == 0 ? eval(str, l, r) : dp[l + 1][r - 1];
            int option1 = Math.min(v1, v2); // to fetch worst result, that's what opponent will try to mimick for us
            //what if we take str[r]
            v1 = dp[l + 1][r - 1] == 0 ? eval(str, l, r) : dp[l + 1][r - 1];
            v2 = dp[l][r - 2] == 0 ? eval(str, r - 1, r) : dp[l][r - 2];
            int option2 = Math.min(v1, v2);
            dp[l][r] = Math.max(option1, option2);
          }
        }
        System.out.println(dp[0][n-1] == 1 ? "Alice" : "Draw");
      }
    }
  }

  private static int eval(String str, int i, int j) {
    return str.charAt(i) == str.charAt(j) ? 0 : 1;
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