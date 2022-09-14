package codeforce.practice;

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
 * @see <a href="https://codeforces.com/problemset/problem/1354/D" target="_top">https://codeforces.com/problemset/problem/1354/D</a>
 * @since 21/10/21 10:53 AM
 */
public class p1354D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      StringBuilder ans = new StringBuilder();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n+1];
        SegTree st = new SegTree(n);
        int cnt = n;
        for (int i = 1; i <= n; i++) {
          arr[i] = sc.nextInt();
          st.set(arr[i], 1);
        }
        while (q-- > 0) {
          int k = sc.nextInt();
          if (k >= 0) {
            st.set(arr[k], 1);
            cnt++;
          } else {
            if (cnt > 0)
              cnt--;
            k = -k;
            st.updateKth(k);
          }
        }
        int kth = st.getKth(1);
        ans.append(cnt == 0 ? 0 : kth).append("\n");
        System.out.println(ans);
        out.flush();
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

    public void updateKth(int k) {
      updateKth(k, 0, 0, size);
    }

    private void updateKth(int k, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos].val--;
        return;
      }
      int mid = (lx + rx) >> 1;
      if (k <= data[2 * pos + 1].val)
        updateKth(k, 2 * pos + 1, lx, mid);
      else
        updateKth(k - data[2 * pos + 1].val, 2 * pos + 2, mid, rx);
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    public int getKth(int k) {
      return getKth(k, 0, 0, size);
    }

    private int getKth(int k, int pos, int lx, int rx) {
      if (rx - lx == 1)
        return lx;
      int mid = (lx + rx) >> 1;
      if (k <= data[2 * pos + 1].val)
        return getKth(k, 2 * pos + 1, lx, mid);
      else
        return getKth(k - data[2 * pos + 1].val, 2 * pos + 2, mid, rx);
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