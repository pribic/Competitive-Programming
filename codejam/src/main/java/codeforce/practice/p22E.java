package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/22/problem/E" target="_top">https://codeforces.com/contest/22/problem/E</a>
 * @since 08/10/21 4:33 PM
 */
public class p22E {
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
        Graph graph = new Graph(n, 0);
        for (int i = 1; i <= n; i++) {
          graph.al[i].add(new Edge(sc.nextInt(), 0));
        }
        for (int i = 1; i <= n; i++) {
          if (!visited[i])
            dfs(i, graph);
        }
        Collections.reverse(order);
        //System.out.println("order = " + order);
        Graph reverse = graph.reverseGraph();
        for (int i = 0; i < n; i++) {
          if (scc_no[order.get(i)] == 0)
            reverseDfs(order.get(i), reverse, order.get(i));
        }
        Map<Integer, Set<Integer>> al = new HashMap<>();
        //condensation graph
        for (int i = 1; i <= n; i++) {
          int scc1 = scc_no[i]; // i is part of scc1
          al.putIfAbsent(scc1, new HashSet<>());
          for (Edge e : graph.al[i]) {
            int scc2 = scc_no[e.to];
            if (scc1 != scc2) {
              al.get(scc1).add(scc2);
            }
          }
        }
        // find leaf components
        int[] inD = new int[al.size() + 1];
        int[] outD = new int[al.size() + 1];
        for (int u : al.keySet()) {
          for (int v : al.get(u)) {
            // edge u -> v
            outD[u]++;
            inD[v]++;
          }
        }

        List<Integer> leaf = new ArrayList<>();
        List<Integer> root = new ArrayList<>();
        for (int i = 1; i < inD.length; i++) {
          if (inD[i] == 0)
            root.add(scc_no[i]);
          if (outD[i] == 0)
            leaf.add(scc_no[i]);
        }
        System.out.println(leaf.size() + root.size() - 1);
        for (int i = 0; i < root.size() - 1; i++) {
          //add edge from root[i] to root[i + 1]
          System.out.println(root.get(i) + " " + root.get(i + 1));
        }
        for (int i = 0; i < leaf.size() - 1; i++) {
          // add edge from i - 1 to i
          System.out.println(leaf.get(i) + " " + leaf.get(i + 1));
        }
        System.out.println(root.get(root.size() - 1) + " " + leaf.get(0));
        if (leaf.size() > 1)
          System.out.println(leaf.get(leaf.size() - 1) + " " + root.get(0));

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