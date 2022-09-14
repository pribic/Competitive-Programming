package codeforce.goodbye2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1616/problem/E" target="_top">https://codeforces.com/contest/1616/problem/E</a>
 * @since 30/12/21 3:23 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        char[] s = sc.next().toCharArray();
        char[] t = sc.next().toCharArray();
        ArrayList<Integer>[] indexes = new ArrayList[26];
        for (int i = 0; i < 26; i++)
          indexes[i] = new ArrayList<>();
        for (int i = 0; i < n; i++)
          indexes[s[i] - 'a'].add(i);
        int[] ptr = new int[26]; // pointers for each character
        //we need segment tree with range sum and point update
        SegmentTree st = new SegmentTree(n);
        int cost = 0;
        for (int i = 0; i < n; i++) {
          int si = s[i] - 'a';
          int ti = t[i] - 'a';
          //I want first index of < tchar , so i need index for each char
          int mincost = Integer.MAX_VALUE;
          int mini = -1;
          for (int j = 0; j < ti; j++) {
            if (ptr[j] < indexes[j].size()) {
              int curpos = indexes[j].get(ptr[j]) + (int) st.get(indexes[j].get(ptr[j]), n);
              if (curpos - i < mincost) {
                mincost = curpos - i;
                mini = j;
              }
            }
          }
          if (mincost == Integer.MAX_VALUE) {
            //use same one
            if (ptr[ti] == indexes[ti].size() || i == n - 1) { // same is also not, so all remaining are >= tchar, means we cannot make lexi smaller, break with -1
              cost = -1;
              break;
            }
            List<Integer> idx = indexes[ti];
            int originalIdx = idx.get(ptr[ti]);
            int newindx = originalIdx + (int) st.get(originalIdx, n);
            cost += newindx - i;
            st.set(newindx, 1); // mark it as consumed
            ptr[ti]++;
          } else { // we found smaller, use it and break;
            int originalIdx = indexes[mini].get(ptr[mini]);
            cost += originalIdx + st.get(originalIdx, n) - i;
            break;
          }
        }
        System.out.println(cost);
      }
    }
  }

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

/*
5
aabba
aabba

 */