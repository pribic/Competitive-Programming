package spoj.practice;

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
 * @see <a href="https://www.spoj.com/problems/LCASQ/" target="_top">https://www.spoj.com/problems/LCASQ/</a>
 * @since 14/11/21 10:02 AM
 */
public class LCASQ {
  static FastScanner sc = new FastScanner(System.in);
  static ArrayList<Integer>[] al;
  static int[] depth;
  static int LOG = 15;
  static int MAX_N = 10000;
  static int up[][];

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        al = new ArrayList[n + 1];
        depth = new int[n + 1];
        up = new int[MAX_N][LOG];
        for (int i = 0; i < al.length; i++) {
          al[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
          int m = sc.nextInt();
          for (int j = 0; j < m; j++) {
            int v = sc.nextInt();
            al[i].add(v);
          }
        }
        dfs(0, 0);
        int q = sc.nextInt();
        while (q-- > 0) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          System.out.println(get_lca(u, v));
        }
      }
    }
  }

  static void dfs(int a, int parent) {
    for (int b : al[a]) {
      if (b != parent) {
        depth[b] = depth[a] + 1;
        up[b][0] = a;
        for (int j = 1; j < LOG; j++) {
          up[b][j] = up[up[b][j - 1]][j - 1];
        }
        dfs(b, a);
      }
    }
  }

  private static int get_lca(int a, int b) {
    if (depth[a] < depth[b]) {
      int t = a;
      a = b;
      b = t;
    }
    int k = depth[a] - depth[b];
    for (int j = LOG - 1; j >= 0; j--) {
      if (((k >> j) & 1) == 1) {
        a = up[a][j];
      }
    }
    if (a == b)
      return a;
    for (int j = LOG - 1; j >= 0; j--) {
      if (up[a][j] != up[b][j]) {
        a = up[a][j];
        b = up[b][j];
      }
    }
    return up[a][0];
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