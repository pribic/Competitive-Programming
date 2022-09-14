package codeforce.div2;

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
 * @see <a href="https://codeforces.com/contest/1627/problem/A" target="_top">https://codeforces.com/contest/1627/problem/A</a>
 * @since 15/01/22 8:52 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
          grid[i] = sc.next().toCharArray();
        }
        if (allwhite(grid, r, c)) {
          System.out.println(-1);
        }
        else if (grid[r][c] == 'B') {
          System.out.println(0);
        } else if (row(grid, r, c) || col(grid, r, c)) {
          System.out.println(1);
        } else {
          System.out.println(2);
        }
      }
    }
  }

  private static boolean col(char[][] grid, int r, int c) {
    for (int i = 0; i < grid.length; i++)
      if (grid[i][c] == 'B')
        return true;
    return false;
  }

  private static boolean row(char[][] grid, int r, int c) {
    for (int i = 0; i < grid[0].length; i++)
      if (grid[r][i] == 'B')
        return true;
    return false;
  }

  private static boolean allwhite(char[][] grid, int r, int c) {
    for (char[] row : grid)
      for (char cc : row)
        if (cc == 'B')
          return false;
    return true;
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