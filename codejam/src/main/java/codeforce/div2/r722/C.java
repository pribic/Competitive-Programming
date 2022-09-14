package codeforce.div2.r722;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1529/problem/C" target="_top">https://codeforces.com/contest/1529/problem/C</a>
 * @since 24/05/21 9:29 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] limits = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
          limits[i][0] = sc.nextInt();
          limits[i][1] = sc.nextInt();
        }
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          tree.putIfAbsent(u, new ArrayList<>());
          tree.putIfAbsent(v, new ArrayList<>());
          tree.get(u).add(v);
          tree.get(v).add(u);
        }
        Pair ans = dfs(limits, tree, 1, 0);
        System.out.println(Math.max(ans.l, ans.r));
      }
    }
  }

  /*
   we are asking node to give its best ans for its left and right boundaries given we have chosen dir for parent
   */
  private static Pair dfs(int[][] limits, Map<Integer, List<Integer>> tree, int node, int p) {
    Pair pair = new Pair();
    for (int v : tree.get(node)) {
      if (v != p) {
        Pair child = dfs(limits, tree, v, node);
        pair.l += Math.max(abs(limits[node][0] - limits[v][0]) + child.l, abs(limits[node][0] - limits[v][1]) + child.r);
        pair.r += Math.max(abs(limits[node][1] - limits[v][0]) + child.l, abs(limits[node][1] - limits[v][1]) + child.r);
      }
    }
    return pair;
  }

  //give me best ans for node's dir direction

  static class Pair {
    long l, r, id;

    public Pair(long l, long r, int id) {
      this.l = l;
      this.r = r;
      this.id = id;
    }

    public Pair() {

    }


    @Override
    public String toString() {
      return "[" + l + "," + r + "]";
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