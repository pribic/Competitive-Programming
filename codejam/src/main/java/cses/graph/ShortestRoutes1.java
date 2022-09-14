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
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.ArrayList;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://cses.fi/problemset/task/1671" target="_top">https://cses.fi/problemset/task/1671</a>
 * @since 30/09/21 9:25 PM
 */
public class ShortestRoutes1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList[] adjacencyList = new ArrayList[n + 1];
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          int c = sc.nextInt();
          if (adjacencyList[u] == null)
            adjacencyList[u] = new ArrayList();
          adjacencyList[u].add(new int[]{v, c});
        }
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
          if (a[0] != b[0])
            return Long.compare(a[0], b[0]);
          return Long.compare(a[1], b[1]);
        });
        boolean[] visited = new boolean[n + 1];
        pq.add(new long[]{0, 1});
        dijkstra(1, dist, adjacencyList, pq, visited);
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; i++)
          ans.append(dist[i]).append(" ");
        System.out.println(ans);
      }
    }
  }

  private static void dijkstra(int node, long[] dist, ArrayList[] adjacencyList, PriorityQueue<long[]> pq, boolean[] visited) {
    visited[node] = true;
    if (adjacencyList[node] != null) {
      for (Object obj : adjacencyList[node]) {
        int v = ((int[]) obj)[0];
        int c = ((int[]) obj)[1];
        if (!visited[v] && dist[v] > dist[node] + c) {
          dist[v] = dist[node] + c;
          pq.add(new long[]{dist[v], v});
        }
      }
    }
    if (pq.size() > 0)
      dijkstra((int)pq.remove()[1], dist, adjacencyList, pq, visited);
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