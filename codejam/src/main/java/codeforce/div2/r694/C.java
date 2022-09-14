package codeforce.div2.r694;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * @see <a href="https://codeforces.com/problemset/problem/1470/A" target="_top">https://codeforces.com/problemset/problem/1470/A</a>
 * @since 17/12/21 3:56 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] k = new int[n];
        for (int i = 0; i < n; i++) {
          k[i] = sc.nextInt() - 1;
        }
        int[] cost = new int[m];
        for (int i = 0; i < m; i++) {
          cost[i] = sc.nextInt();
        }
        long mincost = 0;
        //Arrays.sort(k);
        int[] idx = IntStream.range(1, n + 1).toArray();
        
        int available = 0;
        for (int i = n - 1; i >= 0; i--) {
          if (available < n && cost[available] < cost[k[i]]) {
            mincost += cost[available++];
          } else {
            mincost += cost[k[i]];
          }
        }
        System.out.println(mincost);
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