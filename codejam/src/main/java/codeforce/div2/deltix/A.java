package codeforce.div2.deltix;

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
 * @see <a href="https://codeforces.com/contest/1523/problem/A" target="_top">https://codeforces.com/contest/1523/problem/A</a>
 * @since 30/05/21 8:12 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[] str = sc.next().toCharArray();
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++)
          if (str[i] == '1')
            ones.add(i);
        for (int i = 0; i < ones.size(); i++) {
          if (i == 0) {
            for (int j = ones.get(i) - 1; j >= 0 && ones.get(i) - j <= m; j--)
              str[j] = '1';
          }
          if (i == ones.size() - 1) {
            for (int j = ones.get(i) + 1; j < n && j - ones.get(i) <= m; j++)
              str[j] = '1';
          }
        }
        for (int i = 1; i < ones.size(); i++) {
          int prev = ones.get(i - 1);
          int cur = ones.get(i);
          for (int j = prev + 1; j < cur; j++) {
            if (j - prev != cur - j && (j - prev <= m || cur - j <= m))
              str[j] = '1';
          }

        }
        for (char c : str)
          System.out.print(c);
        System.out.println();
      }
    }
  }
  /*
  
  4
11 3
11111001111
10 2
1110111101
5 2
10101
3 100
000

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