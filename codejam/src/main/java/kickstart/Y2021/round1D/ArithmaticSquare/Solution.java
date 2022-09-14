package kickstart.Y2021.round1D.ArithmaticSquare;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082b813" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082b813</a>
 * @since 11/07/21 10:34 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.println("Case #" + tt + ": ");
        long[][] grid = new long[3][3];
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            if (i == 1 && j == 1) continue;
            grid[i][j] = sc.nextInt();
          }
        }
        int ans = 0;
        if (grid[0][1] - grid[0][0] == grid[0][2] - grid[0][1])
          ans++;
        if (grid[2][1] - grid[2][0] == grid[2][2] - grid[2][1])
          ans++;
        if (grid[1][0] - grid[0][0] == grid[2][0] - grid[1][0])
          ans++;
        if (grid[1][2] - grid[0][2] == grid[2][2] - grid[1][2])
          ans++;
        int maxCnt = 0;
        //try all 4 options
        if (((grid[2][2] - grid[0][0]) % 2) == 0) {
          grid[1][1] = grid[0][0] + (grid[2][2] - grid[0][0]) / 2;
          int cnt = count(grid);
          maxCnt = Math.max(maxCnt, cnt);
        }
        if ((grid[2][1] - grid[0][1]) % 2 == 0) {
          grid[1][1] = grid[0][1] + (grid[2][1] - grid[0][1]) / 2;
          int cnt = count(grid);
          maxCnt = Math.max(maxCnt, cnt);
        }
        if ((grid[2][0] - grid[0][2]) % 2 == 0) {
          grid[1][1] = grid[0][2] + (grid[2][0] - grid[0][2]) / 2;
          int cnt = count(grid);
          maxCnt = Math.max(maxCnt, cnt);
        }
        if ((grid[1][2] - grid[1][0]) % 2 == 0) {
          grid[1][1] = grid[1][0] + (grid[1][2] - grid[1][0]) / 2;
          int cnt = count(grid);
          maxCnt = Math.max(maxCnt, cnt);
        }
        System.out.println(ans + maxCnt);

      }
    }
  }

  private static int count(long[][] grid) {
    int cnt = 0;
    if (grid[1][1] - grid[0][0] == grid[2][2] - grid[1][1])
      cnt++;
    if (grid[1][1] - grid[0][1] == grid[2][1] - grid[1][1])
      cnt++;
    if (grid[1][1] - grid[0][2] == grid[2][0] - grid[1][1])
      cnt++;
    if (grid[1][1] - grid[1][2] == grid[1][0] - grid[1][1])
      cnt++;
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