package codeforce.div3.r744;

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
 * @see <a href="https://codeforces.com/contest/1579/problem/C" target="_top">https://codeforces.com/contest/1579/problem/C</a>
 * @since 28/09/21 8:34 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
          grid[i] = sc.next().toCharArray();
        }
        boolean[][] covered = new boolean[n][m];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            //we will see if there is a valid v starting from this position
            int left = left(grid, i, j);
            int right = right(grid, i, j);
            int maxLen = Math.min(left, right);
            if (maxLen >= k + 1) {
              markLeft(grid, i, j, maxLen, covered);
              markRight(grid, i, j, maxLen, covered);
            }
          }
        }
        boolean valid = true;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if(grid[i][j] == '*' && !covered[i][j])
              valid = false;
          }
        }
        System.out.println(valid ? "YES" : "NO");
      }
    }
  }

  private static void markRight(char[][] grid, int i, int j, int maxLen, boolean[][] covered) {
    for (int ii = 0; ii < maxLen; ii++, i--, j++) {
      covered[i][j] = true;
    }
  }

  private static void markLeft(char[][] grid, int i, int j, int maxLen, boolean[][] covered) {
    for (int ii = 0; ii < maxLen; ii++, i--, j--) {
      covered[i][j] = true;
    }
  }

  private static int right(char[][] grid, int i, int j) {
    int cnt = 0;
    while (i >= 0 && j < grid[0].length && grid[i][j] == '*') {
      i--;
      j++;
      cnt++;
    }
    return cnt;
  }

  private static int left(char[][] grid, int i, int j) {
    int cnt = 0;
    while (i >= 0 && j >= 0 && grid[i][j] == '*') {
      i--;
      j--;
      cnt++;
    }
    return cnt;
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