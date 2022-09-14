package codeforce.div3.r748;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayDeque;
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
 * @see <a href="https://codeforces.com/contest/1593/problem/E" target="_top">https://codeforces.com/contest/1593/problem/E</a>
 * @since 13/10/21 9:51 PM
 * <p>
 * learning: never use max size for array declaration in grpah adjacency list. use n - number of nodes only.
 * array initialization can give tle
 * <p>
 * concept of in degree and out degree. when we are dealing with non directed graph, then in/out degree doesn't make sense. then we can
 * use a common degree ds to specify number of connected neighbors for each node.
 * <p>
 * for directed graph only it makes sense to use in and out degree separately.
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      StringBuilder ans = new StringBuilder();
      for (int tt = 1; tt <= T; tt++) {
        //sc.next();
        int n = sc.nextInt();
        int k = sc.nextInt();
        if (n == 1) {
          System.out.println(0);
          continue;
        }
        ArrayList<Integer>[] al = new ArrayList[n];
        int[] inD = new int[n];
        Set<Integer> nodes = new HashSet<>();
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          if (al[u] == null) al[u] = new ArrayList<>();
          if (al[v] == null) al[v] = new ArrayList<>();
          al[u].add(v);
          al[v].add(u);
          inD[u]++;
          inD[v]++;
          nodes.add(u);
          nodes.add(v);
        }
        ArrayDeque<Integer> start = new ArrayDeque<>();
        for (int node : nodes) {
          if (inD[node] == 1) {
            start.add(node);
            inD[node]--;
          }
        }
        for (int i = 0; i < k && !start.isEmpty(); i++) {
          int sz = start.size();
          for (int j = 0; j < sz; j++) {
            int u = start.removeFirst();
            n--;
            for (int v : al[u]) {
              inD[v]--;
              if (inD[v] == 1) { // this makes sure that each node goes only once in the queue
                start.add(v);
              }
            }
          }
        }
        System.out.println(n);
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