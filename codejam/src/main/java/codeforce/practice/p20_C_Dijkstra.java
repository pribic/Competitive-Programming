package codeforce.practice;

import codechef.longC.NOV21.MAXBRIDGE;

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
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collector;
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
 * @see <a href="https://codeforces.com/problemset/problem/20/C" target="_top">https://codeforces.com/problemset/problem/20/C</a>
 * @since 13/11/21 2:58 PM
 */
public class p20_C_Dijkstra {
  static FastScanner sc = new FastScanner(System.in);
  static ArrayList<int[]>[] al;
  static int[] dist;
  static int[] parent;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        al = new ArrayList[n + 1];
        dist = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i < al.length; i++) {
          al[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          int w = sc.nextInt();
          al[u].add(new int[]{v, w});
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        parent[1] = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(a -> a[0])); // dist, node
        pq.add(new int[]{0, 1});
        while (!pq.isEmpty()) {
          int[] top = pq.remove();
          int d = top[0];
          int u = top[1];
          //if()
          int mod = 1;
          PriorityQueue<long[]> pq1 = new PriorityQueue<>((a, b) -> (int)((a[0] - b[0] + mod) % mod));
          for (int[] v : al[u]) {
            if (d + v[1] < dist[v[0]]) {
              dist[v[0]] = d + v[1];
              parent[v[0]] = u;
              pq.add(new int[]{dist[v[1]], v[0]});
            }
          }
        }
        int node = n;
        while (parent[node] != node) {
          System.out.print(node + " ");
          node = parent[node];
        }
        System.out.println(dist[n]);
      }
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