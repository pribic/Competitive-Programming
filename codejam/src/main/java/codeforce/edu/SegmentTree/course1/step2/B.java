package codeforce.edu.SegmentTree.course1.step2;

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
 * @see <a href="https://codeforces.com/edu/course/2/lesson/4/2/practice/contest/273278/problem/B" target="_top">https://codeforces.com/edu/course/2/lesson/4/2/practice/contest/273278/problem/B</a>
 * @since 30/09/21 2:52 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegmentTree segTree = new SegmentTree(n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (arr[i] == 1)
            segTree.set(i);
        }

        while (m-- > 0) {
          int op = sc.nextInt();
          if (op == 1) {
            int idx = sc.nextInt();
            segTree.set(idx);
          } else {
            int k = sc.nextInt();
            System.out.println(segTree.get(k + 1));
          }
        }
      }
    }
  }

  static class SegmentTree {
    int size;
    int[] data;

    public SegmentTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new int[2 * size];
    }

    void set(int idx) {
      set(idx, 0, 0, size);
    }

    private void set(int idx, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] = 1 - data[pos];
        return;
      }
      int mid = (lx + rx) / 2;
      if (idx < mid)
        set(idx, 2 * pos + 1, lx, mid);
      else
        set(idx, 2 * pos + 2, mid, rx);
      data[pos] = op(data[2 * pos + 1], data[2 * pos + 2]);
    }

    int get(int k) {
      return get(k, 0, 0, size);
    }

    private int get(int k, int pos, int lx, int rx) {
      if (rx > size)
        return -1;
      if (rx - lx == 1)
        return data[pos] == 0 ? -1 : lx;
      int mid = (lx + rx) / 2;
      int left = data[2 * pos + 1];
      if (k <= left)
        return get(k, 2 * pos + 1, lx, mid);
      else
        return get(k - left, 2 * pos + 2, mid, rx);
    }

    private int op(int a, int b) {
      return a + b;
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