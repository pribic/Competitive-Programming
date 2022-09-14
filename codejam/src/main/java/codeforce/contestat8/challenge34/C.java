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
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/group/gwssXKhiVi/contest/364188/problem/C" target="_top">https://codeforces.com/group/gwssXKhiVi/contest/364188/problem/C</a>
 * @since 13/01/22 10:10 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static int mod = (int) (1e9 + 7);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        long ans = 1L;
        for (char c : s.toCharArray()) {
          int x = 0;
          if (c == '-') {
            x = 62;
          } else if (c == '_') {
            x = 63;
          } else if ('a' <= c && c <= 'z') {
            x = 36 + c - 'a';
          } else if ('A' <= c && c <= 'Z') {
            x = 10 + c - 'A';
          } else {
            x = c - '0';
          }
          //how many zeros
          int zero = 0;
          for (int bit = 0; bit < 6; bit++) {
            if (((x >> bit) & 1) == 0)
              zero++;
          }
          ans = (ans * power(3, zero)) % mod;
        }
        System.out.println(ans);
      }
    }
  }

  private static int power(int a, int b) {
    if (b == 0)
      return 1;
    long half = power(a, b / 2);
    long half2 = half * half % mod;
    if (b % 2 == 1)
      half2 = half2 * a % mod;
    return (int) half2;
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