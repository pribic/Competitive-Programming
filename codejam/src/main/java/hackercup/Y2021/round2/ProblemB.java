package hackercup.Y2021.round2;

import diff.A;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ProblemB {
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/chainblock_validation_input.txt"));

  static int ans = 0;

  static int[] log;

  static {
    log = new int[800001];
    for (int i = 2; i < log.length; i++)
      log[i] = log[i / 2] + 1;
  }

  public static void main(String[] args) throws IOException {
    File f = new File("/Users/pdoshi/hackercup/q2021/problemBChainblock_" + System.currentTimeMillis() + ".txt");
    //File f = new File("./problemA1" + System.currentTimeMillis() + ".txt");
    f.createNewFile();
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      //System.out.println("tt = " + tt);
      ans = 0;
      FileWriter fw = new FileWriter(f, true);
      fw.append("Case #" + tt + ": ");
      int n = sc.nextInt();
      ArrayList<Integer>[] tree = new ArrayList[n + 1];
      int[] nodeF = new int[n + 1];
      for (int i = 0; i < n - 1; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        if (tree[u] == null)
          tree[u] = new ArrayList<>();
        if (tree[v] == null)
          tree[v] = new ArrayList<>();
        tree[u].add(v);
        tree[v].add(u);
      }
      for (int i = 0; i < n; i++) {
        nodeF[i + 1] = sc.nextInt();
      }
      int[] parent = new int[n + 1];
      dfs(tree, parent, 1, 1);
      int[][] st = new int[n + 1][20];
      for (int i = 1; i < n + 1; i++)
        st[i][0] = parent[i];
      for (int j = 1; j < 20; j++) {
        for (int i = 1; i < st.length; i++) {
          st[i][j] = st[st[i][j - 1]][j - 1];
        }
      }
      Map<Integer, List<Integer>> freqVsIdx = new HashMap<>();
      for (int i = 1; i < nodeF.length; i++) {
        int fr = nodeF[i];
        freqVsIdx.putIfAbsent(fr, new ArrayList<>());
        freqVsIdx.get(fr).add(i);
      }

      //setting up +1 at nodes and # of frequencies as - in lca node.
      int[] vals = new int[n + 1];
      for (int key : freqVsIdx.keySet()) {
        if (freqVsIdx.get(key).size() > 1) {
          List<Integer> idxes = freqVsIdx.get(key);
          int curLca = idxes.get(0);
          for (int i = 1; i < idxes.size(); i++) {
            curLca = lca(curLca, idxes.get(i), st);
          }
          vals[curLca] -= idxes.size();
          for (int i = 0; i < idxes.size(); i++) {
            vals[idxes.get(i)]++;
          }
        }
      }
      dfs1(tree, 1, 1, vals);
      print(ans, fw);
      print("\n", fw);
      fw.close();
    }

  }

  private static void dfs1(ArrayList<Integer>[] tree, int node, int parent, int[] vals) {
    for (int v : tree[node]) {
      if (v != parent) {
        dfs1(tree, v, node, vals);
        vals[node] += vals[v];
      }
    }
    if (vals[node] == 0)
      ans++;
  }

  /**
   * returns lca of node a and b using sparse table built over parent array
   */
  static int lca(int a, int b, int[][] st) {
    int l = 0;
    int r = (int) Math.pow(2, 20);
    while (r > l + 1) {
      int mid = l + (r - l) / 2;
      if (parent(a, mid, st) == parent(b, mid, st))
        r = mid;
      else
        l = mid;
    }
    return parent(a, r, st);
  }

  private static int parent(int a, int n, int[][] parent) {
    //return nth parent of a
    int p = a;
    for (int j = 0; j < 20; j++) {
      if (((n >> j) & 1) == 1) {
        p = parent[p][j];
      }
    }
    return p;
  }

  private static void dfs(ArrayList<Integer>[] tree, int[] parent, int node, int p) {
    parent[node] = p;
    for (int v : tree[node]) {
      if (v != p) {
        dfs(tree, parent, v, node);
      }
    }
  }

  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.println(o);
    fw.write(o.toString());
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


