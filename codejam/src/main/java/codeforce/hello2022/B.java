package codeforce.hello2022;

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
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1621/problem/B" target="_top">https://codeforces.com/contest/1621/problem/B</a>
 * @since 03/01/22 8:35 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        TreeSet<int[]> leftSide = new TreeSet<>((a, b) -> { // all of them will have same left end point in a[0], we need to sort them by cost and right end point
          if (a[2] != b[2]) // first sort based on cost
            return a[2] - b[2];
          // here, means left end point and cost is same, then pick the one with max right end first
          return b[1] - a[1];
        });
        TreeSet<int[]> rightSide = new TreeSet<>((a, b) -> { // all of them will have same right end point, so sort by cost
          if (a[2] != b[2])
            return a[2] - b[2];
          // means right end point and cost is same, then pick the one with least l value
          return a[0] - b[0];
        });
        TreeSet<int[]> leftmore = new TreeSet<>((a, b) -> {
          if (a[1] != b[1])
            return b[1] - a[1];
          // means left and right end point is same
          return a[2] - b[2]; // then min cost is useful
        });
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
          int l = sc.nextInt();
          int r = sc.nextInt();
          int c = sc.nextInt();
          int[] cur = {l, r, c, i};
          if (l < left) {
            leftSide.clear();
            leftmore.clear();
            leftSide.add(cur);
            leftmore.add(cur);
          }
          if (l == left) {
            leftSide.add(cur);
            leftmore.add(cur);
          }
          if (r > right) {
            rightSide.clear();
            rightSide.add(cur);
          }
          if (r == right)
            rightSide.add(cur);

          left = Math.min(left, l);
          right = Math.max(right, r);

          int[] can1 = leftSide.first();
          int[] can2 = rightSide.first();
          int[] can3 = leftmore.first();
          int cc = Integer.MAX_VALUE;
          if (left == can3[0] && right == can3[1]) { // means we have single interval for entire range
            cc = can3[2];
          }
          System.out.println(Math.min(cc, can1[2] + can2[2]));
        }
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