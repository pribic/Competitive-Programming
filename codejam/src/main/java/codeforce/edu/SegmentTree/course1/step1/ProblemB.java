package codeforce.edu.SegmentTree.course1.step1;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {

      int n = sc.nextInt();
      int m = sc.nextInt();

      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = sc.nextInt();
      }

      SegmentTree sgt = new SegmentTree(n);
      sgt.build(arr);
      sgt.print();
      while (m > 0) {
        m--;
        int op = sc.nextInt();
        if (op == 1) {
          sgt.set(sc.nextInt(), sc.nextInt());
        } else {
          System.out.println(sgt.get(sc.nextInt(), sc.nextInt()));
        }
      }
    }
  }

  static class SegmentTree {

    int size;
    int[] arr;

    int inputSize;

    public SegmentTree(int n) {
      size = 1;
      inputSize = n;
      while (size < n) size *= 2;
      arr = new int[2 * size - 1];
      Arrays.fill(arr, Integer.MAX_VALUE);
    }

    public void print() {
      //Arrays.stream(arr).forEachOrdered(x -> System.out.print(x + " "));
    }

    private void build(int[] input, int x, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < inputSize)
          arr[x] = input[lx];
        return;
      }
      int mid = (lx + rx) / 2;
      build(input, 2 * x + 1, lx, mid);
      build(input, 2 * x + 2, mid, rx);
      arr[x] = Math.min(arr[2 * x + 1], arr[2 * x + 2]);
    }

    public void build(int[] arr) {
      build(arr, 0, 0, size);
    }

    private void set(int index, int val, int x, int lx, int rx) {
      if (rx - lx == 1) {
        arr[x] = val;
        return;
      }
      int mid = (lx + rx) / 2;
      if (index < mid) {
        set(index, val, 2 * x + 1, lx, mid);
      } else {
        set(index, val, 2 * x + 2, mid, rx);
      }
      arr[x] = Math.min(arr[2 * x + 1], arr[2 * x + 2]);
    }

    public void set(int index, int val) {
      set(index, val, 0, 0, size);
    }

    public int get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    public int get(int l, int r, int x, int lx, int rx) {
      if (lx >= r || l >= rx)
        return Integer.MAX_VALUE;
      if (lx >= l && rx <= r)
        return arr[x];
      int mid = (lx + rx) / 2;
      int min1 = get(l, r, 2 * x + 1, lx, mid);
      int min2 = get(l, r, 2 * x + 2, mid, rx);
      return Math.min(min1, min2);
    }
  }
}
