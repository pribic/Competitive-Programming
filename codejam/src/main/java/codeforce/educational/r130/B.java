package codeforce.educational.r130;

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
 * @see <a href="https://codeforces.com/contest/1697/problem/B" target="_top">https://codeforces.com/contest/1697/problem/B</a>
 * @since 13/06/22 1:35 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        sort(arr);
        //System.out.println(Arrays.toString(arr));
        long[] ps = new long[n];
        long cs = 0;
        for (int i = 0; i < n; i++) {
          cs += arr[i];
          ps[i] = cs;
        }
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
          int x = sc.nextInt();
          int y = sc.nextInt();
          // 0 1 2 3 4 5 6 7 8 9 X
          sb.append(range(ps, n - x, n - x + y - 1)).append("\n");
        }
        System.out.println(sb);
      }
    }
  }

  private static void sort ( int[] arr){
    List<Integer> ls = new ArrayList<>();
    for (int n : arr) ls.add(n);
    Collections.sort(ls);
    for (int i = 0; i < arr.length; i++) arr[i] = ls.get(i);
  }

  private static long range(long[] ps, int l, int r) {
    return l - 1 >= 0 ? ps[r] - ps[l - 1] : ps[r];
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