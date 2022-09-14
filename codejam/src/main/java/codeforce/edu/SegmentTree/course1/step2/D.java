package codeforce.edu.SegmentTree.course1.step2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/edu/course/2/lesson/4/2/practice/contest/273278/problem/D" target="_top">https://codeforces.com/edu/course/2/lesson/4/2/practice/contest/273278/problem/D</a>
 * @since 30/09/21 3:14 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        SegTree segTree = new SegTree(n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        segTree.build(arr);
        StringBuilder ansStr = new StringBuilder();
        while (m-- > 0) {
          int op = sc.nextInt();
          if (op == 1) {
            int idx = sc.nextInt();
            int val = sc.nextInt();
            segTree.set(idx, val);
          } else {
            int x = sc.nextInt();
            int l = sc.nextInt();
            ansStr.append(segTree.get(x, l)).append("\n");
          }
        }
        System.out.println(ansStr);

      }
    }
  }

  static class SegTree {
    int size;
    int[] max;

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      max = new int[2 * size];
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        max[pos] = val;
        return;
      }
      int mid = (rx + lx) >> 1;
      if (idx < mid)
        set(idx, val, 2 * pos + 1, lx, mid);
      else
        set(idx, val, 2 * pos + 2, mid, rx);
      merge(pos);
    }


    int get(int x, int pos) {
      return get(x, pos, 0, 0, size);
    }

    private int get(int x, int l, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        return (max[pos] >= x && l <= lx) ? lx : -1;
      }
      // this represents [lx, rx), we need >= l index
      if (rx <= l) // if current range ends before l, then it is of no use to us.
        return -1;
      int mid = (lx + rx) >> 1;
      int left = get(x, l, 2 * pos + 1, lx, mid);
      if (left != -1)
        return left;
      int right = get(x, l, 2 * pos + 2, mid, rx);
      return right;
    }

    private void merge(int pos) {
      int lc = 2 * pos + 1;
      int rc = 2 * pos + 2;
      max[pos] = Math.max(max[lc], max[rc]);
    }

    public void build(int[] arr) {
      build(arr, 0, 0, size);
    }

    private void build(int[] arr, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < arr.length)
          max[pos] = arr[lx];
        return;
      }
      int mid = (lx + rx) >> 1;
      build(arr, 2 * pos + 1, lx, mid);
      build(arr, 2 * pos + 2, mid, rx);
      merge(pos);
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