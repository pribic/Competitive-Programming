package hackercup.Y2022.qualification;

import hackercup.Y2021.qualification.C1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ProblemD {

  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/second_flight_input.txt"));

  public static void main(String[] args) throws IOException {
    File f = new File("/Users/pdoshi/hackercup/q2022/problemD" + System.currentTimeMillis() + ".output");
    f.createNewFile();

    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      System.out.println(tt);
      FileWriter fw = new FileWriter(f, true);
      print(fw, "Case #" + tt + ": ");
      int n = sc.nextInt();
      int m = sc.nextInt();
      int q = sc.nextInt();
      HashSet<Integer>[] al = new HashSet[n + 1];
      Map<Long, Integer> am = new HashMap<>();
      for (int i = 0; i < al.length; i++)
        al[i] = new HashSet<>();
      for (int i = 0; i < m; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        int c = sc.nextInt();
        am.put(id(u, v), c);
        am.put(id(v, u), c);
        al[u].add(v);
        al[v].add(u);
      }
      StringBuilder ans = new StringBuilder();
      for (int i = 0; i < q; i++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        long peakCustomer = 2L * am.getOrDefault(id(x, y), 0);
        HashSet<Integer> first = al[x];
        HashSet<Integer> second = al[x];
        for (int cc : first) {
          if (second.contains(cc) && cc != x && cc != y) {
            peakCustomer += Math.min(am.getOrDefault(id(x, cc), 0), am.getOrDefault(id(cc, y), 0));
          }
        }
        ans.append(peakCustomer).append(" ");
      }
      print(fw, ans.toString() + "\n");
      fw.close();
    }

  }

  private static long id(int x, int y) {
    return x * 10000000000L + y;
  }

  static class Edge {
    int to, c;

    public Edge(int to, int c) {
      this.to = to;
      this.c = c;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Edge edge = (Edge) o;
      return to == edge.to;
    }

    @Override
    public int hashCode() {
      return Objects.hash(to);
    }
  }

  public static void print(FileWriter fw, String s) throws IOException {
    System.out.print(s);
    fw.append(s);
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

