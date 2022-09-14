package codeforce.educational.r119;

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
 * @see <a href="https://codeforces.com/contest/1620/problem/D" target="_top">https://codeforces.com/contest/1620/problem/D</a>
 * @since 19/12/21 12:05 AM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int mincoin1 = 0;
        for (int i = 0; i < n; i++) {
          mincoin1 = Math.max(mincoin1, arr[i]);
        }
        int mincoin2 = 0;
        for (int i = 0; i < n; i++) {
          mincoin2 = Math.max(mincoin2, (arr[i] + 1) / 2);
          if (arr[i] % 2 != 0)
            mincoin2 = Integer.MAX_VALUE;
        }
        int mincoin3 = 0;
        for (int i = 0; i < n; i++) {
          mincoin3 = Math.max(mincoin3, (arr[i] + 2) / 3);
          if (arr[i] % 3 != 0)
            mincoin3 = Integer.MAX_VALUE;
        }
        int mincoin4 = 0;
        for (int i = 0; i < n; i++) { // 1 2
          mincoin4 = Math.max(mincoin4, arr[i] / 2 + arr[i] % 2);
        }
        int mincoin5 = 0;
        for (int i = 0; i < n; i++) { // 2 3
          mincoin5 = Math.max(mincoin5, arr[i] / 3 + (arr[i] % 3 == 0 ? 0 : 1));
          if (arr[i] == 1)
            mincoin5 = Integer.MAX_VALUE;
        }
        int mincoin6 = 0;
        for (int i = 0; i < n; i++) { // 1 3
          mincoin6 = Math.max(mincoin6, arr[i] / 3 + arr[i] % 3);
        }
        int mincoin7 = 0;
        for (int i = 0; i < n; i++) { // 1 3
          mincoin7 = Math.max(mincoin7, (arr[i] + 2) / 3);
        }
        System.out.println(min(mincoin1, mincoin2, mincoin3, mincoin4, mincoin5, mincoin6, mincoin7));
      }
    }
  }

  private static int min(int... min) {
    return IntStream.of(min).filter(num -> num > 0).min().getAsInt();
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