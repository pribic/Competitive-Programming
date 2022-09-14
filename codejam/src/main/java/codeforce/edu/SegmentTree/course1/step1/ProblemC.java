package codeforce.edu.SegmentTree.course1.step1;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemC {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      int[] input = new int[n];
      for (int i = 0; i < n; i++) {
        input[i] = sc.nextInt();
      }

      SegmentTree sgt = new SegmentTree(n);
      sgt.build(input);
      for (int i = 0; i < m; i++) {
        int op = sc.nextInt();
        if (op == 1) {
          sgt.set(sc.nextInt(), sc.nextInt());
        } else {
          int[] ans = sgt.get(sc.nextInt(), sc.nextInt());
          System.out.println(ans[0] + " " + ans[1]);
        }
      }
    }
  }

  private static class SegmentTree {

    int size;

    // 0 is min
    // 1 is count
    int[][] arr;

    int inputSize;

    public SegmentTree(int n) {
      inputSize = n;
      size = 1;
      while (size < n) size *= 2;
      arr = new int[2 * size - 1][2];
      for (int i = 0; i < arr.length; i++) {
        arr[i][0] = Integer.MAX_VALUE;
        arr[i][1] = 0;
      }
    }


    private void build(int[] in, int x, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < inputSize) {
          arr[x][0] = in[lx];
          arr[x][1] = 1;
        }

        return;
      }
      int mid = (lx + rx) / 2;
      build(in, 2 * x + 1, lx, mid);
      build(in, 2 * x + 2, mid, rx);
      int[] left = arr[2 * x + 1];
      int[] right = arr[2 * x + 2];
      int min1 = left[0];
      int min2 = right[0];
      if (min1 == min2) {
        arr[x][1] = left[1] + right[1];
      } else if (min1 < min2)
        arr[x][1] = left[1];
      else
        arr[x][1] = right[1];
      arr[x][0] = Math.min(min1, min2);

    }

    public void build(int[] in) {
      build(in, 0, 0, size);
    }

    public void set(int index, int val) {
      set(index, val, 0, 0, size);
    }

    private void set(int index, int val, int x, int lx, int rx) {
      if (rx - lx == 1) {
        arr[x][0] = val;
        return;
      }
      int mid = (lx + rx) / 2;
      if (index < mid)
        set(index, val, 2 * x + 1, lx, mid);
      else
        set(index, val, 2 * x + 2, mid, rx);
      int[] left = arr[2 * x + 1];
      int[] right = arr[2 * x + 2];
      int min1 = left[0];
      int min2 = right[0];
      if (min1 == min2) {
        arr[x][1] = left[1] + right[1];
      } else if (min1 < min2)
        arr[x][1] = left[1];
      else
        arr[x][1] = right[1];
      arr[x][0] = Math.min(min1, min2);

    }

    public int[] get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    public int[] get(int l, int r, int x, int lx, int rx) {
      if (lx >= r || l >= rx)
        return new int[]{Integer.MAX_VALUE, 0};
      if (lx >= l && rx <= r)
        return arr[x];
      int mid = (lx + rx) / 2;
      int[] left = get(l, r, 2 * x + 1, lx, mid);
      int[] right = get(l, r, 2 * x + 2, mid, rx);
      int min1 = left[0];
      int min2 = right[0];
      int cnt;
      if (min1 == min2) {
        cnt = left[1] + right[1];
      } else if (min1 < min2)
        cnt = left[1];
      else
        cnt = right[1];
      return new int[]{Math.min(min1, min2), cnt};
    }

  }
}
