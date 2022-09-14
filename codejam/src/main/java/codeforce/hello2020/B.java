package codeforce.hello2020;

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

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1284/B" target="_top">https://codeforces.com/problemset/problem/1284/B</a>
 * @since 16/05/22 7:08 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;// sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long cnt = 0;
        BIT minBit = new BIT(1000000 + 10);
        BIT maxBit = new BIT(1000000 + 10);
        List<int[]> maxElement = new ArrayList<>();
        int asC = 0;
        for (int i = 0; i < n; i++) {
          int len = sc.nextInt();
          int min = Integer.MAX_VALUE;
          int max = Integer.MIN_VALUE;
          int[] subarr = new int[len];
          for (int j = 0; j < len; j++) {
            int val = sc.nextInt();
            max = Math.max(max, val);
            min = Math.min(min, val);
            subarr[i] = val;
          }
          if(ascent(subarr)) {
            asC++;
            continue;
          }
          maxElement.add(new int[]{min, max});
          minBit.update(min, 1);
          maxBit.update(max, 1);
        }
        for (int[] m : maxElement) {
          minBit.update(m[0], -1);
          maxBit.update(m[1], -1);
          cnt += minBit.query(m[1] - 1) + (maxBit.query(m[0] + 1, 1000001));
          minBit.update(m[0], 1);
          maxBit.update(m[1], 1);
        }
        System.out.println(cnt);
      }
    }
  }

  static boolean ascent(int[] arr) {
    if (arr.length == 1)
      return true;
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > min)
        return true;
      min = Math.min(min, arr[i]);
    }
    return false;
  }
  /*
  3
4 2 0 2 0 | a
6 9 9 8 8 7 7 | b
1 6 | c

a + a/b/c, b/c + a, c + b, b + b

   */

  static class BIT {
    int[] val;

    BIT(int size) {
      this.val = new int[size + 1];
    }

    long query(int idx) {
      long sum = 0;
      while (idx > 0) {
        sum += val[idx];
        idx -= idx & -idx;
      }
      return sum;
    }

    long query(int l, int r) {
      return query(r) - query(l - 1);
    }

    void update(int idx, int delta) {
      while (idx > 0 && idx < val.length) {
        val[idx] += delta;
        idx += idx & -idx;
      }
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