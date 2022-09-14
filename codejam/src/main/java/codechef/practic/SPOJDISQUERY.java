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
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.spoj.com/problems/DISQUERY/" target="_top">https://www.spoj.com/problems/DISQUERY/</a>
 * @since 21/05/21 10:36 PM
 */
public class SPOJDISQUERY {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        Map<Integer, List<Pair>> edges = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          int w = sc.nextInt();
          edges.putIfAbsent(u, new ArrayList<>());
          edges.putIfAbsent(v, new ArrayList<>());
          edges.get(u).add(new Pair(v, w));
          edges.get(v).add(new Pair(u, w));
        }
        int[] parent = new int[n + 1];
        parent[1] = 1;
        dfs(edges, parent, 1, 1);
        int[] level = new int[n + 1];
        level[1] = 1;
        levelDfs(edges, level, 1, 1, 1);
        //System.out.println("parent.toString() = " + Arrays.toString(parent));

        int[][] stParent = new int[n + 1][20];
        for (int i = 1; i <= n; i++)
          stParent[i][0] = parent[i];
        for (int j = 1; j < 20; j++) {
          for (int i = 1; i + (1 << j) <= n + 1; i++) {
            stParent[i][j] = stParent[stParent[i][j - 1]][j - 1];
          }
        }

        int q = sc.nextInt();
        while (q-- > 0) {
          int s = sc.nextInt();
          int d = sc.nextInt();

          if (level[s] < level[d]) {
            //move d up
            int diff = level[d] - level[s];
            d = getNthParent(d, stParent, diff);
          }
          if (level[d] < level[s]) {
            int diff = level[s] - level[d];
            s = getNthParent(s, stParent, diff);
          }
          //both are at same level, now bs 
          int l = 1;
          int r = level[s];
          // T T T F F
          while (r > l + 1) {
            int mid = l + (r - l) / 2;
            if(getNthParent(s, stParent, level[mid] - level[s]) == getNthParent(d, stParent, level[mid] - level[s])) 
              l = mid;
            else
              r = mid;
          }
          //so we got lca as l.
        }
      }
    }
  }

  private static int getNthParent(int node, int[][] stTable, int n) {
    //we need nth parent of node
    for (int i = 19; i >= 0; i--) {
      if (((n >> i) & 1) == 1) {
        node = stTable[node][i];
      }
    }
    return node;
  }

  private static void levelDfs(Map<Integer, List<Pair>> edges, int[] level, int node, int l, int p) {
    //node has level l.. so all its children will have level l + 1
    for (Pair edge : edges.get(node)) {
      if (edge.v != p) {
        level[edge.v] = l + 1;
        levelDfs(edges, level, edge.v, l + 1, node);
      }
    }
  }

  private static Stack<Integer> buildPath(int[] parent, int s) {
    Stack<Integer> path = new Stack<>();
    while (s != parent[s]) {
      path.push(s);
      s = parent[s];
    }
    path.push(s);
    return path;
  }

  private static void dfs(Map<Integer, List<Pair>> edges, int[] parent, int v, int p) {
    for (Pair edge : edges.get(v)) {
      if (edge.v != p) {
        parent[edge.v] = v;
        dfs(edges, parent, edge.v, v);
      }
    }
  }

  static class Pair {
    int v, w;

    public Pair(int v, int w) {
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
      br = new BufferedReader(new InputStreamReader(f));
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