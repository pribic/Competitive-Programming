package codeforce.edu.SegmentTree.course2.step2;

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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/5/2/practice/contest/279653/problem/A" target="_top">https://codeforces.com/edu/course/2/lesson/5/2/practice/contest/279653/problem/A</a>
 * @since 15/10/21 2:29 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      SegTree sg = new SegTree(n);
      for (int i = 0; i < m; i++) {
        int op = sc.nextInt();
        switch (op) {
          case 1:
            int l = sc.nextInt();
            int r = sc.nextInt();
            int v = sc.nextInt();
            sg.operation(l, r, v);
            break;
          case 2:
            System.out.println(sg.get(sc.nextInt(), sc.nextInt()));
        }
      }
    }
  }

  private static class SegTree {
    int size;
    long[] data; //  [i]: stores min for the range covered by index i
    long[] lazy; // stores pending sums on the given range
    boolean[] mark; // true : pending operation stored here, false : nothing to apply

    public SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new long[2 * size];
      lazy = new long[2 * size];
      mark = new boolean[2 * size];
    }

    public void operation(int l, int r, int v) {
      operation(l, r, v, 0, 0, size);
    }

    void lazyPropogate(int pos, int lx, int rx) { // move stored operation from this node to its children
      if (rx - lx == 1 || !mark[pos]) // no lazy for leaf node because they can't move it anywhere else
        return;
      lazy[2 * pos + 1] += lazy[pos];
      lazy[2 * pos + 2] += lazy[pos];
      lazy[pos] = 0; // set with neutral element
      data[pos] = Math.min(getLeftVal(pos), getRightVal(pos));
      mark[2 * pos + 1] = true;
      mark[2 * pos + 2] = true;
      mark[pos] = false;
    }

    private void operation(int l, int r, int v, int pos, int lx, int rx) {
      lazyPropogate(pos, lx, rx);
      if (rx <= l || lx >= r)
        return;
      if (lx >= l && rx <= r) {
        lazy[pos] += v;
        mark[pos] = true;
        return;
      }
      int mid = (lx + rx) >> 1;
      operation(l, r, v, 2 * pos + 1, lx, mid);
      operation(l, r, v, 2 * pos + 2, mid, rx);
      data[pos] = Math.min(getLeftVal(pos), getRightVal(pos));
    }

    private long getLeftVal(int pos) {
      return data[2 * pos + 1] + lazy[2 * pos + 1];
    }

    private long getRightVal(int pos) {
      return data[2 * pos + 2] + lazy[2 * pos + 2];
    }

    public long get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    private long get(int l, int r, int pos, int lx, int rx) {
      lazyPropogate(pos, lx, rx);
      if (rx <= l || lx >= r) // no overlap
        return Long.MAX_VALUE; // neutral element for min operation
      if (lx >= l && rx <= r) { //complete overlap
        return data[pos] + lazy[pos];
      }
      int mid = (lx + rx) >> 1;
      long left = get(l, r, 2 * pos + 1, lx, mid);
      long right = get(l, r, 2 * pos + 2, mid, rx);
      return Math.min(left, right) + lazy[pos];
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