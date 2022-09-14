package codeforce.practice.unacadamy.r3;

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
 * @see <a href="https://codeforces.com/gym/346944/problem/C" target="_top">https://codeforces.com/gym/346944/problem/C</a>
 * @since 30/09/21 10:51 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        DSU dsu = new DSU(n + 1);
        for (int i = 1; i <= n; i++) {
          int val = sc.nextInt();
          // i and val are in same component
          dsu.union(i, val);
        }
        System.out.println(dsu.totalComponent - 1);
      }
    }
  }

  static class DSU {
    int[] parent;
    int[] size;
    int totalComponent;

    DSU(int n) {
      parent = new int[n];
      size = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
      totalComponent = n;
    }

    boolean sameGroup(int a, int b) {
      return root(a) == root(b);
    }

    void union(int a, int b) {
      int par1 = root(a);
      int par2 = root(b);
      if(par1 != par2)
        totalComponent--;
      int sz1 = size[par1];
      int sz2 = size[par2];
      if (sz1 >= sz2) {
        int t = par1;
        par1 = par2;
        par2 = t;
      }
      parent[par1] = par2;
      size[par2] += size[par1];
    }

    int root(int a) {
      return parent[a] = a == parent[a] ? a : root(parent[a]);
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