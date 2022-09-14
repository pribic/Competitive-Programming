package hackerearth.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
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
 * @see <a href="https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/components-of-graph-2b95e067/" target="_top">https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/components-of-graph-2b95e067/</a>
 * @since 08/10/21 2:47 PM
 */
public class ComponentsOfGraphAirBus {
  static FastScanner sc = new FastScanner(System.in);

  static boolean[] visited;
  static int[] scc_no;
  static ArrayList<Integer>[] al;
  static ArrayList<Integer>[] rAl;
  static List<Integer> nodeOrder;
  static int maxACI;
  static int[] aci;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        aci = new int[n + 1];
        al = new ArrayList[n + 1];
        rAl = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
          aci[i] = sc.nextInt();
          al[i] = new ArrayList<>();
          rAl[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt(), v = sc.nextInt();
          al[u].add(v);
          rAl[v].add(u);
        }
        int l = 0;
        int r = Integer.MAX_VALUE;
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (check(mid, n, k))
            l = mid;
          else
            r = mid;
        }
        System.out.println(l);
      }
    }
  }

  // 1 based indexing
  static class Graph {
    int n;
    int m;
    ArrayList<Edge>[] al;

    Graph(int n, int m) {
      this.n = n;
      this.m = sc.nextInt();
      al = new ArrayList[n + 1];
      for (int i = 0; i < n + 1; i++) {
        al[i] = new ArrayList<>();
      }
    }

    void readDirectedEdges(Scanner sc) {
      for (int i = 0; i < m; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        al[u].add(new Edge(v, 0));
      }
    }

    void readUndirectedEdges(Scanner sc) {
      for (int i = 0; i < m; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        al[u].add(new Edge(v, 0));
        al[v].add(new Edge(u, 0));
      }
    }
  }

  static class Edge {
    int to, w;

    public Edge(int to, int w) {
      this.to = to;
      this.w = w;
    }
  }

  private static boolean check(int mid, int n, int k) {
    out.println(mid);
    maxACI = mid;
    visited = new boolean[n + 1];
    scc_no = new int[n + 1];
    nodeOrder = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (!visited[i] && aci[i] >= maxACI)
        dfs(i);
    }
    Collections.reverse(nodeOrder);
    int id = 1;
    for (int node : nodeOrder) {
      if (scc_no[node] == 0 && aci[node] >= maxACI) {
        reverseDfs(node, id++);
      }
    }
    Map<Integer, Integer> sccSize = new HashMap<>();
    for (int i = 1; i < scc_no.length; i++)
      if (scc_no[i] > 0)
        sccSize.put(scc_no[i], sccSize.getOrDefault(scc_no[i], 0) + 1);
    for (int val : sccSize.values())
      if (val >= k)
        return true;

    return false;
  }

  private static void reverseDfs(int node, int id) {
    scc_no[node] = id;
    for (int v : rAl[node]) {
      if (scc_no[v] == 0 && aci[v] >= maxACI)
        reverseDfs(v, id);
    }
  }

  private static void dfs(int node) {
    visited[node] = true;
    for (int v : al[node]) {
      if (!visited[v] && aci[v] >= maxACI)
        dfs(v);
    }
    nodeOrder.add(node);
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