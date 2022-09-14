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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.min;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1609/problem/E" target="_top">https://codeforces.com/contest/1609/problem/E</a>
 * @since 29/11/21 10:37 PM
 */
public class p1609_William_The_Oblivious {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        String s = sc.next();
        SegTree st = new SegTree(n);
        for (int i = 0; i < s.length(); i++)
          st.set(i, s.charAt(i));
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
          int pos = sc.nextInt() - 1;
          char c = sc.next().charAt(0);
          st.set(pos, c);
          sb.append(st.get()).append("\n");
        }
        System.out.println(sb);
      }
    }
  }

  static class Node {
    int a, b, c, ab, bc, abc;

    Node() {
    }

    Node(char ch) {
      if (ch == 'a')
        a = 1;
      else if (ch == 'b')
        b = 1;
      else if (ch == 'c')
        c = 1;
    }
  }

  static class SegTree {
    Node[] data;
    int size;

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new Node[2 * size];
      for (int i = 0; i < data.length; i++)
        data[i] = new Node();
    }

    void set(int pos, char c) {
      set(pos, c, 0, 0, size);
    }

    private void set(int idx, char c, int pos, int lx, int rx) { // [lx, rx), pos represents location in array
      if (rx - lx == 1) {
        Node node = new Node(c);
        data[pos] = node;
        return;
      }
      int mid = (lx + rx) / 2;
      if (idx < mid)
        set(idx, c, 2 * pos + 1, lx, mid);
      else
        set(idx, c, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[pos * 2 + 1], data[2 * pos + 2]);
    }

    int get() {
      return data[0].abc;
    }

    private Node merge(Node one, Node other) {
      Node node = new Node();
      node.a = one.a + other.a;
      node.b = one.b + other.b;
      node.c = one.c + other.c;
      node.ab = min(one.a + other.ab, one.ab + other.b);
      node.bc = min(one.b + other.bc, one.bc + other.c);
      node.abc = min(one.a + other.abc, min(one.ab + other.bc, one.abc + other.c));
      return node;
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