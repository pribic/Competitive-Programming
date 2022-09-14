package codeforce.practice;

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
 * min(ai, aj) = min(x,y) -> min(ai, aj) = ai or aj so min(x,y) = ai or min(x,y) = aj,
 * <p>
 * so we need to keep 1 number from ai and aj,
 * <p>
 * min(3,2) = min(10, 2)
 * <p>
 * 5
 * 3 5 3 3 4
 *
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1521/B" target="_top">https://codeforces.com/problemset/problem/1521/B</a>
 * @since 28/06/21 4:19 PM
 */
public class p1521B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int min = Integer.MAX_VALUE;
        int minI = -1;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (arr[i] < min) {
            min = arr[i];
            minI = i;
          }
        }
        System.out.println(n - 1);
        minI++;
        for (int i = 1; i <= n; i++) {
          if (i != minI) {
            System.out.println(i + " " + minI + " " + (min + Math.abs(minI - i)) + " " + min);
          }
        }
      }
    }
  }

  // 9 4 3 11 15
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