package codeforce.practice.unacadamy.r3;

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
import java.util.List;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/gym/346944/problem/B" target="_top">https://codeforces.com/gym/346944/problem/B</a>
 * @since 30/09/21 10:38 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  static int even;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        even = 0;
        int n = sc.nextInt();
        ArrayList<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
          adjacencyList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          adjacencyList[u].add(v);
          adjacencyList[v].add(u);
        }
        dfs(adjacencyList, 0, 0, 1);
        long ans = (long) (n - even) * even - (n - 1);
        System.out.println(ans);
      }
    }
  }

  private static void dfs(ArrayList<Integer>[] adjacencyList, int node, int p, int level) {
    even += level;
    for (int v : adjacencyList[node]) {
      if (p != v) {
        dfs(adjacencyList, v, node, 1 - level);
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