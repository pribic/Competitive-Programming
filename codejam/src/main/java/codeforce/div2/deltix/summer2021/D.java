package codeforce.div2.deltix.summer2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
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
 * @see <a href="https://codeforces.com/contest/1556/problem/D" target="_top">https://codeforces.com/contest/1556/problem/D</a>
 * @since 30/08/21 7:57 AM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        int s12 = and(1, 2) + or(1, 2);
        int s13 = and(1, 3) + or(1, 3);
        int s23 = and(3, 2) + or(3, 2);
        nums[0] = ((s12 + s13) - s23) / 2;
        nums[1] = s12 - nums[0];
        nums[2] = s13 - nums[0];
        for (int i = 3; i < n; i++) {
          int si0 = and(i + 1, 1) + or(i + 1, 1);
          nums[i] = si0 - nums[0];
        }
        nums = Arrays.stream(nums).sorted().toArray();
        System.out.println("finish " + nums[k - 1]);
        System.out.flush();
      }
    }
  }

  static int and(int i, int j) {
    out.println("and " + i + " " + j);
    System.out.flush();
    return sc.nextInt();
  }

  static int or(int i, int j) {
    out.println("or " + i + " " + j);
    System.out.flush();
    return sc.nextInt();
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