package codeforce.div2.rharbour;

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
 * @see <a href="https://codeforces.com/contest/1553/problem/C" target="_top">https://codeforces.com/contest/1553/problem/C</a>
 * @since 22/07/21 8:56 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static int score = 10;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        score = 10;
        char[] scores = sc.next().toCharArray();
        generate(scores, 0);
        System.out.println(score);
      }
    }
  }

  private static void generate(char[] scores, int idx) {
    if (idx == 10) {
      score = Math.min(score, calc(scores));
      return;
    }
    if (scores[idx] == '?') {
      scores[idx] = '1';
      generate(scores, idx + 1);
      scores[idx] = '0';
      generate(scores, idx + 1);
      scores[idx] = '?';
    } else {
      generate(scores, idx + 1);
    }
  }

  private static int calc(char[] scores) {
    int a = 0, b = 0;
    int ra = 5;
    int rb = 5;
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        a += (scores[i] - '0');
        ra--;
      } else {
        b += (scores[i] - '0');
        rb--;
      }
      if (a > b + rb)
        return i + 1;
      if (b > a + ra)
        return i + 1;
    }
    return 10;
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