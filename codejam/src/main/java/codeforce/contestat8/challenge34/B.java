package codeforce.contestat8.challenge34;

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
 * @see <a href="https://codeforces.com/group/gwssXKhiVi/contest/364188/problem/B" target="_top">https://codeforces.com/group/gwssXKhiVi/contest/364188/problem/B</a>
 * @since 13/01/22 8:14 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    out.println(Integer.toBinaryString(131039));
    out.println(Integer.toBinaryString(61));
    out.println(Integer.toBinaryString(63));
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long a = sc.nextLong();
        long b = sc.nextLong();
        System.out.println(calc(b) - calc(a - 1));
      }
    }
  }

  private static long calc(long n) {
    long cnt = 0;
    Set<Long> ss = new HashSet<>();
    //int len = Long.toBinaryString(n).length();
    for (int len = 1; len <= 61; len++) {
      for (int bit = 0; bit < len - 1; bit++) {
        // bit is 0 rest are 1
        long num = (1L << len) - 1 - (1L << bit);
        if (0 < num && num <= n)
          ss.add(num);
      }
    }
    //out.println("ss = " + ss);
    return ss.size();
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