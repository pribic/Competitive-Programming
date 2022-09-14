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

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/gym/346944/problem/A" target="_top">https://codeforces.com/gym/346944/problem/A</a>
 * @since 30/09/21 9:59 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  static int ans = 0;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        ans = 0;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        ArrayList<Integer>[] adjacencyList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
          adjacencyList[i] = new ArrayList();
        }
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          adjacencyList[u].add(v);
          adjacencyList[v].add(u);
        }
        dfs(adjacencyList, 0, 0, arr, m, 0);
        System.out.println(ans);
      }

    }
  }

  private static void dfs(ArrayList<Integer>[] adjacencyList, int node, int p, int[] arr, int m, int cur) {
    if (arr[node] == 1)
      cur++;
    if (cur > m)
      return;
    boolean isLeaf = true;
    for (int v : adjacencyList[node]) {
      if (v != p) {
        isLeaf = false;
        if (arr[v] == 1)
          dfs(adjacencyList, v, node, arr, m, cur);
        else
          dfs(adjacencyList, v, node, arr, m, 0);
      }
    }
    if (isLeaf)
      ans++;
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