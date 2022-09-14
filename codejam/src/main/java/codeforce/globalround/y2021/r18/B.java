package codeforce.globalround.y2021.r18;

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
 * @see <a href="https://codeforces.com/contest/1615/problem/B" target="_top">https://codeforces.com/contest/1615/problem/B</a>
 * @since 25/12/21 12:39 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);
  static int K = 30;

  public static void main(String[] args) {
    int[][] prefix = new int[2 * 100000 + 1][K];
    for (int i = 1; i < prefix.length; i++) {
      for (int bit = 0; bit < K; bit++) {
        prefix[i][bit] = prefix[i - 1][bit];
        if (set(i, bit)) {
          prefix[i][bit]++;
        }
      }
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int l = sc.nextInt();
        int r = sc.nextInt();
        int maxone = 0;
        for (int i = 0; i < K; i++) {
          maxone = Math.max(maxone, prefix[r][i] - prefix[l - 1][i]);
        }
        System.out.println(r - l + 1 - maxone);
      }
    }
  }

  private static boolean set(int mask, int i) {
    return ((mask >> i) & 1) == 1;
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