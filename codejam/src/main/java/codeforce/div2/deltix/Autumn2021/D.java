package codeforce.div2.deltix.Autumn2021;

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
import java.util.TreeMap;
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1609/problem/D" target="_top">https://codeforces.com/contest/1609/problem/D</a>
 * @since 28/11/21 9:11 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int d = sc.nextInt();
        DSU dsu = new DSU(n);
        int pending = 1;
        while (d-- > 0) {
          int a = sc.nextInt();
          int b = sc.nextInt();
          if (!dsu.union(a, b)) {
            pending++;
          }
          int ans = sumTopFreq(dsu, pending);
          System.out.println(ans - 1);
        }
      }
    }
  }

  private static int sumTopFreq(DSU dsu, int pending) {
    int ans = 0;
    //sum top pending sizes
    int times = 0;
    outer:
    for (int key : dsu.sizes.descendingKeySet()) {
      for (int i = 0; i < dsu.sizes.get(key); i++) {
        ans += key;
        times++;
        if (times == pending)
          break outer;
      }
    }
    return ans;
  }

  static class DSU {
    int[] parent;
    int[] size;
    TreeMap<Integer, Integer> sizes;

    DSU(int n) {
      parent = new int[n + 1];
      size = new int[n + 1];
      sizes = new TreeMap<>();
      for (int i = 1; i <= n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
      sizes.put(1, n);
    }

    boolean union(int a, int b) {
      int pa = get(a);
      int pb = get(b);
      if (pa == pb)
        return false;
      int sa = size[pa];
      int sb = size[pb];
      if (sa < sb) {
        //swap
        int t = pa;
        pa = pb;
        pb = t;
      }
      remove(size[pb]);
      remove(size[pa]);
      parent[pb] = pa;
      size[pa] += size[pb];
      add(size[pa]);
      return true;
    }

    private void add(int num) {
      sizes.put(num, sizes.getOrDefault(num, 0) + 1);
    }

    private void remove(int num) {
      sizes.put(num, sizes.get(num) - 1);
    }

    int get(int a) {
      return parent[a] = (a == parent[a] ? a : get(parent[a]));
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