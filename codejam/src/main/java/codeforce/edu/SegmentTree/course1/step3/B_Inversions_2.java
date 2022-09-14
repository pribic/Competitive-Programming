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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/4/3/practice/contest/274545/problem/B" target="_top">https://codeforces.com/edu/course/2/lesson/4/3/practice/contest/274545/problem/B</a>
 * @since 01/10/21 3:25 PM
 */
public class B_Inversions_2 {
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
        int[] original = new int[n];
        for (int i = n - 1; i >= 0; i--) {
          original[i] = segTree.get(i - arr[i] + 1) + 1;
        }

        StringBuilder ans = new StringBuilder();
        for (int num : original)
          ans.append(num).append(" ");
        System.out.println(ans);
      }
    }
  }

  static class Node {
    int val;

    public Node(int val) {
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
        data[i] = new Node(0);
      build(0, 0, size);
    }

    private void build(int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] = new Node(1);
        return;
      }
      int mid = (lx + rx) >> 1;
      build(2 * pos + 1, lx, mid);
      build(2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int pos, int lx, int rx) {
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

    int get(int k) { // [l, r)
      return get(k, 0, 0, size);
    }

    private int get(int k, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos].val--;
        return lx;
      }
      int mid = (lx + rx) >> 1;
      int ans;
      if (k <= data[2 * pos + 1].val)
        ans = get(k, 2 * pos + 1, lx, mid);
      else
        ans = get(k - data[2 * pos + 1].val, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
      return ans;
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