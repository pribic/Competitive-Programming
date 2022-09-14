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
 * @see <a href="https://codeforces.com/problemset/problem/427/C" target="_top">https://codeforces.com/problemset/problem/427/C</a>
 * @since 08/10/21 4:33 PM
 */
public class p427C {
  static FastScanner sc = new FastScanner(System.in);

  static boolean[] visited;
  static int[] scc_no;
  static List<Integer> order;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] cost = new int[n + 1];
        visited = new boolean[n + 1];
        scc_no = new int[n + 1];
        order = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
          cost[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        Graph graph = new Graph(n, m);
        graph.readDirectedEdges();
        for (int i = 1; i <= n; i++) {
          if (!visited[i])
            dfs(i, graph);
        }
        Collections.reverse(order);
        //System.out.println("order = " + order);
        int id = 1;
        Graph reverse = graph.reverseGraph();
        for (int i = 0; i < n; i++) {
          if (scc_no[order.get(i)] == 0)
            reverseDfs(order.get(i), reverse, id++);
        }
        Map<Integer, Integer> sccToMin = new HashMap<>();
        for (int i = 1; i <= n; i++) {
          int sccId = scc_no[i];
          sccToMin.put(sccId, Math.min(sccToMin.getOrDefault(sccId, Integer.MAX_VALUE), cost[i]));
        }

        //System.out.println("sccToMin = " + sccToMin);
        long minCost = sccToMin.values().stream().mapToLong(i -> i).reduce(Long::sum).getAsLong();
        long ways = 1;
        Map<Integer, Integer> sccToMinFreq = new HashMap<>();
        for (int i = 1; i <= n; i++) {
          int sccId = scc_no[i];
          if (cost[i] == sccToMin.get(sccId)) {
            sccToMinFreq.put(sccId, sccToMinFreq.getOrDefault(sccId, 0) + 1);
          }
        }
        for (int val : sccToMinFreq.values()) {
          ways = (ways * val) % 1000000007;
        }
        System.out.println(minCost + " " + ways);
      }
    }
  }

  private static void reverseDfs(Integer node, Graph graph, int id) {
    scc_no[node] = id;
    for (Edge e : graph.al[node]) {
      if (scc_no[e.to] == 0)
        reverseDfs(e.to, graph, id);
    }
  }

  private static void dfs(int node, Graph g) {
    visited[node] = true;
    for (Edge v : g.al[node]) {
      if (!visited[v.to])
        dfs(v.to, g);
    }
    order.add(node);
  }


  // 1 based indexing
  static class Graph {
    int n;
    int m;
    ArrayList<Edge>[] al;

    Graph(int n, int m) {
      this.n = n;
      this.m = m;
      al = new ArrayList[n + 1];
      for (int i = 0; i < n + 1; i++) {
        al[i] = new ArrayList<>();
      }
    }

    void readDirectedEdges() {
      for (int i = 0; i < m; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        al[u].add(new Edge(v, 0));
      }
    }

    void readUndirectedEdges() {
      for (int i = 0; i < m; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        al[u].add(new Edge(v, 0));
        al[v].add(new Edge(u, 0));
      }
    }

    Graph reverseGraph() {
      Graph reverse = new Graph(n, m);
      reverse.al = new ArrayList[n + 1];
      for (int i = 0; i < al.length; i++)
        reverse.al[i] = new ArrayList<>();
      for (int i = 0; i < n + 1; i++) {
        for (Edge e : al[i]) {
          //edge from i to e.to
          reverse.al[e.to].add(new Edge(i, e.w));
        }
      }
      return reverse;
    }
  }

  static class Edge {
    int to, w;

    public Edge(int to, int w) {
      this.to = to;
      this.w = w;
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