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
public class ProblemB {

  /**
   * 5 5
   * 1 0 3 3
   * 2 1
   * 1 2 4 4
   * 2 3
   * 2 4
   *
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
            sg.operation(l, r, v);
            break;
          case 2:
            System.out.println(sg.get(sc.nextInt()));
        }
      }
    }
  }

  static class SegTree {

    int size;

    int[] operations;

    public SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      operations = new int[2 * size - 1];
    }

    private void operation(int l, int r, int v, int x, int lx, int rx) {
      //entirely inside
      if (lx >= l && rx <= r) {
        operations[x] = Math.max(operations[x], v);
        return;
      }

      //entirely outside
      if (rx <= l || lx >= r)
        return;

      //overlapping
      int mid = (lx + rx) / 2;
      operation(l, r, v, 2 * x + 1, lx, mid);
      operation(l, r, v, 2 * x + 2, mid, rx);
    }

    public void operation(int l, int r, int v) {
      operation(l, r, v, 0, 0, size);
    }

    public int get(int i, int x, int lx, int rx) {
      if (rx - lx == 1)
        return operations[x];
      int mid = (lx + rx) / 2;
      int val;
      if (i < mid)
        val = get(i, 2 * x + 1, lx, mid);
      else
        val = get(i, 2 * x + 2, mid, rx);
      return Math.max(val, operations[x]);
    }

    public int get(int i) {
      return get(i, 0, 0, size);
    }
  }
}