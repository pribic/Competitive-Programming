package codeforce.LATOKEN;

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
 * @see <a href="pr" target="_top">pr</a>
 * @since 14/06/21 8:45 PM
 */
public class practice {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        UnionFind uf = new UnionFind(n);
        while (m-- > 0) {
          String op = sc.next();
          if (op.equals("union")) {
            uf.union(sc.nextInt(), sc.nextInt());
          } else {
            int node = sc.nextInt();
            System.out.println(uf.min(node) + " " + uf.max(node) + " " + uf.sz(node));
          }
        }
      }
    }
  }

  static class UnionFind {
    int[] parent;
    int[] size;
    int[] rank;
    int[] min;
    int[] max;

    public UnionFind(int n) {
      this.parent = new int[n + 1];
      this.rank = new int[n + 1];
      this.size = new int[n + 1];
      this.min = new int[n + 1];
      this.max = new int[n + 1];
      for (int i = 1; i < n + 1; i++) {
        parent[i] = i;
        size[i] = 1;
        min[i] = i;
        max[i] = i;
      }
    }

    //merges group of a with group of p
    public void union(int a, int b) {
      int p1 = get(a);
      int p2 = get(b);
      if (p1 != p2) {
        if (rank[p1] == rank[p2])
          rank[p1]++;
        if (rank[p1] > rank[p2]) {
          merge(p2, p1);
        } else {
          merge(p1, p2);
        }
      }
    }

    private void merge(int child, int par) {
      parent[child] = par;
      size[par] += size[child];
      min[par] = Math.min(min[child], min[par]);
      max[par] = Math.max(max[child], max[par]);
    }

    //returns the group identifier
    public int get(int a) {
      return parent[a] = (a == parent[a] ? a : get(parent[a]));
    }

    public int min(int a) {
      return min[get(a)];
    }

    public int max(int a) {
      return max[get(a)];
    }

    public int sz(int node) {
      return size[get(node)];
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