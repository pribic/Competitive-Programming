package codeforce.div2.deltix.Autumn2021;

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
 * @see <a href="https://codeforces.com/contest/1609/problem/B" target="_top">https://codeforces.com/contest/1609/problem/B</a>
 * @since 28/11/21 8:19 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        char[] str = sc.next().toCharArray();
        int cnt = 0;
        for (int i = 0; i < n - 2; i++) {
          if (isAbcAt(str, i))
            cnt++;
        }
        while (q-- > 0) {
          int pos = sc.nextInt() - 1;
          char c = sc.next().charAt(0);
          if (str[pos] != c) {
            //if this is part of a valid substring
            cnt = updateCount(n, str, cnt, pos, -1);
            str[pos] = c;
            //if any new is formed
            cnt = updateCount(n, str, cnt, pos, 1);
          }
          System.out.println(cnt);
        }
      }
    }
  }

  private static int updateCount(int n, char[] str, int cnt, int pos, int add) {
    for (int j = pos - 2; j <= pos; j++) {
      int end = j + 2;
      if (j >= 0 && end < n) {
        if (isAbcAt(str, j)) {
          cnt += add;
        }
      }
    }
    return cnt;
  }


  private static boolean isAbcAt(char[] str, int i) {
    return str[i] == 'a' && str[i + 1] == 'b' && str[i + 2] == 'c';
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