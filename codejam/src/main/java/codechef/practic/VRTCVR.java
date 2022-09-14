package codechef.practic;

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
 * @see <a href="https://www.codechef.com/UADPIP01/problems/VRTCVR" target="_top">https://www.codechef.com/UADPIP01/problems/VRTCVR</a>
 * @since 01/06/21 9:33 PM
 */
public class VRTCVR {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        ArrayList<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
          edges[i] = new ArrayList();
        for (int i = 1; i < n; i++) {
          int u = sc.nextInt();
          int v = sc.nextInt();
          edges[u].add(v);
          edges[v].add(u);
        }
        
        int[] ans = dfs(1, 0, edges);
        System.out.println(Math.min(ans[0], ans[1]));
      }
    }
  }

  private static int[] dfs(int node, int p, ArrayList<Integer>[] edges) {
    int[] ans = new int[] {0, 1};
    for(int v : edges[node]) {
      if(v != p) {
        int[] child = dfs(v, node, edges);
        ans[0] += child[1];
        ans[1] += Math.min(child[0], child[1]);
      }
    }
    return ans;
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