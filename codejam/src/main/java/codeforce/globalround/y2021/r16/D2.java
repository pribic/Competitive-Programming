package codeforce.globalround.y2021.r16;

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
 * @see <a href="https://codeforces.com/contest/1566/problem/D2" target="_top">https://codeforces.com/contest/1566/problem/D2</a>
 * @since 12/09/21 9:50 PM
 */
public class D2 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        Pair[] sight = new Pair[n * m];
        for (int i = 0; i < n * m; i++) {
          int s = sc.nextInt();
          sight[i] = new Pair(i, s);
        }
        Arrays.sort(sight, (p1, p2) -> {
          if (p1.s == p2.s)
            return p2.idx - p1.idx;
          return p1.s - p2.s;
        });
        int[] ans = new int[n * m];
        for (int i = 0; i < n * m; i++) {
          ans[sight[i].idx] = i;
          // we know that sight[i].idx person will seat on i + 1 position
        }
        for (int seat : ans)
          System.out.print(seat + " ");

        SegmentTree sgt = new SegmentTree(n * m);
        sgt.build(new int[n * m]);
        int inconvenience = 0;
        for (int i = 0; i < n * m; i++) {
          //lets see where ith person will seat
          int targetSeat = ans[i];
          //out of current occupancies, how many seats have value < targetSeat
          int row = getX(targetSeat, m);
          int col = getY(targetSeat, m);
          inconvenience += sgt.get(m * row, targetSeat); //[ l, r)
          sgt.set(targetSeat, 1);
        }
        System.out.println(inconvenience);
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

  static class Pair {
    int idx, s;

    public Pair(int idx, int s) {
      this.idx = idx;
      this.s = s;
    }
  }

  static int idx(int x, int y, int m) {
    return x * m + y;
  }

  static int getX(int num, int m) {
    return num / m;
  }

  static int getY(int num, int m) {
    return num % m;
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