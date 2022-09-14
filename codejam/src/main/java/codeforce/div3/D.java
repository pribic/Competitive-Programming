package codeforce.div3;

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
 * @see <a href="https://codeforces.com/contest/1675/problem/D" target="_top">https://codeforces.com/contest/1675/problem/D</a>
 * @since 06/05/22 12:07 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] parent = new int[n + 1];
        Set<Integer> leaf = new HashSet<>();
        for (int i = 1; i <= n; i++) {
          leaf.add(i);
        }
        for (int i = 1; i <= n; i++) {
          parent[i] = sc.nextInt();
          if (i != parent[i])
            leaf.remove(parent[i]);
        }
        //find leaf nodes
        //whichever node have out degree of 1 are leaf nodes
        boolean[] visited = new boolean[n + 1];
        System.out.println(leaf.size());
        StringBuilder ans = new StringBuilder();
        for (int i : leaf) {
          //found leaf node, now traverse entire hierarchy
          List<Integer> path = new ArrayList<>();
          while (!visited[i]) {
            path.add(i);
            visited[i] = true;
            i = parent[i];
          }
          Collections.reverse(path);
          ans.append(path.size()).append("\n");
          for (int p : path)
            ans.append(p).append(" ");
          ans.append("\n");
        }
        System.out.println(ans);
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