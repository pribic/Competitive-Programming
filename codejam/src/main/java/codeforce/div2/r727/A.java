package codeforce.div2.r727;

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
 * @see <a href="https://codeforces.com/contest/1539/problem/A" target="_top">https://codeforces.com/contest/1539/problem/A</a>
 * @since 20/06/21 3:36 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  /*
  
  k <= t/x
  0 - 5, 5 - 10, 10 - 15... 
  we will have this ranges, 0-x, x - 2x, 2x - 3x...(n-1)x - nx
  // x + t is the time when 1st will  
  1 1 1 1 1
      1 1 1 1 1
          1 1 1 1 1
              1 1 1 1 1
                  1 1 1 1 1
                      1 1 1 1 1
                          1 1 1 1 1
                          
    [0-7] [7-14][14-21]
    [0-10][7-17][14-24]
    1 1 1 1 1 1 1 1 1 1 , 2 4 6 8 10 will do
         2 2 2 ... 
             3 3 3...
                 4 4 4...                 
   */
  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int t = sc.nextInt();
        if (t < x)
          System.out.println(0);
        else {
          int k = t / x;// k > n -> t/x > n -> t > nx
          long ans = 0;
          if (n > k) {
            ans = ((long) n - k) * k;
            ans += ((k - 1L) * (k)) / 2;
          } else {
            k = Math.min(k , n);
            ans += ((k - 1L) * (k)) / 2;
          }
          System.out.println(ans);
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