package codeforce.practice;

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
 * @see <a href="https://codeforces.com/problemset/problem/894/A" target="_top">https://codeforces.com/problemset/problem/894/A</a>
 * @since 02/11/21 10:41 AM
 */
public class p894_A_QAQ {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        int cnt = 0;
        int[] left = fill(str);
        int[] right = fill(new StringBuilder(str).reverse().toString());
        reverse(right);
        for (int i = 0; i < str.length(); i++) {
          if (str.charAt(i) == 'A')
            cnt += left[i] * right[i];
        }
        System.out.println(cnt);
      }
    }
  }

  private static void reverse(int[] fill) {
    for (int l = 0, r = fill.length - 1; l < r; l++, r--) {
      int t = fill[l];
      fill[l] = fill[r];
      fill[r] = t;
    }
  }

  private static int[] fill(String str) {
    int[] left = new int[str.length()];
    int cur = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'Q')
        cur++;
      left[i] = cur;
    }
    return left;
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