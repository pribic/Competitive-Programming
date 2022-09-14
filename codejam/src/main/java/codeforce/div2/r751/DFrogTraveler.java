package codeforce.div2.r751;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
 * @see <a href="https://codeforces.com/contest/1602/problem/D" target="_top">https://codeforces.com/contest/1602/problem/D</a>
 * @since 25/10/21 12:44 PM
 */
public class DFrogTraveler {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] jumps = new int[n];
        for (int i = 0; i < n; i++) {
          jumps[i] = sc.nextInt();
        }
        int[] slip = new int[n];
        for (int i = 0; i < n; i++) {
          slip[i] = sc.nextInt();
        }
        ArrayList<Integer>[] targetSlips = new ArrayList[n + 1];
        for (int i = 0; i < targetSlips.length; i++)
          targetSlips[i] = new ArrayList();
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        for (int i = 0; i < slip.length; i++) {
          targetSlips[i - slip[i]].add(i);
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
          if (jumps[i] == n - i) {
            dist[i] = 1;
            visited[i] = true;
            queue.add(i);
          }
        }
        while (!queue.isEmpty()) {
          int node = queue.removeFirst();
          for (int v : targetSlips[node]) {
            if (!visited[v]) {
              visited[v] = true;
              dist[v] = 1 + dist[node];
              queue.add(v);
            }
          }
        }
        System.out.println(dist[0]);
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