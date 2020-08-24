package codeforce.edu.SegmentTree.course1;

import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int m = sc.nextInt();

      SegmentTree sgt = new SegmentTree(n);
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }
      sgt.build(arr);
//
      for (int i = 0; i < m; i++) {
        int op = sc.nextInt();
        if (op == 1) {
          int index = sc.nextInt();
          int val = sc.nextInt();
          sgt.set(index, val);
        } else {
          int l = sc.nextInt();
          int r = sc.nextInt();
          long sum = sgt.get(l, r);
          System.out.println(sum);
        }
      }
    }
  }

  static class SegmentTree {

    int size;

    long[] sums;

    public SegmentTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      sums = new long[2 * size - 1];
    }

    private void build(int[] arr, int x, int lx, int rx) {
      //System.out.println(x + " " + lx + " " + rx);
      if (rx - lx == 1) {
        if (lx < arr.length)
          sums[x] = arr[lx];
        return;
      }
      int mid = (lx + rx) / 2;
      build(arr, 2 * x + 1, lx, mid);
      build(arr, 2 * x + 2, mid, rx);
      sums[x] = sums[2 * x + 1] + sums[2 * x + 2];
    }

    public void build(int[] arr) {
      // System.out.println("build s");
      build(arr, 0, 0, size);
      // System.out.println("build end");
    }

    private void set(int index, int val, int x, int lx, int rx) {
      //System.out.println(index + " " + val + " " + x + " " + lx + " " + rx);
      if (rx - lx == 1) {
        sums[x] = val;
        return;
      }

      int mid = (lx + rx) / 2;
      if (index < mid) {
        //go left
        set(index, val, 2 * x + 1, lx, mid);
      } else {
        //go right
        set(index, val, 2 * x + 2, mid, rx);
      }
      sums[x] = sums[2 * x + 1] + sums[2 * x + 2];
    }

    public void set(int index, int val) {
      set(index, val, 0, 0, size);
    }

    public long get(int l, int r, int x, int lx, int rx) {
      if (lx >= l && rx <= r)
        return sums[x];
      else if (lx >= r || l >= rx)
        return 0;
      int mid = (lx + rx) / 2;
      return get(l, r, 2 * x + 1, lx, mid) + get(l, r, 2 * x + 2, mid, rx);
    }

    public long get(int l, int r) {
      return get(l, r, 0, 0, size);
    }
  }
}
