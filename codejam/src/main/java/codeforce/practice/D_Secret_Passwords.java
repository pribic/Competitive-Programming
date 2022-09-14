package codeforce.practice;

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
import java.util.Set;
import java.util.HashSet;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1263/D" target="_top">https://codeforces.com/problemset/problem/1263/D</a>
 * @since 29/10/21 12:52 PM
 */
public class D_Secret_Passwords {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
          passwords[i] = sc.next();
        }
        Map<String, Integer> mapping = new HashMap<>();
        for (String s : passwords)
          mapping.putIfAbsent(s, mapping.size());
        List<String>[] list = new ArrayList[26];
        for (int i = 0; i < list.length; i++)
          list[i] = new ArrayList<>();
        for (String pswd : passwords)
          for (char c : unique(pswd))
            list[c - 'a'].add(pswd);
        DSU dsu = new DSU(mapping.size());
        for (List<String> ll : list) {
          for (int i = 1; i < ll.size(); i++)
            dsu.union(mapping.get(ll.get(0)), mapping.get(ll.get(i)));
        }
        System.out.println(dsu.size);

      }
    }
  }

  private static HashSet<Character> unique(String pswd) {
    List<Character> ll = new ArrayList<>();
    for (char c : pswd.toCharArray())
      ll.add(c);
    return new HashSet<>(ll);
  }

  static class DSU {
    int[] parent;
    int size;

    DSU(int n) {
      this.size = n;
      parent = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    int get(int a) {
      return parent[a] = (a == parent[a] ? a : get(parent[a]));
    }

    void union(int a, int b) {
      int pa = get(a);
      int pb = get(b);
      if (pa != pb)
        size--;
      parent[pb] = pa;
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