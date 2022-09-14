package codeforce.edu.SegmentTree.course1.step3;

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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/4/3/practice/contest/274545/problem/E" target="_top">https://codeforces.com/edu/course/2/lesson/4/3/practice/contest/274545/problem/E</a>
 * @since 24/10/21 12:18 AM
 */
public class EAdditionToSegment {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegTree st = new SegTree(n + 1);
        while (m-- > 0) {
          int type = sc.nextInt();
          if (type == 1) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int v = sc.nextInt();
            st.set(l, v);
            st.set(r, -v);
          } else {
            int i = sc.nextInt();
            System.out.println(st.get(0, i + 1).val);
          }
        }
      }
    }
  }

  static class Node {
    long val;

    public Node(long val) {
      this.val = val;
    }
  }

  static class SegTree {
    int size;
    Node[] data;

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new Node[2 * size];
      for (int i = 0; i < data.length; i++)
        data[i] = new Node(0);
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] = merge(data[pos], new Node(val));
        return;
      }
      int mid = (lx + rx) >> 1;
      if (idx < mid)
        set(idx, val, 2 * pos + 1, lx, mid);
      else
        set(idx, val, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    Node get(int l, int r) { // [l, r)
      return get(l, r, 0, 0, size);
    }

    private Node get(int l, int r, int pos, int lx, int rx) {
      if (lx >= r || rx <= l) // out of range
        return new Node(0); // neutral element
      if (lx >= l && rx <= r) { //completely inside
        return data[pos];
      }
      int mid = (lx + rx) >> 1;
      Node left = get(l, r, 2 * pos + 1, lx, mid);
      Node right = get(l, r, 2 * pos + 2, mid, rx);
      return merge(left, right);
    }

    private Node merge(Node left, Node right) {
      return new Node(left.val + right.val);
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