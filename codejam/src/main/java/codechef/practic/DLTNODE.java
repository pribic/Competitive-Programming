package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.ArrayList;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/CSNS21B/problems/DLTNODE" target="_top">https://www.codechef.com/CSNS21B/problems/DLTNODE</a>
 * @since 04/11/21 1:29 PM
 */
public class DLTNODE {
  static FastScanner sc = new FastScanner(System.in);
  static int[] inDp; // inDp[u] = gcd of u and all the nodes present in its subtree
  static int[] outDp; // outDp[u] = gcd of entire tree except this node and its subtree
  static long[] sumInDp; // sumInDp[u] = sum of all inDp[v] such that v is children of u
  static int[] vals;
  static long maxGcdSum;
  static ArrayList<Integer>[] edges;
  static Map<Integer, int[]> prefixGcds;
  static Map<Integer, int[]> sufixGcds;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        maxGcdSum = 0;
        int n = sc.nextInt();
        edges = new ArrayList[n + 1];
        for (int i = 0; i < edges.length; i++)
          edges[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt(), v = sc.nextInt();
          edges[u].add(v);
          edges[v].add(u);
        }
        vals = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
          vals[i] = sc.nextInt();
        }

        inDp = new int[n + 1];
        outDp = new int[n + 1];
        sumInDp = new long[n + 1];
        prefixGcds = new HashMap<>();
        sufixGcds = new HashMap<>();

        edges[1].add(0);
        edges[0].add(1);
        prefixGcds.put(0, new int[]{0});
        sufixGcds.put(0, new int[]{0});

        dfsIn(1, 0);
        dfsPrefixGcd(1, 0);
        dfsSuffixGcd(1, 0);
        dfsOut(1, 0, 0);
        dfsSum(1, 0);
        dfs2(1, 0);
        // System.out.println(Arrays.toString(inDp));
        // System.out.println(Arrays.toString(outDp));
        System.out.println(maxGcdSum);
      }
    }
  }

  private static void dfsSum(int u, int parent) {
    for (int v : edges[u]) {
      if (v != parent) {
        sumInDp[u] += inDp[v];
        dfsSum(v, u);
      }
    }
  }

  /*
  1
5
1 2
1 3
3 4
3 5
4 6 8 12 15
[0, 1, 6, 1, 12, 15]
[0, 4, 1, 2, 1, 1]
29
   */

  private static void dfs2(int u, int parent) {
    maxGcdSum = Math.max(maxGcdSum, outDp[u] + sumInDp[u]);
    for (int v : edges[u]) {
      if (v != parent)
        dfs2(v, u);
    }
  }

  private static void dfsIn(int u, int parent) {
    inDp[u] = vals[u];
    for (int v : edges[u]) {
      if (v != parent) {
        dfsIn(v, u);
        inDp[u] = gcd(inDp[u], inDp[v]);
      }
    }
  }

  private static void dfsPrefixGcd(int u, int parent) {
    int childrenCount = edges[u].size();
    int[] pGcd = new int[childrenCount];
    int curGcd = 0;
    for (int i = 0; i < edges[u].size(); i++) {
      int v = edges[u].get(i);
      if (v != parent) {
        pGcd[i] = curGcd;
        curGcd = gcd(curGcd, vals[v]);
      }
    }
    prefixGcds.put(u, pGcd);
    for (int v : edges[u])
      if (v != parent)
        dfsPrefixGcd(v, u);
  }

  private static void dfsSuffixGcd(int u, int parent) {
    int childrenCount = edges[u].size();
    int[] pGcd = new int[childrenCount];
    int curGcd = 0;
    for (int i = edges[u].size() - 1; i >= 0; i--) {
      int v = edges[u].get(i);
      if (v != parent) {
        pGcd[i] = curGcd;
        curGcd = gcd(curGcd, vals[v]);
      }
    }
    sufixGcds.put(u, pGcd);
    for (int v : edges[u])
      if (v != parent)
        dfsSuffixGcd(v, u);
  }

  private static void dfsOut(int u, int parent, int idx) {
    outDp[u] = gcd(outDp[parent], vals[parent], prefixGcds.get(parent)[idx], sufixGcds.get(parent)[idx]);
    for (int i = 0; i < edges[u].size(); i++) {
      int v = edges[u].get(i);
      if (v != parent) {
        dfsOut(v, u, i);
      }
    }
  }

  private static int gcd(int... arr) {
    int gcd = arr[0];
    for (int i = 1; i < arr.length; i++)
      gcd = gcd(gcd, arr[i]);
    return gcd;
  }

  private static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
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