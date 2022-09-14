package hackerearth.practice;

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
 * @see <a href="https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/gcd-on-directed-graph-1122228a/" target="_top">https://www.hackerearth.com/practice/algorithms/graphs/strongly-connected-components/practice-problems/algorithm/gcd-on-directed-graph-1122228a/</a>
 * @since 08/10/21 3:35 PM
 */
public class GCDOnDirectedGraph {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Graph graph = new Graph(n, m);
        int[] cost = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
          cost[i] = sc.nextInt();
        }
        graph.readDirectedEdges();
        
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