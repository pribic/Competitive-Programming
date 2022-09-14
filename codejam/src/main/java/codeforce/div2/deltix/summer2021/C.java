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
 * @see <a href="https://codeforces.com/contest/1556/problem/C" target="_top">https://codeforces.com/contest/1556/problem/C</a>
 * @since 29/08/21 9:01 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long diff = 0;
        long pairs = 0;
        int idx = 0;
        int lastPairIdx = -1;
        long lastPairCnt = 0;
        for (int i = 0; i < n; i++) {
          idx += arr[i];
          if (i % 2 == 0) {
            diff += arr[i];
          } else {
            if (diff < arr[i]) {
              //try as many as we can and reset
              pairs += diff;
              if ((idx - arr[i] - diff <= lastPairIdx) && (idx - arr[i] - 1L >= lastPairIdx)) {
                pairs += lastPairCnt;
              }
              diff = 0;
              lastPairCnt = 0;
              lastPairIdx = -1;
            } else {
              pairs += arr[i];
              diff -= arr[i];
              if ((idx - arr[i] * 2L <= lastPairIdx) && (idx - arr[i] - 1L >= lastPairIdx)) {
                pairs += lastPairCnt;
              /*  if (idx - arr[i] * 2L == lastPairIdx) {
                  lastPairCnt++;
                  lastPairIdx = idx;
                } else {
                  lastPairCnt = 1;
                  lastPairIdx = -1;
                }*/
              }
              lastPairIdx = idx;
              lastPairCnt++;
            }
          }
        }
        System.out.println(pairs);
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