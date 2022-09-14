package codeforce.practice;

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
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
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
import static java.util.stream.Collectors.toList;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1196/F" target="_top">https://codeforces.com/problemset/problem/1196/F</a>
 * @since 02/10/21 8:35 PM
 */
public class p1196F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int kk = sc.nextInt();
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          int w = sc.nextInt();
          edges.add(new int[]{w, u, v});
        }

        edges.sort(comparingInt(a -> a[0]));

        List<int[]> topEdges = new ArrayList<>();
        for (int i = 0; i < Math.min(edges.size(), 400); i++) {
          topEdges.add(edges.get(i));
        }
        Set<Integer> vertices = new HashSet<>();
        for (int[] e : topEdges) {
          int w = e[0], u = e[1], v = e[2];
          vertices.add(u);
          vertices.add(v);
        }

        int reducedN = vertices.size();
        long[][] dp = new long[reducedN][reducedN];
        Map<Integer, Integer> compress = new HashMap<>();
        for (int v : vertices)
          compress.put(v, compress.size());

        for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE);
        for (int i = 0; i < reducedN; i++) {
          dp[i][i] = 0; // no self loops
        }
        //System.out.println("top edges");
        for (int[] e : topEdges) {
          int w = e[0], u = compress.get(e[1]), v = compress.get(e[2]);
          //System.out.println(u + " " + v + " " + w);
          dp[u][v] = dp[v][u] = w; //set up initial given direct edges
        }

        //floyd warshall
        for (int k = 0; k < reducedN; k++) {
          for (int i = 0; i < reducedN; i++) {
            for (int j = 0; j < reducedN; j++) {
              if (dp[i][k] < Long.MAX_VALUE && dp[k][j] < Long.MAX_VALUE)
                dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
            }
          }
        }
        
        List<Long> sortedWeights = Arrays.stream(dp)
          .flatMapToLong(Arrays::stream)
          .filter(x -> x > 0)
          .boxed()
          .sorted()
          .limit(Math.min(2 * kk - 1, 400 * 2 - 1) + 1)
          .collect(toList());
        //System.out.println("sortedWeights = " + sortedWeights);
        System.out.println(sortedWeights.get(sortedWeights.size() - 1)); // since same edge might have been considered twice, 2 need 2 * (k - 1)


      }
    }
  }

  static class Edge {
    int v;
    int w;

    public Edge(int v, int w) {
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