package codeforce.practice;

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
 * @see <a href="https://codeforces.com/contest/315/problem/A" target="_top">https://codeforces.com/contest/315/problem/A</a>
 * @since 17/01/22 8:44 PM
 */
public class p315_A {
  static FastScanner sc = new FastScanner(System.in);
  static ArrayList<Integer>[] al;
  static boolean[] visited;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        al = new ArrayList[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++)
          al[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          if (u != v)
            al[u].add(v);
        }
        for (int i = 0; i < n; i++) {
            dfs(i);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
          if(!visited[i])
            cnt++;
        }
        System.out.println(cnt);
      }
    }
  }

  private static void dfs(int node) {
    for (int v : al[node]) {
      if (!visited[v]) {
        visited[node] = true;
        dfs(v);
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