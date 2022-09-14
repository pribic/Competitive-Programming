package codeforce.div2.r758;

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
 * @see <a href="https://codeforces.com/contest/1608/problem/C" target="_top">https://codeforces.com/contest/1608/problem/C</a>
 * @since 11/12/21 5:04 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] ai = new int[n];
        for (int i = 0; i < n; i++) {
          ai[i] = sc.nextInt();
        }
        int[] bi = new int[n];
        for (int i = 0; i < n; i++) {
          bi[i] = sc.nextInt();
        }
        TreeSet<Pair> sorted = new TreeSet<>();
        int lastB = -1;
        for (int i = 0; i < n; i++) {
          sorted.add(new Pair(ai[i], bi[i], i));
        }
        lastB = sorted.last().b;
        int[] ans = new int[n];
        int leftMax = -1;
        for (Pair p : sorted) {
          // we are at i,
          int i = p.i;
          leftMax = Math.max(leftMax, bi[i]);
          if (leftMax >= lastB) {
            ans[i] = 1;
          } else {
            ans[i] = 0;
          }
        }
        StringBuilder sb = new StringBuilder();
        for (int aa : ans)
          sb.append(aa);
        System.out.println(sb);
      }

    }
  }


  static class Pair implements Comparable<Pair> {
    int a, b, i;

    public Pair(int a, int b, int i) {
      this.a = a;
      this.b = b;
      this.i = i;
    }

    @Override
    public int compareTo(Pair o) {
      return a - o.a;
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

/*
1
4
1 2 3 4
2 4 1 3
 */
//1 2 3 4
//