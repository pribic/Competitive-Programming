package codeforce.div3.r762;

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
import java.util.PriorityQueue;
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
 * @see <a href="https://codeforces.com/contest/1619/problem/D" target="_top">https://codeforces.com/contest/1619/problem/D</a>
 * @since 21/12/21 9:44 AM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] happiness = new int[m][n];
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
            happiness[i][j] = sc.nextInt();
          }
        }
        int l = 0;
        int r = Integer.MAX_VALUE;
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (check(mid, happiness))
            l = mid;
          else
            r = mid;
        }
        System.out.println(l);
      }
    }
  }

  private static boolean check(int mid, int[][] happiness) {
    int[] colsum = new int[happiness[0].length];
    int[] rowsum = new int[happiness.length];
    int[][] h = new int[happiness.length][happiness[0].length];
    for (int i = 0; i < happiness.length; i++)
      for (int j = 0; j < happiness[i].length; j++) {
        h[i][j] = happiness[i][j] >= mid ? 1 : 0;
        colsum[j] += h[i][j];
        rowsum[i] += h[i][j];
      }
    return IntStream.of(colsum).allMatch(num -> num > 0) && IntStream.of(rowsum).anyMatch(num -> num > 1);
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