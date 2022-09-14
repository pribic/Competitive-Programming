package cses.tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://cses.fi/problemset/task/1139/" target="_top">https://cses.fi/problemset/task/1139/</a>
 * @since 21/05/22 2:23 PM
 */
public class DistinctColors {
  static FastScanner sc = new FastScanner(System.in);
  static ArrayList<Integer>[] al;
  static HashSet<Integer>[] distColor;
  static int[] distCnt;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] color = new int[n + 1];
        al = new ArrayList[n + 1];
        distColor = new HashSet[n + 1];
        distCnt = new int[n + 1];
        for (int i = 0; i < al.length; i++) {
          al[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++)
          color[i + 1] = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          al[u].add(v);
          al[v].add(u);
        }
        dfs(1, 0, color);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < distColor.length; i++)
          sb.append(distCnt[i] + " ");
        System.out.println(sb);
      }
    }
  }

  private static void dfs(int u, int p, int[] color) {
    int size = -1; // size of the children with biggest distinct color count
    int node = -1;
    for (int v : al[u]) {
      if (v != p) {
        dfs(v, u, color);
        if (distCnt[v] > size) {
          size = distCnt[v];
          distColor[u] = distColor[v];
          node = v;
        }
      }
    }
    if (distColor[u] == null) {
      distColor[u] = new HashSet<>();
    }
    distColor[u].add(color[u]);
    for (int v : al[u]) {
      if(v != node && v != p)
        distColor[u].addAll(distColor[v]);
    }
    distCnt[u] = distColor[u].size();
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