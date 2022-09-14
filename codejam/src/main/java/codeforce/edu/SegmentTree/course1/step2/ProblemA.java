package codeforce.edu.SegmentTree.course1.step2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/edu/course/2/lesson/4/2/practice/contest/273278/problem/A" target="_top">https://codeforces.com/edu/course/2/lesson/4/2/practice/contest/273278/problem/A</a>
 * @since 18/09/21 4:31 PM
 */
public class ProblemA {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        SegTree segTree = new SegTree(n);
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          segTree.set(i, arr[i]);
        }
        System.out.println(segTree.get(0, n).inside);
        while (m-- > 0) {
          int idx = sc.nextInt();
          int val = sc.nextInt();
          segTree.set(idx, val);
          System.out.println(segTree.get(0, n).inside);
        }
      }
    }
  }

  static class SegNode {
    long inside, pref, suf, sum;

    public SegNode(long inside, long pref, long suf, long sum) {
      this.inside = inside;
      this.pref = pref;
      this.suf = suf;
      this.sum = sum;
    }
  }

  static class SegTree {
    SegNode[] data;
    int size;

    SegNode neutral = new SegNode(0, 0, 0, 0);

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new SegNode[2 * size];
      Arrays.fill(data, neutral);
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int pos, int lx, int rx) { // [lx, rx)
      if (rx - lx == 1) {
        if (val >= 0)
          data[pos] = new SegNode(val, val, val, val);
        else {
          data[pos] = new SegNode(0, 0, 0, val);
        }
        return;
      }
      int mid = (lx + rx) / 2;
      if (idx < mid) { // [lx, mid)
        set(idx, val, 2 * pos + 1, lx, mid);
      } else { // [mid, rx)
        set(idx, val, 2 * pos + 2, mid, rx);
      }
      data[pos] = merge(data[2 * pos + 1], data[2 * pos + 2]);
    }

    private SegNode merge(SegNode one, SegNode other) {
      return new SegNode(max(one.inside, other.inside, one.suf + other.pref),
        max(one.pref, one.sum + other.pref),
        max(other.suf, one.suf + other.sum),
        one.sum + other.sum);
    }

    private long max(long... nums) {
      long max = Long.MIN_VALUE;
      for (long num : nums)
        max = Math.max(max, num);
      return max;
    }

    SegNode get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    private SegNode get(int l, int r, int pos, int lx, int rx) {
      if (rx <= l || lx >= r)
        return neutral;
      if (lx >= l && rx <= r)
        return data[pos];
      int mid = (lx + rx) / 2;
      SegNode left = get(l, r, 2 * pos + 1, lx, mid);
      SegNode right = get(l, r, 2 * pos + 2, mid, rx);
      return merge(left, right);
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