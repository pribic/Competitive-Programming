package codeforce.educational.r133;

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
 * @see <a href="https://codeforces.com/contest/1716/problem/C" target="_top">https://codeforces.com/contest/1716/problem/C</a>
 * @since 04/08/22 9:23 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int m = sc.nextInt();
        int[][] grid = new int[2][m];
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < m; j++) {
            grid[i][j] = sc.nextInt();
          }
        }
        long minTime = Long.MAX_VALUE;
        // route1
        long ct = 0;
        for (int j = 1; j < m; j++) {
          //want to jump to grid[0][j] from grid[0][j - 1]
          ct = getCt(ct, grid, 0, j);
        }
        for (int j = m - 1; j >= 0; j--) {
          //want to jump to grid[0][j] from grid[0][j - 1]
          ct = getCt(ct, grid, 1, j);
        }
        //System.out.println("ct = " + ct);
        //System.out.println("minTime = " + minTime);
        minTime = Math.min(minTime, ct);
        //route 2
        ct = 0;
        for (int j = 0; j < m; j++) {
          //want to jump to grid[0][j] from grid[0][j - 1]
          ct = getCt(ct, grid, 1, j);
        }
        for (int j = m - 1; j > 0; j--) {
          //want to jump to grid[0][j] from grid[0][j - 1]
          ct = getCt(ct, grid, 0, j);
        }
        //System.out.println("ct = " + ct);
        //System.out.println("minTime = " + minTime);
        minTime = Math.min(minTime, ct);

        //route 3
        ct = 0;
        int row = 1;
        for (int col = 0; col < m - 1; col++, row = 1 - row) {
          //grid[row][i]
          ct = getCt(ct, grid, row, col);
          //grid[row][i+1]
          ct = getCt(ct, grid, row, col + 1);
        }
        //last cell
        ct = getCt(ct, grid, m % 2, m - 1);
        minTime = Math.min(minTime, ct);
        System.out.println(minTime);
      }
    }
  }

  private static long getCt(long ct, int[][] grid, int row, int col) {
    if (ct >= grid[row][col]) {
      //can jump
      ct++;
    } else {
      //need to wait
      ct = grid[row][col] + 1;
    }
    return ct;
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