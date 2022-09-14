package codeforce.contestat8.challenge31;

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

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/group/gwssXKhiVi/contest/363206/problem/C" target="_top">https://codeforces.com/group/gwssXKhiVi/contest/363206/problem/C</a>
 * @since 07/01/22 8:48 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        Pair[] input = new Pair[n];
        for (int i = 0; i < n; i++) {
          input[i] = new Pair(sc.next().charAt(0), sc.nextInt());
        }
        int[] ans = new int[1024];
        for (int x = 0; x <= 1023; x++) {
          ans[x] = eval(x, input);
        }
      }
    }
  }
  
  /*
  3
| 3
^ 2
| 1

(((x|3)^2)|1)
   */

  private static int eval(int x, Pair[] input) {
    for (Pair p : input) {
      switch (p.c) {
        case '|':
          x = x | p.val;
          break;
        case '&':
          x = x & p.val;
          break;
        case '^':
          x = x ^ p.val;
          break;
        default:
          break;
      }
    }
    return x;
  }

  static class Pair {
    char c;
    int val;

    public Pair(char c, int val) {
      this.c = c;
      this.val = val;
    }

    public Pair(int c, int val) {
      if (c == 1)
        this.c = '&';
      else if (c == 2)
        this.c = '|';
      else if (c == 3)
        this.c = '^';
      this.val = val;
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