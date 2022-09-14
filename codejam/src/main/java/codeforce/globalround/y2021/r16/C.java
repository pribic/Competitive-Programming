package codeforce.globalround.y2021.r16;

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
 * @see <a href="https://codeforces.com/contest/1566/problem/C" target="_top">https://codeforces.com/contest/1566/problem/C</a>
 * @since 12/09/21 8:37 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] bitable = new int[2][n];
        for (int i = 0; i < 2; i++) {
          bitable[i] = parse(sc.next());
        }
/*

0 10 10 0 0
1 10 11 0 0
 */
        int ans = 0;
        boolean lastoneone = false;
        boolean lastzerozero = false;
        for (int i = 0; i < n; i++) {
          int sum = bitable[0][i] + bitable[1][i];
          if (sum == 1) { // 1 0 or 0 1 config
            //we cannot get better mex than 2 so just split greedily
            ans += 2;
            if (lastzerozero) {
              ans += 1;
            }
            lastzerozero = false;
            lastoneone = false;
          } else if (sum == 0) { // 0 0 
            if (lastoneone) {
              ans += 2;
              lastoneone = false;
              lastzerozero = false;
            } else {
              if (lastzerozero) {
                ans += 1;
              }
              lastzerozero = true;
            }
          } else { // 1 1
            //we are getting no benifit of splitting here, so we will just continue
            if (lastzerozero) {
              ans += 2;
              lastzerozero = false;
              lastoneone = false;
            } else {
              lastzerozero = false;
              lastoneone = true;
            }
          }
        }
        if (lastzerozero)
          ans += 1;
        System.out.println(ans);

      }
    }
  }


  private static int mex(int sum0) {
    return (sum0 + 1) % 3;
  }

  private static int[] parse(String next) {
    int[] arr = new int[next.length()];
    for (int i = 0; i < next.length(); i++) {
      arr[i] = next.charAt(i) - '0';
    }
    return arr;
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