package codeforce.div2.r798;

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
 * @see <a href="https://codeforces.com/contest/1689/problem/0" target="_top">https://codeforces.com/contest/1689/problem/0</a>
 * @since 10/06/22 10:17 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] fr = new int[2][26];
        StringBuilder ans = new StringBuilder();
        char[] a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        int idx1 = 0;
        int idx2 = 0;
        int cnt = 0; // how many we took continuously
        int lastId = -1;
        while (idx1 < a.length && idx2 < b.length) {
          if (a[idx1] < b[idx2]) {
            //can we take it? we can if cnt is < k and lastId is this only
            if (lastId == -1 || lastId == 1 || (lastId == 0 && cnt < k)) {
              ans.append(a[idx1++]);
              if (lastId == 1)
                cnt = 1;
              else 
                cnt++;
              lastId = 0;
            } else {
              ans.append(b[idx2++]);
              if (lastId == 0)
                cnt = 1;
              else
                cnt++;
            }
          } else {

          }
        }

        System.out.println(ans);
      }
    }
  }
/*
aaabbb
   ddd
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