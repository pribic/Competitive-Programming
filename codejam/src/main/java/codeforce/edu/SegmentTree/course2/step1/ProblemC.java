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

  static class SegTree {

    int size;

    int[] operations;

    static int NO_OPERATION = -1;

    public void print() {
      String s = "";
      for(int i = 0; i < operations.length; i++)
        s += ( i + "=" + operations[i] + ",");
     // System.out.println("Arrays.toString(operations) = " + s);
    }
    
    public SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      operations = new int[2 * size - 1];
    }

    private void operation(int l, int r, int v, int x, int lx, int rx) {
      propogate(x, lx, rx);
      //entirely inside
      if (lx >= l && rx <= r) {
        operations[x] = v;
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
      propogate(x, lx, rx);
      if (rx - lx == 1)
        return operations[x];
      int mid = (lx + rx) / 2;
      if (i < mid)
        return get(i, 2 * x + 1, lx, mid);
      else
        return get(i, 2 * x + 2, mid, rx);
    }

    private void propogate(int x, int lx, int rx) {
      if (operations[x] != NO_OPERATION && rx - lx > 1) {
        //it has some operations -- lazy propagate them to child nodes.
        operations[2 * x + 1] = operations[x];
        operations[2 * x + 2] = operations[x];
        operations[x] = NO_OPERATION;
      }
    }

    public int get(int i) {
      return get(i, 0, 0, size);
    }
  }
}