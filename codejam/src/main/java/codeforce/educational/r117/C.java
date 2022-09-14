package codeforce.educational.r117;

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
 * @see <a href="https://codeforces.com/contest/1612/problem/C" target="_top">https://codeforces.com/contest/1612/problem/C</a>
 * @since 22/11/21 3:38 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long k = sc.nextLong();
        long x = sc.nextLong();
        long maxMsg = sum(k) + sum(k - 1);
        if (maxMsg <= x)
          System.out.println(2 * k - 1);
        else {
          //check for first half
          if (x <= sum(k)) {
            long l = 1;
            long r = k + 1;
            while (r > l + 1) {
              long mid = l + (r - l) / 2;
              if (sum(mid - 1) < x)
                l = mid;
              else
                r = mid;
            }
            System.out.println(l);
          } else {
            x -= sum(k);
            long l = 1;
            long r = k + 1;
            while (r > l + 1) {
              long mid = l + (r - l) / 2;
              if (sum(k - 1) - sum(k - mid) < x)
                l = mid;
              else
                r = mid;
            }
            System.out.println(k + l);
          }
        }
      }
    }
  }
  
  /*
  
  x > sum(k)
  
  1
  1 2
  1 2 3
  1 2 3 4
  1 2 3 4 5
  1 2 3 4 5 6
  1 2 3 4 5
  1 2 3 4
  1 2 3
  1 2
  1
   */

  static long sum(long n) { // returns sum of 1 + 2 .. n
    return n * (n + 1) / 2;
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