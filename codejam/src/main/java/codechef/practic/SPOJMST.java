package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
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
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.spoj.com/problems/MST/" target="_top">https://www.spoj.com/problems/MST/</a>
 * @since 21/05/21 5:00 PM
 */
public class SPOJMST {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Edge[] edges = new Edge[m];
        Map<Integer, List<Edge>> adjacencyList = new HashMap<>();
        for (int i = 0; i < m; i++) {
          edges[i] = new Edge(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
          adjacencyList.putIfAbsent(edges[i].u, new ArrayList<>());
          adjacencyList.putIfAbsent(edges[i].v, new ArrayList<>());
          adjacencyList.get(edges[i].u).add(edges[i]);
          adjacencyList.get(edges[i].v).add(edges[i]);
        }
        PriorityQueue<Edge> activeEdge = new PriorityQueue<>(comparingInt(e -> e.w));
        Set<Integer> visited = new HashSet<>();
        for (Edge edge : adjacencyList.get(0)) {
          activeEdge.add(new Edge(0, edge.u != 0 ? edge.u : edge.v, edge.w));
        }
        visited.add(0);
        long mst = 0l;
        while (!activeEdge.isEmpty()) {
          Edge nxt = activeEdge.remove();
          if (visited.contains(nxt.u) && visited.contains(nxt.v))
            continue;
          int nw = visited.contains(nxt.u) ? nxt.v : nxt.v;
          visited.add(nxt.u);
          visited.add(nxt.v);
          mst += nxt.w;
          for (Edge edge : adjacencyList.get(nw)) {
            if (!visited.contains(edge.u) || !visited.contains(edge.v))
              activeEdge.add(edge);
          }
        }
        System.out.println(mst);
      }
    }
  }

  static class Edge {
    int u, v, w;

    public Edge(int u, int v, int w) {
      this.u = u;
      this.v = v;
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
      br = new BufferedReader(new InputStreamReader(f));
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