package codeforce.globalround.y2021.r17;

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
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1610/problem/C" target="_top">https://codeforces.com/contest/1610/problem/C</a>
 * @since 23/11/21 8:39 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] ai = new int[n];
        int[] bi = new int[n];
        for (int i = 0; i < n; i++) {
          ai[i] = sc.nextInt();
          bi[i] = sc.nextInt();
        }
        /*
        income is fixed
        1 2 3 4 .... n
        
        // n - i people are richer -> can invite Math.min(n - i, ai[i])
        // i - 1 people are poor -> can invite Math.min(i-1, bi[i])  
        
        
        can we do binary search? ans seems monotonic
        
        how do we check but?
        
         */
        int l = 0; // true
        int r = n + 1; //false
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (check(mid, ai, bi, n))
            l = mid;
          else
            r = mid;
        }
        System.out.println(l);
      }
    }
  }

  private static boolean check(int mid, int[] ai, int[] bi, int n) {
    int chosen = 0;
    int b = mid;
    for (int i = n - 1; i >= 0; i--) {
      if (1 + bi[i] >= mid && chosen <= ai[i]) {
        chosen++;
        mid--;
      }
    }
    return chosen >= b;
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