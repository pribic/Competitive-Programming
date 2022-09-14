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
 * @see <a href="https://codeforces.com/problemset/problem/1327/E" target="_top">https://codeforces.com/problemset/problem/1327/E</a>
 * @since 21/08/21 10:11 PM
 */
public class p1327E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] dp = new int[n + 1][10];
        for (int i = 0; i < 10; i++) dp[1][i] = 1;
        // 1123345
        // dp[i][j] -> out of all segments, this tells count of segments of length i and ending with j
        //n = 2
        // 00 11 22 ... 99  10 of len 2
        // 90 nums , 180 of len 1
        // n = 3
        // 000 111 ... 999 10 of len 3
        // AAB || ABB -> 10 * 9 || 10 * 9 -> 180 of len 2
        // ABA || ABC -> 10 * 9 || 10 * 9 * 8 -> 90 + 720 -> 810
        // n = 4
        // 0000 1111.... 9999 10 of len 4
        // AAAB || ABBB -> 10 * 9 || 10 * 9 -> 180 of len 3
        // AABB || AABC || ABBA || ABBC || ABCC -> 90 + 720 + 90 + 720 + 720 =   
        
        for (int len = 2; len <= n; len++) {
          for (int ld = 0; ld <= 9; ld++) {
            dp[len][ld] = dp[len - 1][ld];
            for (int cd = 0; cd <= 9; cd++) {
              if (cd != ld)
                dp[len][ld] += dp[len - 1][cd];
            }
          }
        }
        int ans = 0;
        for (int ld = 0; ld <= 9; ld++) ans += dp[n][ld];
        System.out.println(ans);
      }
    }
  }

  static int MOD = 998244353;


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