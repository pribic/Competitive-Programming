package codeforce.div4.r874;

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
 * @see <a href="https://codeforces.com/contest/1669/problem/F" target="_top">https://codeforces.com/contest/1669/problem/F</a>
 * @since 21/04/22 9:53 PM
 */
public class F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
        }
        long[] psLeft = new long[n];
        long cs = 0;
        for (int i = 0; i < n; i++) {
          cs += arr[i];
          psLeft[i] = cs;
        }
        cs = 0;
        long[] psRight = new long[n];
        for (int i = n - 1; i >= 0; i--) {
          cs += arr[i];
          psRight[i] = cs;
        }

        Map<Long, Integer> idx = new HashMap<>();
        //for each sum, on right, i will store its right most index
        for (int i = 0; i < psRight.length; i++) { // 0 1 2 3 4 5
          Long num = psRight[i];
          idx.put(num, i);
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
          Long leftSum = psLeft[i];
          if(idx.containsKey(leftSum) && idx.get(leftSum) > i)
            ans = Math.max(ans, i + 1 + n - idx.get(leftSum));
        }
  
        System.out.println(ans);

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