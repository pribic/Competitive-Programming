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
 * @see <a href="https://codeforces.com/problemset/problem/910/A" target="_top">https://codeforces.com/problemset/problem/910/A</a>
 * @since 02/11/21 3:51 PM
 */
public class p910_A_The_Way_to_Home {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int d = sc.nextInt();
        char[] arr = sc.next().toCharArray();
        int[] dp = new int[n];
        // dp[i] = min jumps to reach i when arr[i] = 1, for other states, it is undefined or max int
        Arrays.fill(dp, n + 2);
        boolean reachable = true;
        for (int i = 0; i < n; i++) {
          if (arr[i] == '1') {
            //find min from last d positions
            int min = n + 2; // x, x - 1, x - d + 1
            for (int j = i - 1; j >= 0 && j >= i - d; j--) {
              min = Math.min(min, dp[j]);
            }
            if(min == n + 2)
              dp[i] = 1;
            else
              dp[i] = 1 + min;
          }
        }
        System.out.println(dp[n - 1] == 1 ? -1 : dp[n - 1] - 1);
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