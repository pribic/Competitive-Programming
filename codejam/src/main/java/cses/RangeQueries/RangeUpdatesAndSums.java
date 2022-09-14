package cses.RangeQueries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.TreeMap;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://cses.fi/problemset/task/1735" target="_top">https://cses.fi/problemset/task/1735</a>
 * @since 17/10/21 11:19 PM
 */
public class RangeUpdatesAndSums {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        SegmentTree segmentTree = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
          segmentTree.updateVal(i, i + 1, arr[i]);
        }
        while (q-- > 0) {
          int type = sc.nextInt();
          switch (type) {
            case 1:
              int a = sc.nextInt() - 1;
              int b = sc.nextInt();
              int x = sc.nextInt();
              segmentTree.updateSum(a, b, x);
              break;
            case 2:
              a = sc.nextInt() - 1;
              b = sc.nextInt();
              x = sc.nextInt();
              segmentTree.updateVal(a, b, x);
              break;
            case 3:
              a = sc.nextInt() - 1;
              b = sc.nextInt();
              System.out.println(segmentTree.rangeSum(a, b));
              break;
          }
        }
      }
    }
  }

  static class SegmentTree {
    int size;
    Node[] nodes;

    public SegmentTree(int n) {
      this.size = 1;
      while (size < n) size *= 2;
      nodes = new Node[2 * size];
      for (int i = 0; i < nodes.length; i++)
        nodes[i] = new Node();
    }

    void updateSum(int l, int r, int val) { // add x to range[l,r) 
      updateSum(l, r, val, 0, 0, size);
    }

    private void updateSum(int l, int r, int val, int pos, int lx, int rx) {
      lazyPropagate(pos, lx, rx);
      if (rx <= l || lx >= r) // no overlap
        return;
      if (lx >= l && rx <= r) { // complete overlap
        nodes[pos].lazyUpdate = val;
        nodes[pos].lazyAdd = 0;
        nodes[pos].mark = true;
        return;
      }
      //partial overlap
      int mid = (lx + rx) >> 1;
      updateSum(l, r, val, 2 * pos + 1, lx, mid);
      updateSum(l, r, val, 2 * pos + 2, mid, rx);
      nodes[pos].sum = calc(lc(pos), lx, mid) + calc(rc(pos), mid, rx);
    }

    void updateVal(int l, int r, int val) { // assign x to range[l, r)
      updateVal(l, r, val, 0, 0, size);
    }

    private void updateVal(int l, int r, int val, int pos, int lx, int rx) { //query range [l, r), node pos' range [lx, rx)
      lazyPropagate(pos, lx, rx);
      if (rx <= l || lx >= r) // no overlap
        return;
      if (lx >= l && rx <= r) { // complete overlap
        nodes[pos].lazyUpdate = val;
        nodes[pos].lazyAdd = 0;
        nodes[pos].mark = true;
        return;
      }
      //partial overlap
      int mid = (lx + rx) >> 1;
      updateVal(l, r, val, 2 * pos + 1, lx, mid);
      updateVal(l, r, val, 2 * pos + 2, mid, rx);
      nodes[pos].sum = calc(lc(pos), lx, mid) + calc(rc(pos), mid, rx);
    }

    long rangeSum(int l, int r) { // sum of [l, r)
      return rangeSum(l, r, 0, 0, size);
    }

    private int rangeSum(int l, int r, int pos, int lx, int rx) {
      lazyPropagate(pos, lx, rx);
      if (rx <= l || lx >= r)
        return 0;
      if (lx >= l && rx <= r) {
        return calc(pos, lx, rx);
      }
      int mid = (lx + rx) >> 1;
      int left = rangeSum(l, r, lc(pos), lx, mid);
      int right = rangeSum(l, r, rc(pos), mid, rx);
      return left + right;
    }


    private void lazyPropagate(int pos, int lx, int rx) {
      if (rx - lx == 1)
        return;

      if (!nodes[pos].mark) {
        nodes[lc(pos)].lazyAdd += nodes[pos].lazyAdd;
        nodes[rc(pos)].lazyAdd += nodes[pos].lazyAdd;
        nodes[pos].lazyAdd = 0;
      } else {
        nodes[lc(pos)].lazyUpdate = nodes[rc(pos)].lazyUpdate = nodes[pos].lazyUpdate;
        nodes[lc(pos)].lazyAdd = nodes[rc(pos)].lazyAdd = 0;

        nodes[pos].mark = false;
        nodes[lc(pos)].mark = true;
        nodes[rc(pos)].mark = true;
      }

    }

    private int calc(int pos, int lx, int rx) {
      return nodes[pos].mark ? nodes[pos].lazyUpdate * (rx - lx) : nodes[pos].sum + nodes[pos].lazyAdd * (rx - lx);
    }

    private int lc(int pos) {
      return 2 * pos + 1;
    }

    private int rc(int pos) {
      return lc(pos) + 2;
    }
  }

  static class Node {
    int lazyUpdate, lazyAdd; // represents pending update and pending sum for this node
    int sum; // represents sum of all nodes covered by the range pointed by this node
    boolean mark; // true: this node has pending update, false: no pending updates

    @Override
    public String toString() {
      
      return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
        .add("lazyUpdate=" + lazyUpdate)
        .add("lazyAdd=" + lazyAdd)
        .add("sum=" + sum)
        .add("mark=" + mark)
        .toString();
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