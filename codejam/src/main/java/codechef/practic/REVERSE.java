package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
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
 * @see <a href="https://www.codechef.com/problems/REVERSE" target="_top">https://www.codechef.com/problems/REVERSE</a>
 * @since 23/09/21 6:00 PM
 */
public class REVERSE {
  static FastScanner sc = new FastScanner(System.in);
  static Set<Edge>[] al;
  static int[] dist;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        al = new HashSet[n];
        dist = new int[n];
        for (int i = 0; i < n; i++) {
          al[i] = new HashSet<>();
        }
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          if (u == v)
            continue;
          al[u].add(new Edge(v, 0));
          al[v].add(new Edge(u, 1));
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(0);
        dist[0] = 0;
        while (!deque.isEmpty()) {
          int node = deque.removeFirst();
          for (Edge edge : al[node]) {
            if (dist[node] + edge.w < dist[edge.v]) {
              dist[edge.v] = dist[node] + edge.w;
              if (edge.w == 0)
                deque.addFirst(edge.v);
              else
                deque.addLast(edge.v);
            }
          }
        }
        System.out.println(dist[n - 1] == Integer.MAX_VALUE / 2 ? -1 : dist[n - 1]);
      }
    }
  }

  static class Edge {
    int v, w;

    Edge(int v, int w) {
      this.v = v;
      this.w = w;
    }

    @Override
    public int hashCode() {
      return Objects.hash(v, w);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || this.getClass() != o.getClass())
        return false;
      Edge other = (Edge) o;
      return this.v == other.v && this.w == other.w;
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