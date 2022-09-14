package codeforce.div2.r795;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1691/problem/D" target="_top">https://codeforces.com/contest/1691/problem/D</a>
 * @since 31/05/22 9:08 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    /*verify(new int[] {1,2,3,4,5});
    verify(new int[] {5 , 4, 3, 2 , 1});
    verify(new int[] {1,2,2,10, 3});
    verify(new int[] {1,20,3,40,5});
    verify(new int[] {10, 5, 0, 11, 2});*/
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      StringBuilder ans = new StringBuilder();
      for (int tt = 1; tt <= T; tt++) {
/*

max(𝑎𝑖,𝑎𝑖+1,…,𝑎𝑗−1,𝑎𝑗) < 𝑎𝑖+𝑎𝑖+1+⋯+𝑎𝑗−1+𝑎𝑗 -- for any subarray
//suppose max is ak
   ak < ai + ... ak + ... aj
   
   ai + .....ak-1 + ak+1 + ... aj > 0

 */
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] left = prevGreaterElements(arr);
        int[] right = nextGreaterElements(arr);
        long[] prefixSum = buildPS(arr);
        long[] suffixSum = buildSS(arr);
        SegmentTree segmentTreeForPrefix = new SegmentTree(n);
        SegmentTree segmentTreeForSuffix = new SegmentTree(n);
        segmentTreeForPrefix.build(prefixSum);
        segmentTreeForSuffix.build(suffixSum);
        // left[i] = index of the element on left side, which is greater then arr[i]
        boolean found = false;
        for (int i = 0; !found && i < n; i++) {
          //I know how long this will be max, find max prefix and max suffix sum
          long maxP = Math.max(0, left[i] < i ? segmentTreeForSuffix.get(left[i] + 1, i) - suffixSum[i] : 0);
          long maxS = Math.max(0, right[i] > i ? segmentTreeForPrefix.get(i + 1, right[i]) - prefixSum[i] : 0);
          if (maxP + maxS > 0)
            found = true;
        }
        ans.append(found ? "NO" : "YES").append("\n");
      }
      System.out.println(ans);
    }
  }

  private static long[] buildSS(int[] arr) {
    long[] ss = new long[arr.length];
    long sum = 0;
    for (int i = arr.length - 1; i >= 0; i--) {
      sum += arr[i];
      ss[i] = sum;
    }
    return ss;
  }

  private static long[] buildPS(int[] arr) {
    long[] ps = new long[arr.length];
    long sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      ps[i] = sum;
    }
    return ps;
  }

  private static void verify(int[] arr) {
    out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
    out.println("Arrays.toString(nextGreaterElements(arr)) = " + Arrays.toString(nextGreaterElements(arr)));
    out.println("Arrays.toString(prevGreaterElements(arr)) = " + Arrays.toString(prevGreaterElements(arr)));
  }

  static public int[] nextGreaterElements(int[] A) {
    int n = A.length, res[] = new int[n];
    Arrays.fill(res, n);
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && A[stack.peek()] < A[i])
        res[stack.pop()] = i;
      stack.push(i);
    }
    return res;
  }

  static public int[] prevGreaterElements(int[] A) {
    int n = A.length, res[] = new int[n];
    Arrays.fill(res, -1);
    Stack<Integer> stack = new Stack<>();
    for (int i = n - 1; i >= 0; i--) {
      while (!stack.isEmpty() && A[stack.peek()] < A[i])
        res[stack.pop()] = i;
      stack.push(i);
    }
    return res;
  }

  static class SegmentTree {

    int size;
    long[] arr;

    int inputSize;

    public SegmentTree(int n) {
      size = 1;
      inputSize = n;
      while (size < n) size *= 2;
      arr = new long[2 * size - 1];
      Arrays.fill(arr, Long.MIN_VALUE / 2);
    }

    public void print() {
      //Arrays.stream(arr).forEachOrdered(x -> System.out.print(x + " "));
    }

    private void build(long[] input, int x, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < inputSize)
          arr[x] = input[lx];
        return;
      }
      int mid = (lx + rx) / 2;
      build(input, 2 * x + 1, lx, mid);
      build(input, 2 * x + 2, mid, rx);
      arr[x] = Math.max(arr[2 * x + 1], arr[2 * x + 2]);
    }

    public void build(long[] arr) {
      build(arr, 0, 0, size);
    }

    private void set(int index, long val, int x, int lx, int rx) {
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
      arr[x] = Math.max(arr[2 * x + 1], arr[2 * x + 2]);
    }

    public void set(int index, long val) {
      set(index, val, 0, 0, size);
    }

    public long get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    public long get(int l, int r, int x, int lx, int rx) {
      if (lx >= r || l >= rx)
        return Long.MIN_VALUE / 2;
      if (lx >= l && rx <= r)
        return arr[x];
      int mid = (lx + rx) / 2;
      long max1 = get(l, r, 2 * x + 1, lx, mid);
      long max2 = get(l, r, 2 * x + 2, mid, rx);
      return Math.max(max1, max2);
    }
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