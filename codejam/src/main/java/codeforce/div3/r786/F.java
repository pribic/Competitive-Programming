package codeforce.div3.r786;

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
 * @see <a href="https://codeforces.com/contest/1674/problem/F" target="_top">https://codeforces.com/contest/1674/problem/F</a>
 * @since 03/05/22 10:17 AM
 */
public class F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        int[] grid = new int[n * m];
        for (int i = 0; i < n; i++) {
          String row = sc.next();
          for (int j = 0; j < m; j++)
            grid[i * n + j] = getCnt(row.charAt(j));
        }
        int total = IntStream.of(grid).sum();
        int cur = 0; // [0, total) is the current range of stars/*
        for (int i = 0; i < total; i++) {
          cur += grid[i];
        }
        
        while (q-- > 0) {
          int x = sc.nextInt() - 1;
          int y = sc.nextInt() - 1;
          int pos = x * n + y;
          if (grid[pos] == '.') {
            grid[pos] = '*';
            total++;
            if(pos < cur) {
              cur++;
            }
          } else {
            grid[pos] = '.';
            total--;
          }
          int fullCol = total / n;
          int halfCol = total % n;
          int starsCount = 0;
          for (int i = 0; i < fullCol; i++) {
            //starsCount += colCount[i];
          }
          for (int i = 0; i < halfCol; i++) {
            //starsCount += getCnt(grid, i, fullCol);
          }
          System.out.println(total - starsCount);
        }
      }
    }
  }

  private static int getCnt(char[][] grid, int x, int y) {
    return grid[x][y] == '*' ? 1 : 0;
  }


  private static int getCnt(char ch) {
    return ch == '*' ? 1 : 0;
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