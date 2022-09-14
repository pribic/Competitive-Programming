package codeforce.div3.r764;

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
 * @see <a href="https://codeforces.com/contest/1624/problem/G" target="_top">https://codeforces.com/contest/1624/problem/G</a>
 * @since 11/01/22 7:08 PM
 */
public class G {
  static FastScanner sc = new FastScanner(System.in);
  static ArrayList<int[]>[] al;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        al = new ArrayList[n];
        for (int i = 0; i < n; i++)
          al[i] = new ArrayList<int[]>();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          int w = sc.nextInt();
          al[u].add(new int[]{v, w});
          al[v].add(new int[]{u, w});
        }
        // lets try to implement a bit selector, which will tell us which bit has to be 0 for sure
        int mask = 0; // if a bit is 1, it indicates that that bit in edge weight has to be 0
        int minOr = 0;
        for (int bit = 29; bit >= 0; bit--) {
          mask += 1 << bit;
          // check if this graph is still connected or not.
          UnionFind uf = new UnionFind(n);
          for (int i = 0; i < n; i++) {
            for (int[] edge : al[i]) {
              //see if this edge satisfies bit selector
              if ((mask & edge[1]) == 0) {
                // this is an edge from i to edge[0]
                uf.union(i, edge[0]);
              }
            }
          }
          //check if all node belongs to same component or not.
          boolean allsame = IntStream.range(0, n).allMatch(i -> uf.find(i) == uf.find(0));
          if (!allsame) { // means we cannot get rid of this bit
            mask -= 1 << bit;
            minOr += 1 << bit;
          }
          //System.out.println("mask = " + Integer.toBinaryString(mask));
        }
        System.out.println(minOr);
      }
    }
  }

  static class UnionFind {
    int[] parent;

    UnionFind(int n) {
      parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    int find(int x) {
      return parent[x] = (x == parent[x] ? x : find(parent[x]));
    }

    boolean union(int a, int b) {
      a = find(a);
      b = find(b);
      if (a == b)
        return false;
      parent[a] = b;
      return true;
    }
  }

  private static ArrayList[] build(ArrayList<int[]>[] al, int bit) {
    ArrayList<int[]>[] alWithoutBit = new ArrayList[al.length];
    for (int i = 0; i < al.length; i++) {
      alWithoutBit[i] = new ArrayList<>();
    }
    for (int i = 0; i < al.length; i++) {
      for (int[] edge : al[i]) {
        if (!set(edge[1], bit)) {
          alWithoutBit[i].add(new int[]{edge[0], edge[1]});
        }
      }
    }
    return alWithoutBit;
  }

  static boolean set(int mask, int i) {
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