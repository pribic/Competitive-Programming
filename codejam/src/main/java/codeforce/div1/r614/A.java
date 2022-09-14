package codeforce.div1.r614;

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
 * @see <a href="https://codeforces.com/problemset/problem/1292/A" target="_top">https://codeforces.com/problemset/problem/1292/A</a>
 * @since 16/05/22 2:55 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[][] grid = new int[2][n + 2];
        int closed = 0;
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
          int r = sc.nextInt() - 1; // 0 based row
          int c = sc.nextInt(); // 1 based col
          //if already closed, then it will open up
          for (int i : new int[]{-1, 0, 1})
            if (grid[r][c] == 1 && grid[1 - r][c + i] == 1)
              closed--;
          grid[r][c] = 1 - grid[r][c]; // toggles between 1 and 0
          for (int i : new int[]{-1, 0, 1})
            if (grid[r][c] == 1 && grid[1 - r][c + i] == 1)
              closed++;
          sb.append(closed == 0 ? "Yes" : "No").append("\n");
        }
        System.out.print(sb);
      }
    }
  }

  /*
  - - - X -
  - - X - -
   */
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