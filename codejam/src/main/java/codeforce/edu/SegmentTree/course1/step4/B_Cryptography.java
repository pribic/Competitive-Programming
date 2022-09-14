package codeforce.edu.SegmentTree.course1.step4;

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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/4/4/practice/contest/274684/problem/B" target="_top">https://codeforces.com/edu/course/2/lesson/4/4/practice/contest/274684/problem/B</a>
 * @since 24/10/21 1:20 AM
 */
public class B_Cryptography {
  static FastScanner sc = new FastScanner(System.in);

  static int mod = 0;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int r = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        mod = r;
        int[][][] matrixes = new int[n][2][2];
        for (int i = 0; i < n; i++) {
          matrixes[i][0][0] = sc.nextInt();
          matrixes[i][0][1] = sc.nextInt();
          matrixes[i][1][0] = sc.nextInt();
          matrixes[i][1][1] = sc.nextInt();
        }
        SegTree st = new SegTree(n + 1);
        st.build(matrixes);
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
          int left = sc.nextInt() - 1;
          int right = sc.nextInt();
          int[][] ans = st.get(left, right).val;
          for (int[] row : ans) {
            for (int cell : row)
              sb.append(cell).append(" ");
            sb.append("\n");
          }
          sb.append("\n");
        }
        System.out.println(sb);
      }
    }
  }

  static class Node {
    int[][] val;

    Node() {
      val = new int[2][2];
      val[0][0] = 1;
      val[1][1] = 1;
    }

    public Node(int[][] val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return val + "";
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
        data[i] = new Node();
    }

    void build(int[][][] matrixes) {
      build(matrixes, 0, 0, size);
    }

    private void build(int[][][] matrixes, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < matrixes.length)
          data[pos].val = matrixes[lx];
        return;
      }
      int mid = (lx + rx) >> 1;
      build(matrixes, 2 * pos + 1, lx, mid);
      build(matrixes, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    void set(int idx, int[][] val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int[][] val, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] = new Node(val);
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
        return new Node(); // neutral element
      if (lx >= l && rx <= r) { //completely inside
        return data[pos];
      }
      int mid = (lx + rx) >> 1;
      Node left = get(l, r, 2 * pos + 1, lx, mid);
      Node right = get(l, r, 2 * pos + 2, mid, rx);
      return merge(left, right);
    }

    private Node merge(Node left, Node right) {
      int[][] arr = new int[2][2];
      int[][] one = left.val;
      int[][] two = right.val;
      arr[0][0] = ((one[0][0] * two[0][0]) % mod + (one[0][1] * two[1][0]) % mod) % mod;
      arr[0][1] = ((one[0][0] * two[0][1]) % mod + (one[0][1] * two[1][1]) % mod) % mod;
      arr[1][0] = ((one[1][0] * two[0][0]) % mod + (one[1][1] * two[1][0]) % mod) % mod;
      arr[1][1] = ((one[1][0] * two[0][1]) % mod + (one[1][1] * two[1][1]) % mod) % mod;
      return new Node(arr);
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