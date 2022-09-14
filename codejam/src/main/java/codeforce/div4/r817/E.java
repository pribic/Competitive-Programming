package codeforce.div4.r817;

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
 * @see <a href="https://codeforces.com/contest/1722/problem/E" target="_top">https://codeforces.com/contest/1722/problem/E</a>
 * @since 01/09/22 9:31 AM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();

        long[][] rectangles = new long[1001][1001];
        for (int i = 0; i < n; i++) {
          int h = sc.nextInt();
          int w = sc.nextInt();
          rectangles[h][w] += (long) h * w;
        }
        //precompute
        for (int i = 1; i < rectangles.length; i++) {
          for (int j = 1; j < rectangles.length; j++) {
            rectangles[i][j] += rectangles[i][j - 1] + rectangles[i - 1][j] - rectangles[i - 1][j - 1];
          }
        }
        //read queries
        for (int i = 0; i < q; i++) {
          int hs = sc.nextInt();
          int ws = sc.nextInt();
          int hb = sc.nextInt();
          int wb = sc.nextInt();
          long totalArea = rectangles[hb - 1][wb - 1]
            - rectangles[hb - 1][ws]
            - rectangles[hs][wb - 1]
            + rectangles[hs][ws];
          System.out.println(totalArea);
        }

      }
    }
  }
  
  /*
  3*3
   0  1  2  3 4
 0 | | |  |  | |
 1 | | |  |  | |
 2 | | |  | 6| |
 3 | | |6 |  | |
 
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