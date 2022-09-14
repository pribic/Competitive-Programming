package codeforce.div2.r763;

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
import java.util.stream.LongStream;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1623/problem/C" target="_top">https://codeforces.com/contest/1623/problem/C</a>
 * @since 28/12/21 8:34 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
          height[i] = sc.nextInt();
        }
        int l = 0;
        int r = Integer.MAX_VALUE;
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (check(mid, copy(height)))
            l = mid;
          else
            r = mid;
        }
        System.out.println(l);
      }
    }
  }

  private static long[] copy(int[] height) {
    long[] a = new long[height.length];
    for (int i = 0; i < height.length; i++) {
      a[i] = height[i];
    }
    return a;
  }

  private static boolean check(int mid, long[] height) {
    long[] extra = new long[height.length];
    for (int i = height.length - 1; i >= 2; i--) {
      if (height[i] + extra[i] < mid)
        return false;
      long extra1 = height[i] - mid;
      long d = extra1 / 3;
      height[i] -= 3 * d;
      height[i - 1] += d;
      height[i - 2] += 2 * d;
    }
    return height[0] >= mid && height[1] >= mid;
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