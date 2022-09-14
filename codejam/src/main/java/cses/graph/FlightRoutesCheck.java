package cses.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * @see <a href="https://cses.fi/problemset/task/1682" target="_top">https://cses.fi/problemset/task/1682</a>
 * @since 08/10/21 8:16 PM
 */
public class FlightRoutesCheck {
  static FastScanner sc = new FastScanner(System.in);

  static boolean[] visited;
  static int[] scc_no;
  static List<Integer> order;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        visited = new boolean[n + 1];
        scc_no = new int[n + 1];
        order = new ArrayList<>();
        Graph graph = new Graph(n, m);
        graph.readDirectedEdges();
        stronglyConnectedComponent(graph);
        Set<Integer> uniqueRoot = new HashSet<>();
        for (int sccId : scc_no)
          uniqueRoot.add(sccId);
        if (uniqueRoot.size() == 2)
          System.out.println("YES");
        else {
          System.out.println("NO");
          //System.out.println(order);
          //System.out.println(Arrays.toString(scc_no));
          int root = scc_no[order.get(0)];
          int another = -1;
          for(int num : scc_no)
            if(num != 0 && num != root) {
              another = num;
              break;
            }
          System.out.println(another + " " + root);
        }
      }
    }
  }

  private static void stronglyConnectedComponent(Graph graph) {
    for (int i = 1; i <= graph.n; i++) {
      if (!visited[i])
        dfs(i, graph);
    }
    Collections.reverse(order);
    Graph reverse = graph.reverseGraph();
    for (int node : order)
      if (scc_no[node] == 0)
        reverseDfs(node, reverse, node);
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