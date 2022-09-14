package codeforce.div3.r739;

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
 * @see <a href="https://codeforces.com/contest/1560/problem/D" target="_top">https://codeforces.com/contest/1560/problem/D</a>
 * @since 18/08/21 8:44 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    String[] powers = new String[63];
    long num = 1;
    for (int i = 0; i < powers.length; i++, num *= 2) {
      powers[i] = num + "";
    }
    //out.println(Arrays.toString(powers));
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        //1 2 4 8 16 32 64 128 512 1024 
        String str = sc.next();
        int ans = Integer.MAX_VALUE;
        for (String pow : powers)
          ans = Math.min(ans, lcs(pow, str));
        System.out.println(ans);
      }
    }
  }
//1021024
  private static int lcs(String pow, String str) {
    /*int l1 = str.length();
    int l2 = pow.length();
    int[][] dp = new int[l1][l2];
    for (int[] row : dp) Arrays.fill(row, -1);
    dp[0][0] = str.charAt(0) == pow.charAt(0) ? 1 : 0;
    for (int row = 1; row < l1; row++) {
      dp[row][0] = str.charAt(row) == pow.charAt(row) ? 1 + dp[row - 1][0] : 0;
    }
    for (int col = 1; col < l2; col++) {
      dp[0][col] = str.charAt(0) == pow.charAt(col) ? 1 + dp[0][col - 1] : 0;
    }
    for (int i = 1; i < l1; i++) {
      for (int j = 1; j < l2; j++) {
        if (str.charAt(i) == str.charAt(j))
          dp[i][j] = 1 + dp[i - 1][j - 1];
        else
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
      }
    }
    int lcscnt = dp[l1 - 1][l2 - 1];*/
    //pow 2048
    //str 248
    //1052
    //1024
    int j = 0; // for power 
    for (int i = 0; j < pow.length() && i < str.length(); i++) {
      if (str.charAt(i) == pow.charAt(j))
        j++;
    }
    return str.length() - j + (pow.length() - j);
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