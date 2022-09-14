package codeforce.div2.r728;

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
 * @see <a href="https://codeforces.com/contest/1541/problem/B" target="_top">https://codeforces.com/contest/1541/problem/B</a>
 * @since 28/06/21 3:09 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        // 1 2 3 4 5
        // 3 4 5 6 | 5 6 7 | 7 8 | 9
        // we have numbers from [3, 2n - 1]
        // 3 1 5 9 2  -> [3,9]
        // 3 * 2 = 3 | 6 | 9
        int n = sc.nextInt();
        int[] freq = new int[2 * n + 1];
        for (int i = 1; i <= n; i++) {
          freq[sc.nextInt()] = i;
        }
        int cnt = 0;
        for (int i = 1; i < freq.length; i++) {
          if (freq[i] > 0) {
            // i * x = freq[i] + j
            for (int x = 1; (long) i * x < freq.length; x++) {
              int product = i * x;
              int j = product - freq[i];
              if (freq[x] == j && freq[i] < j)
                cnt++;
            }
          }
        }
        System.out.println(cnt);
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