package codeforce.div2.r165;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1351/C" target="_top">https://codeforces.com/problemset/problem/1351/C</a>
 * @since 16/05/22 6:20 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static Map<Character, int[]> direction = new HashMap<>();

  public static void main(String[] args) {
    direction.put('N', new int[]{-1, 0});
    direction.put('E', new int[]{0, 1});
    direction.put('S', new int[]{1, 0});
    direction.put('W', new int[]{0, -1});
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        int x = 0;
        int y = 0;
        Set<Edge> visited = new HashSet<>();
        int cost = 0;
        for (char c : s.toCharArray()) {
          int[] curdir = direction.get(c);
          Vertex source = new Vertex(x, y);
          x += curdir[0];
          y += curdir[1];
          Vertex dest = new Vertex(x, y);
          Edge edge1 = new Edge(source, dest);
          Edge edge2 = new Edge(dest, source);
          if (visited.add(edge1) || visited.add(edge2)) {
            cost += 5;
          } else {
            cost += 1;
          }
          visited.add(edge1);
          visited.add(edge2);
          //System.out.println("visited = " + visited);
        }
        System.out.println(cost);
      }
    }
  }

  static class Edge {
    Vertex to, from;

    public Edge(Vertex to, Vertex from) {
      this.to = to;
      this.from = from;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Edge edge = (Edge) o;
      return Objects.equals(to, edge.to) && Objects.equals(from, edge.from);
    }

    @Override
    public int hashCode() {
      return Objects.hash(to, from);
    }
  }

  static class Vertex {
    int x, y;

    public Vertex(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Vertex point = (Vertex) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
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