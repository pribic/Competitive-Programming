package codeforce.edu.DSU.step1;

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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/7/1/practice/contest/289390/problem/C" target="_top">https://codeforces.com/edu/course/2/lesson/7/1/practice/contest/289390/problem/C</a>
 * @since 14/06/21 10:13 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        UnionFind uf = new UnionFind(n);
        while (m-- > 0) {

        }
      }
    }
  }

  static class UnionFind {
    int[] parent;
    int[] size;
    List<Integer> expPoints;
    int[] points;

    public UnionFind(int n) {
      parent = new int[n + 1];
      size = new int[n + 1];
      points = new int[n + 1];
      expPoints = new ArrayList<>();
    }

    public void union(int a, int b) {
      int p1 = parent[a];
      int p2 = parent[b];
      if (p1 != p2) {
        int sz1 = size[p1];
        int sz2 = size[p2];
        if (sz1 >= sz2) {
          merge(p2, p1);
        } else {
          merge(p1, p2);
        }
      }
    }

    private void merge(int child, int par) {
      size[par] += child;
      parent[child] = par;
    }

    public int get(int node) {
      return parent[node] = (parent[node] == node ? node : get(parent[node]));
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