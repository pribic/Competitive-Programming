package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/problems/AERTS" target="_top">https://www.codechef.com/problems/AERTS</a>
 * @since 07/10/21 9:13 PM
 */
public class AERTS {
  static FastScanner sc = new FastScanner(System.in);

  static boolean[] visited;
  static ArrayList<Integer>[] al;
  static ArrayList<Integer>[] rAl;
  static int time;
  static int[] scc_no;
  static List<Integer> nodeOrder;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        time = 0;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] tax = new int[n];
        al = new ArrayList[n];
        rAl = new ArrayList[n];
        visited = new boolean[n];
        scc_no = new int[n];
        nodeOrder = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          tax[i] = sc.nextInt();
          al[i] = new ArrayList<>();
          rAl[i] = new ArrayList<>();
          scc_no[i] = -1;
        }
        for (int i = 0; i < m; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          al[u].add(v);
          rAl[v].add(u);
        }
        for (int i = 0; i < n; i++) {
          if (!visited[i])
            dfs(i);
        }
        //System.out.println(nodeOrder);
        int id = 1;
        for (int i = n - 1; i >= 0; i--) {
          if (scc_no[nodeOrder.get(i)] == -1) {
            // find an SCC from node i and make node i as head of this group
            reverseDfs(nodeOrder.get(i), id++);
          }
        }
       // System.out.println(Arrays.toString(scc_no));
        Map<Integer, Long> componentSum = new HashMap<>();
        for (int i = 0; i < scc_no.length; i++) {
          int sccId = scc_no[i];
          componentSum.put(sccId, componentSum.getOrDefault(sccId, 0L) + tax[i]);
        }
       // System.out.println("componentSum = " + componentSum);
        int maxId = 1;
        List<Long> sums = new ArrayList<>(componentSum.values());
        Collections.sort(sums);
        long ans = 0;
        for (long sum : sums) {
          ans += sum * maxId;
          maxId++;
        }
        System.out.println(ans);
      }
    }
  }

  private static void dfs(int node) {
    visited[node] = true;
    for (int v : al[node]) {
      if (!visited[v])
        dfs(v);
    }
    nodeOrder.add(node);
  }

  private static void reverseDfs(int node, int id) {
    scc_no[node] = id;
    for (int v : rAl[node]) {
      if (scc_no[v] == -1)
        reverseDfs(v, id);
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