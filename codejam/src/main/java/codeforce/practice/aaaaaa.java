package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="aaaaaa" target="_top">aaaaaa</a>
 * @since 21/10/21 7:31 PM
 */
public class aaaaaa {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        SegmentTree st = new SegmentTree(s.length());
        st.build(s.toCharArray());
        int q = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt();
          ans.append(st.query(l, r).total).append("\n");
        }
        System.out.println(ans);
      }
    }
  }

  static class Node {
    int totalOpening;
    int totalClosing;
    int total;

    public Node(int totalOpening, int totalClosing, int total) {
      this.totalOpening = totalOpening;
      this.totalClosing = totalClosing;
      this.total = total;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
        .add("totalOpening=" + totalOpening)
        .add("totalClosing=" + totalClosing)
        .add("total=" + total)
        .toString();
    }
  }

  //1 based 
  static class SegmentTree {
    Node[] data;
    int size;

    public SegmentTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new Node[2 * size];
    }


    Node query(int l, int r) { //[l, r)
      return query(l, r, 0, 0, size);
    }

    Node query(int l, int r, int x, int lx, int rx) {
      if (l >= rx || r <= lx)
        return new Node(0, 0, 0); //neutral element
      if (lx >= l && rx <= r)
        return data[x];
      int mid = (lx + rx) / 2;
      return merge(query(l, r, 2 * x + 1, lx, mid), query(l, r, 2 * x + 2, mid, rx));
    }

    public void build(char[] arr) {
      build(arr, 0, 0, size);
    }

    private void build(char[] arr, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < arr.length)
          data[pos] = new Node(arr[lx] == '(' ? 1 : 0, arr[lx] == ')' ? 1 : 0, 0);
        else
          data[pos] = new Node(0, 0, 0);
        return;
      }
      int mid = (lx + rx) / 2;
      build(arr, 2 * pos + 1, lx, mid);
      build(arr, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    private Node merge(Node a, Node b) {
      Node c = new Node(0, 0, 0);
      c.totalOpening = a.totalOpening + b.totalOpening;
      c.totalClosing = a.totalClosing + b.totalClosing;
      c.total = a.total + b.total + 2 * Math.min(a.totalOpening - a.total / 2, b.totalClosing - b.total / 2);
      return c;
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