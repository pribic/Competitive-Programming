package codeforce.edu.SegmentTree.course1.step1;

import java.util.Arrays;
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
      //System.out.println(Arrays.toString(sgt.data));
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

  //1 based 
  static class SegmentTree {
    long[] data;
    int size;

    public SegmentTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new long[2 * size];
    }

    void set(int idx, int val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, int val, int x, int lx, int rx) { // we are at element x which represents range [lx, rx)
      if (rx - lx == 1) {
        data[x] = val;
        return;
      }
      int mid = (lx + rx) / 2; // [1, 7) -- 1 2 3 4 5 6 
      if (idx < mid)
        set(idx, val, 2 * x + 1, lx, mid);
      else
        set(idx, val, 2 * x + 2, mid, rx);
      data[x] = op(data[2 * x + 1], data[2 * x + 2]);
    }

    long get(int l, int r) { //[l, r)
      return get(l, r, 0, 0, size);
    }

    long get(int l, int r, int x, int lx, int rx) {
      if (l >= rx || r <= lx)
        return 0; //neutral element
      if (lx >= l && rx <= r)
        return data[x];
      int mid = (lx + rx) / 2;
      return op(get(l, r, 2 * x + 1, lx, mid), get(l, r, 2 * x + 2, mid, rx));
    }

    public void build(int[] arr) {
      build(arr, 0, 0, size);
    }

    private void build(int[] arr, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < arr.length)
          data[pos] = arr[lx];
        return;
      }
      int mid = (lx + rx) / 2;
      build(arr, 2 * pos + 1, lx, mid);
      build(arr, 2 * pos + 2, mid, rx);
      data[pos] = op(data[2 * pos + 1], data[2 * pos + 2]);
    }

    private long op(long a, long b) {
      return a + b;
    }
  }

}
