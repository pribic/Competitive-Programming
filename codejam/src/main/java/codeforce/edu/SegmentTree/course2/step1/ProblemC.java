package codeforce.edu.SegmentTree.course2.step1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Segment tree lazy propogation
 * <p>
 * operations
 * : updates on a range
 * : get on a single point
 *
 * @author pribic (Priyank Doshi)
 * @since 2020-08-10
 */
public class ProblemC {

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
            // System.out.println("111");
            // sg.print();
            break;
          case 2:
            System.out.println(sg.get(sc.nextInt()));
            // System.out.println("222");
            //sg.print();
        }
      }
    }
  }

  private static class SegTree {
    int size;
    int[] data;
    boolean[] mark; // true : pending operation stored here, false : nothing to apply

    public SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new int[2 * size];
      mark = new boolean[2 * size];
    }

    public void operation(int l, int r, int v) {
      operation(l, r, v, 0, 0, size);
    }

    void lazyPropogate(int pos, int lx, int rx) { // move stored operation from this node to its children
      if (rx - lx == 1 || !mark[pos]) // no lazy for leaf node because they can't move it anywhere else
        return;
      data[2 * pos + 1] = data[pos];
      data[2 * pos + 2] = data[pos];
      data[pos] = 0; // set with neutral element
      
      mark[2 * pos + 1] = true;
      mark[2 * pos + 2] = true;
      mark[pos] = false;
    }

    private void operation(int l, int r, int v, int pos, int lx, int rx) {
      lazyPropogate(pos, lx, rx);
      if (rx <= l || lx >= r)
        return;
      if (lx >= l && rx <= r) {
        data[pos] = v;
        mark[pos] = true;
        return;
      }
      int mid = (lx + rx) >> 1;
      operation(l, r, v, 2 * pos + 1, lx, mid);
      operation(l, r, v, 2 * pos + 2, mid, rx);
    }

    public int get(int x) {
      return get(x, 0, 0, size);
    }

    private int get(int x, int pos, int lx, int rx) {
      lazyPropogate(pos, lx, rx);
      if (rx - lx == 1) {
        return data[pos];
      }
      int mid = (lx + rx) >> 1;
      if (x < mid)
        return get(x, 2 * pos + 1, lx, mid);
      else
        return get(x, 2 * pos + 2, mid, rx);
    }
  }
}