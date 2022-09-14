package codeforce.div2.r761;

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
 * @see <a href="https://codeforces.com/contest/1617/problem/0" target="_top">https://codeforces.com/contest/1617/problem/0</a>
 * @since 16/12/21 7:23 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        String t = sc.next();
        int[] f = new int[26];
        for (char c : s.toCharArray())
          f[c - 'a']++;
        if (f[0] != 0 && f[1] != 0 && f[2] != 0 && t.equals("abc")) {
          for (int times = 0; times < f[0]; times++)
            System.out.print('a');
          f[0] = 0;
          for (int times = 0; times < f[2]; times++)
            System.out.print('c');
          f[2] = 0;
          for (int times = 0; times < f[1]; times++)
            System.out.print('b');
          f[1] = 0;
          for (int i = 0; i < 26; i++)
            for (int times = 0; times < f[i]; times++)
              System.out.print((char) (i + 'a'));
          System.out.println();
        } else {
          for (int i = 0; i < 26; i++)
            for (int times = 0; times < f[i]; times++)
              System.out.print((char) (i + 'a'));
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