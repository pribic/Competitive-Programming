package codeforce.div2.r741;

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
 * @see <a href="https://codeforces.com/contest/1562/problem/A" target="_top">https://codeforces.com/contest/1562/problem/A</a>
 * @since 26/08/21 8:06 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int l = sc.nextInt();
        int r = sc.nextInt();
        int max = 0;
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (right > left + 1) {
          int mid = left + (right - left) / 2;
          int mod = mid + 1;
          int ll = ((l + mod - 1) / mod) * mod;
          if ((ll >= l && ll + mid <= r) || ( l >= mod && r - l + 1 >= mod))
            left = mid;
          else
            right = mid;
        }
        System.out.println(left);
      }
    }
  }

  // 50 - 70 , first multiple of 13
  // 3
  /*
  
  l l + 1 l + 2  . . .   l + r - l
  
  mod = 0 always possible
  mod = 1, 2 consequtive
  mod = 2, nums with diff 2
  
  
  8 - 26
  
  12 -> l = 13
   */
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