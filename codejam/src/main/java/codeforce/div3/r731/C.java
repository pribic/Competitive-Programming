package codeforce.div3.r731;

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

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1547/problem/C" target="_top">https://codeforces.com/contest/1547/problem/C</a>
 * @since 10/07/21 8:32 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int k = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
        }
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = sc.nextInt();
        }
        int[] ans = new int[n + m];
        int idx = 0;
        int l = 0;
        int r = 0;
        boolean invalid = false;
        while (!invalid && idx < n + m) {
          if (l < n && a[l] == 0) {
            k++;
            ans[idx] = a[l];
            l++;
          } else if (r < m && b[r] == 0) {
            k++;
            ans[idx] = b[r];
            r++;
          } else if (l < n && a[l] > 0 && a[l] <= k) {
            ans[idx] = a[l];
            l++;
          } else if (r < m && b[r] > 0 && b[r] <= k) {
            ans[idx] = b[r];
            r++;
          } else {
            invalid = true;
          }
          idx++;
        }
        if (invalid)
          System.out.println(-1);
        else {
          for (int num : ans)
            System.out.print(num + " ");
          System.out.println();
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