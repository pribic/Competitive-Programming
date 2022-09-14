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
 * @see <a href="https://codeforces.com/contest/1716/problem/B" target="_top">https://codeforces.com/contest/1716/problem/B</a>
 * @since 04/08/22 8:18 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        System.out.println(n);
        StringBuilder sb = new StringBuilder();
        int[] raw = new int[n];
        for (int i = 0; i < n; i++) {
          raw[i] = i + 1;
        }
        sb.append(concat(raw));
        swap(raw, n - 2, n - 1);
        sb.append(concat(raw));
        int si = n - 3;
        for (int i = 0; i < n - 2; i++, si--) {
          swap(raw, si, si + 1);
          sb.append(concat(raw));
        }
        System.out.print(sb);
        // 1 2 3 4 -> 1 2 4 3 -> 1 4 2 3 -> 4 1 2 3
        //
      }
    }
  }

  private static StringBuilder concat(int[] raw) {
    StringBuilder sb = new StringBuilder();
    for (int ii : raw)
      sb.append(ii).append(" ");
    sb.append("\n");
    return sb;
  }

  private static void swap(int[] raw, int i, int j) {
    int t = raw[i];
    raw[i] = raw[j];
    raw[j] = t;
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