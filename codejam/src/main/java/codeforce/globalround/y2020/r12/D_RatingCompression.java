package codeforce.globalround.y2020.r12;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D_RatingCompression {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
         // arr[i] = sc.nextInt();
        }
        /**
         * 1 5 3 2 2
         * 
         * 
         * 
         */
        //1 3 3 2
        //1 3 2
        for(int i = 1; i <= n; i++) {
          arr[i-1] = i;
        }
        SegmentTree segTree = new SegmentTree(n);
        segTree.build(arr);
        System.out.println("tree build");
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; i++) {
          int k = i;
          // there will be n - k + 1 element 
          // 1 2 3 4 5 --  5 - 3 + 1
          Set<Integer> cur = new HashSet<>();
          long sum = 0;
          for (int j = 0; j < n - k + 1; j++) {
            //length is k
            int l = j;
            int r = j + k;
            int min = segTree.get(l, r);
            if (cur.contains(min)) {
              break;
            }
            cur.add(min);
            sum += min;
          }
          int nn = cur.size();
          if (cur.size() == n - k + 1 && (sum == (((long) nn * (nn + 1)) / 2))) {
            ans.append('1');
          } else
            ans.append('0');
        }
        System.out.println(ans);
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
