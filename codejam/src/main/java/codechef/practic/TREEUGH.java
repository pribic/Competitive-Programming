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
import java.util.StringTokenizer;
import java.util.ArrayList;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/problems/TREEUGH" target="_top">https://www.codechef.com/problems/TREEUGH</a>
 * @since 16/10/21 2:40 PM
 */
public class TREEUGH {
  static FastScanner sc = new FastScanner(System.in);

  static ArrayList<Integer>[] al;
  static int[] parent;
  static int[] vals;
  static int[] lazy;
  static int[] inT;
  static int[] outT;
  static int time = 0;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        vals = new int[n];
        lazy = new int[n];
        for (int i = 0; i < n; i++) {
          vals[i] = sc.nextInt();
        }
        al = new ArrayList[n];
        fill(al);
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt() - 1;
          int v = sc.nextInt() - 1;
          al[u].add(v);
          al[v].add(u);
        }
        parent = new int[n];
        parent[0] = 0;
        dfs(0, 0);
        //System.out.println(Arrays.toString(parent));
        while (q-- > 0) {
          int u = sc.nextInt() - 1;
          int val = sc.nextInt();
          lazy[u] += val;
        }
        // lazy stores the extra value for each node 
        dfs1(0);

        StringBuilder ans = new StringBuilder();
        for (int val : vals)
          ans.append(val).append(" ");
        System.out.println(ans);
      }
    }
  }

  private static void dfs1(int node) {
    vals[node] += lazy[node];
    for (int v : al[node]) {
      if (v != parent[node]) {
        lazy[v] += lazy[node];
        dfs1(v);
      }
    }
  }

  private static void dfs(int node, int par) {
    inT[node] = time++;
    parent[node] = par;
    for (int v : al[node])
      if (v != par)
        dfs(v, node);
    outT[node] = time++;
  }

  private static void fill(ArrayList<Integer>[] al) {
    for (int i = 0; i < al.length; i++)
      al[i] = new ArrayList<>();
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