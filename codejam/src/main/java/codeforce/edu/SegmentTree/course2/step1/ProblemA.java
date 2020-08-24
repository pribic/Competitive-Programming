package codeforce.edu.SegmentTree.course2.step1;

import java.util.Scanner;

/**
 * Segment tree
 * <p>
 * operations
 * : updates on a range
 * : get on a single point
 *
 * @author pribic (Priyank Doshi)
 * @since 2020-08-10
 */
public class ProblemA {

  /**
   * 5 5
   * 1 0 3 3
   * 2 1
   * 1 2 4 4
   * 2 3
   * 2 4
   * @param args
   */
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
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
            sg.add(l, r, v);
            break;
          case 2:
            System.out.println(sg.get(sc.nextInt()));
        }
      }
    }
  }

  static class SegTree {

    // represents total leaf node
    //represents total range 
    int size;

    long[] sums;

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      sums = new long[2 * size - 1];
    }

    void add(int l, int r, int v) {
      add(l, r, v, 0, 0, size);
    }

    private void add(int l, int r, int v, int x, int lx, int rx) {
      //entirely inside
      if (lx >= l && rx <= r) {
        //combining two operations on a node
        sums[x] += v;
        return;
      }

      //entirely outside
      if (rx <= l || lx >= r)
        return;

      //otherwise go to both children
      int mid = (lx + rx) / 2;
      add(l, r, v, 2 * x + 1, lx, mid);
      add(l, r, v, 2 * x + 2, mid, rx);
    }

    long get(int i) {
      return get(i, 0, 0, size);
    }

    private long get(int i, int x, int lx, int rx) {
      if (rx - lx == 1)
        return sums[x];
      int mid = (lx + rx) / 2;
      long val;
      if (i < mid)
        val = get(i, 2 * x + 1, lx, mid);
      else
        val = get(i, 2 * x + 2, mid, rx);
      return val + sums[x];
    }
  }
}