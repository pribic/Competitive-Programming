package codeforce.div3.r744;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1579/problem/E1" target="_top">https://codeforces.com/contest/1579/problem/E1</a>
 * @since 28/09/21 9:37 PM
 */
public class E2 {
  static FastScanner sc = new FastScanner(System.in);
  static int maxVal = 2 * 100000 + 10;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        SegTree segTree = new SegTree(maxVal);
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] original = arr.clone();
        sort(arr);
        Map<Integer, Integer> numVsId = new HashMap<>();
        for (int num : arr)
          numVsId.putIfAbsent(num, numVsId.size());
        for (int i = 0; i < n; i++) {
          original[i] = numVsId.get(original[i]);
        }
        Map<Integer, Integer> numsSoFar = new HashMap<>();
        arr = original;
        long ans = 0;
        for (int i = 0; i < n; i++) {
          long one = segTree.get(arr[i] + 1, maxVal);
          long two = segTree.get(0, arr[i]);
          ans += Math.min(one, two);
          segTree.set(arr[i], 1);
          numsSoFar.put(arr[i], numsSoFar.getOrDefault(arr[i], 0) + 1);
        }
        System.out.println(ans);
      }
    }
  }

  private static void sort(int[] arr) {
    List<Integer> nums = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      nums.add(arr[i]);
    }
    Collections.sort(nums);
    for (int i = 0; i < nums.size(); i++) {
      arr[i] = nums.get(i);
    }
  }

  private static void compress(int[] arr) {
    Map<Integer, Integer> numVsId = new HashMap<>();
    for (int num : arr)
      numVsId.putIfAbsent(num, numVsId.size());
    for (int i = 0; i < arr.length; i++) {
      arr[i] = numVsId.get(arr[i]);
    }
  }

  static class SegTree { // 0 based seg tree
    long[] data;
    int size;

    SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      data = new long[2 * size];
    }

    void build(int[] arr) {
      for (int i = 0; i < arr.length; i++)
        set(i, arr[i]);
    }

    void set(int idx, long val) {
      set(idx, val, 0, 0, size);
    }

    private void set(int idx, long val, int pos, int lx, int rx) {
      if (rx - lx == 1) {
        data[pos] += val;
        return;
      }
      int mid = (lx + rx) / 2;
      if (idx < mid) // [lx, mid)
        set(idx, val, 2 * pos + 1, lx, mid);
      else // [mid, rx)
        set(idx, val, 2 * pos + 2, mid, rx);
      data[pos] = operation(data[2 * pos + 1], data[2 * pos + 2]);
    }

    private long operation(long a, long b) {
      return a + b;
    }

    long get(int l, int r) { // returns [l, r)
      return get(l, r, 0, 0, size);
    }

    private long get(int l, int r, int pos, int lx, int rx) {
      if (rx <= l || lx >= r) { // [lx, rx) is outside of [l, r)
        return 0; // return neutral element
      }
      if (lx >= l && rx <= r) { // [lx, rx) is completely inside [l, r)
        return data[pos];
      }
      //go to both the sides;
      int mid = (lx + rx) / 2;
      long left = get(l, r, 2 * pos + 1, lx, mid);
      long right = get(l, r, 2 * pos + 2, mid, rx);
      return operation(left, right);
    }
  }

  static long merge(int[] arr, int[] left, int[] right) {
    int i = 0, j = 0, count = 0;
    while (i < left.length || j < right.length) {
      if (i == left.length) {
        arr[i + j] = right[j];
        j++;
      } else if (j == right.length) {
        arr[i + j] = left[i];
        i++;
      } else if (left[i] <= right[j]) {
        arr[i + j] = left[i];
        i++;
      } else {
        arr[i + j] = right[j];
        count += left.length - i;
        j++;
      }
    }
    return count;
  }

  static long invCount(int[] arr) {
    if (arr.length < 2)
      return 0;

    int m = (arr.length + 1) / 2;
    int left[] = Arrays.copyOfRange(arr, 0, m);
    int right[] = Arrays.copyOfRange(arr, m, arr.length);

    return invCount(left) + invCount(right) + merge(arr, left, right);
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
