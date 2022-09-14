package codeforce.div2.r722;

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
import java.util.TreeSet;
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
 * @see <a href="https://codeforces.com/contest/1529/problem/B" target="_top">https://codeforces.com/contest/1529/problem/B</a>
 * @since 24/05/21 8:17 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long[] arr = new long[n];
        long cnt1 = 0;
        long cnt2 = 0;
        long cnt3 = 0;
        Set<Long> seen = new HashSet<>();
        boolean repeatNegative = false;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          if (arr[i] < 0)
            cnt1++;
          else if (arr[i] == 0)
            cnt2++;
          else cnt3++;
          if (arr[i] < 0 && seen.contains(arr[i]))
            repeatNegative = true;
          seen.add(arr[i]);
        }
        Arrays.sort(arr);
        long minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
          if (arr[i] >= 0 || arr[i + 1] >= 0)
            break;
          minDiff = Math.min(minDiff, Math.abs(arr[i] - arr[i + 1]));
        }
        long ans = cnt1 + cnt2;
        if (cnt2 <= 1 && !repeatNegative) {
          for (long x : arr) {
            if (x <= 0)
              continue;
            if (x <= minDiff) {
              ans++;
              break;
            }
          }
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