package codeforce.educational.r120;

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
 * @see <a href="https://codeforces.com/contest/1622/problem/C" target="_top">https://codeforces.com/contest/1622/problem/C</a>
 * @since 27/12/21 8:27 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] arr = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          sum += arr[i];
        }
        arr = LongStream.of(arr).sorted().toArray();
        // we will first reduce min number as much as we want, then we do the replacing max with min operation
        //we binary search on how many times we reduce this
        long l = -1;
        long r = Long.MAX_VALUE / 2;
        while (r > l + 1) {
          long mid = l + (r - l) / 2;
          if (check(mid, arr, sum, k))
            r = mid;
          else
            l = mid;
        }
        System.out.println(r);
      }
    }
  }

  private static boolean check(long mid, long[] arr, long sum, long k) {
    if (sum <= k)
      return true;
    if (arr.length == 1)
      return arr[0] - mid <= k;
    long[] diff1 = new long[arr.length];
    for (int i = 1; i < arr.length; i++)
      diff1[i] = arr[i] - arr[0];
    long[] ss = new long[diff1.length];
    long cs = 0;
    for (int i = ss.length - 1; i > 0; i--) {
      cs += diff1[i];
      ss[i] = cs;
    }
    for (int i = arr.length - 1; i > 0; i--) {
      if (arr.length - i > mid)
        break;
      long extraStepsForMin = mid - (arr.length - i); // how much we can subtract from 1st and last (arr.length - i) nums
      long newsum = sum - extraStepsForMin - ss[i] - (arr.length - i) * extraStepsForMin;
      if (newsum <= k)
        return true;
    }
    return false;
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