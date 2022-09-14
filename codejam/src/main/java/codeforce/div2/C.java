package codeforce.div2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1627/problem/A" target="_top">https://codeforces.com/contest/1627/problem/A</a>
 * @since 15/01/22 9:08 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static ArrayList<int[]>[] al;
  static int[] degree;
  static int[] weights;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        al = new ArrayList[n];
        degree = new int[n];
        weights = new int[n - 1];
        for (int i = 0; i < n; i++)
          al[i] = new ArrayList<>();
        boolean degMoreThan2 = false;
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          if (v < u) { // we want u < v
            int t = u;
            u = v;
            v = t;
          }
          al[u].add(new int[]{v, i});
          al[v].add(new int[]{u, i});
          degree[u]++;
          degree[v]++;
          degMoreThan2 = degMoreThan2 || degree[u] > 2 || degree[v] > 2;
        }
        if (degMoreThan2) {
          System.out.println(-1);
        } else {
          for (int i = 0; i < n; i++) {
            if (al[i].size() == 1) {
              dfs(i, -1, 2);
              break;
            }
          }
          // print
          for (long w : weights) {
            System.out.print(w + " ");
          }
          System.out.println();
        }
      }
    }
  }

  private static void dfs(int node, int parent, int prime) { // we will assign this prime
    for (int[] neigh : al[node]) {
      if (neigh[0] != parent) {
        //we have an edge from node to neigh
        weights[neigh[1]] = prime;
        dfs(neigh[0], node, 7 - prime);
      }
    }
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