package codeforce.div2.deltix.summer2021;

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
 * @see <a href="https://codeforces.com/contest/1556/problem/B" target="_top">https://codeforces.com/contest/1556/problem/B</a>
 * @since 29/08/21 8:16 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int ones = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt() % 2;
          ones += arr[i];
        }
        int zeros = n - ones;
        if ((n % 2 == 0 && ones != zeros) || (n % 2 == 1 && Math.abs(ones - zeros) != 1))
          System.out.println(-1);
        else {
          if (n % 2 == 0) {
            // we can make 010101 or 101010
            System.out.println(Math.min(swaps(arr, n, 0), swaps(arr, n, 1)));
          } else {
            System.out.println(swaps(arr, n, ones > zeros ? 1 : 0));
          }
        }
      }
    }
  }

  private static int swaps(int[] arr, int n, int first) {
    int moves = 0;
    for (int i = 0, pos = 1; i < n; i++) {
      if (arr[i] != first) {
        moves += Math.abs(pos - i);
        pos += 2;
      }
    }
    return moves;
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