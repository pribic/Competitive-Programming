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
 * @see <a href="https://codeforces.com/contest/1534/problem/D" target="_top">https://codeforces.com/contest/1534/problem/D</a>
 * @since 14/06/21 1:53 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] baseQuery = query(1, n);
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
          if (baseQuery[i] % 2 == 0) {
            odd.add(i);
          } else {
            even.add(i);
          }
        }
        Map<Integer, List<Integer>> edges = new HashMap<>();
        process(edges, baseQuery, 1);
        List<Integer> queried = odd.size() <= even.size() ? odd : even;
        for (int node : queried) {
          if (node != 1) {
            int[] queryResult = query(node, n);
            process(edges, queryResult, node);
          }
        }
        System.out.println("!");
        boolean[][] visited = new boolean[n + 1][n + 1];
        for (int u : edges.keySet())
          for (int v : edges.get(u)) {
            if (!visited[u][v] && !visited[v][u]) {
              System.out.println(u + " " + v);
              visited[u][v] = true;
              visited[v][u] = true;
            }
          }
      }
    }
  }

  private static void process(Map<Integer, List<Integer>> edges, int[] baseQuery, int node) {
    for (int i = 1; i < baseQuery.length; i++) {
      if (baseQuery[i] == 1) {
        edges.putIfAbsent(node, new ArrayList<>());
        edges.get(node).add(i);
      }
    }
  }


  private static int[] query(int node, int n) {
    out.println("? " + node);
    int[] arr = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      arr[i] = sc.nextInt();
    }
    return arr;
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