package codeforce.div3.r753;

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
 * @see <a href="https://codeforces.com/contest/1607/problem/B" target="_top">https://codeforces.com/contest/1607/problem/B</a>
 * @since 02/11/21 8:40 PM
 */
public class p1607_B_Odd_Grasshopper {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long x0 = sc.nextLong();
        long n = sc.nextLong();
        long maxSum = n * (n + 1) / 2;
        if (n == 1) {
          if (x0 % 2 == 0)
            System.out.println(x0 - 1);
          else
            System.out.println(x0 + 1);
        }
        if (x0 % 2 == 0) {
          // -1 + 2 + 3 - 4 - 5 + 6 + 7 - 8 - 9 ...
          //      5 - 9 + 13 - 17
          //      -4 - 4
          n--;
          long diff = 4 * n / 4;

        } else {

        }
      }
    }
  }

  // 0 -> -1 -> 1 -> 4 -> 0 -> -5 -> 1 -> 8 -> 0
  // 1 -> 2 -> 0 -> -3 -> 1 -> 6 -> 0 -> -7 -> 1

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