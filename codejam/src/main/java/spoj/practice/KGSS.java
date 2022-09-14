package spoj.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * @see <a href="https://www.spoj.com/problems/KGSS/" target="_top">https://www.spoj.com/problems/KGSS/</a>
 * @since 12/10/21 6:40 PM
 */
public class KGSS {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        SegTree segTree = new SegTree(n);
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        segTree.build(arr);
        int q = sc.nextInt();
        while (q-- > 0) {
          char c = sc.next().charAt(0);
          if (c == 'Q') {
            int l = sc.nextInt() - 1;
            int r = sc.nextInt();
            SegTreeNode node = segTree.get(l, r);
            System.out.println(node.max + node.max2);
          } else {
            int idx = sc.nextInt() - 1;
            int val = sc.nextInt();
            segTree.set(idx, val);
          }
        }
      }
    }
  }

  static class SegTreeNode {
    int max;
    int max2;

    public SegTreeNode(int max, int max2) {
      this.max = max;
      this.max2 = max2;
    }
  }

  static class SegTree {
    int size;
    SegTreeNode[] data;

    public SegTree(int n) {
      this.size = 1;
      while (size < n) size *= 2;
      data = new SegTreeNode[2 * size];
      for (int i = 0; i < data.length; i++)
        data[i] = new SegTreeNode(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    SegTreeNode merge(SegTreeNode a, SegTreeNode b) {
      int[] vals = {a.max, a.max2, b.max, b.max2};
      Arrays.sort(vals);
      return new SegTreeNode(vals[3], vals[2]);
    }

    SegTreeNode get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    private SegTreeNode get(int l, int r, int pos, int lx, int rx) {
      if (l >= rx || r <= lx) {
        return new SegTreeNode(Integer.MIN_VALUE, Integer.MIN_VALUE);
      }
      if (lx >= l && rx <= r) {
        return data[pos];
      }
      int mid = (lx + rx) / 2;
      SegTreeNode left = get(l, r, 2 * pos + 1, lx, mid);
      SegTreeNode right = get(l, r, 2 * pos + 2, mid, rx);
      return merge(left, right);
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] = new SegTreeNode(val, Integer.MIN_VALUE);
        return;
      }
      int mid = (lx + rx) / 2;
      if (idx < mid)
        set(idx, val, 2 * pos + 1, lx, mid);
      else
        set(idx, val, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    int idx;

    public void build(int[] arr) {
      idx = 0;
      build(arr, 0, 0, size);
    }

    private void build(int[] arr, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        if (idx < arr.length)
          data[pos] = new SegTreeNode(arr[idx++], Integer.MIN_VALUE);
        return;
      }
      int mid = (lx + rx) / 2;
      build(arr, 2 * pos + 1, lx, mid);
      build(arr, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
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