package codeforce.div4.r790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contests/1676/problem/F" target="_top">https://codeforces.com/contests/1676/problem/F</a>
 * @since 10/05/22 8:00 PM
 */
public class G {
  static FastScanner sc = new FastScanner(System.in);
  static int cnt = 0;
  static ArrayList<Integer>[] al;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        cnt = 0;
        int n = sc.nextInt();
        al = new ArrayList[n + 1];
        int[] parent = new int[n + 1];
        for (int i = 0; i < parent.length; i++)
          al[i] = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
          parent[i] = sc.nextInt();
          al[parent[i]].add(i);
        }
        parent[1] = 1;
        char[] color = sc.next().toCharArray();
        dfs(1, color);
        System.out.println(cnt);
      }
    }
  }

  private static int dfs(int node, char[] color) {
    int balance = color[node - 1] == 'W' ? 1 : -1;
    for (int v : al[node])
      balance += dfs(v, color);
    if (balance == 0)
      cnt++;
    return balance;
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