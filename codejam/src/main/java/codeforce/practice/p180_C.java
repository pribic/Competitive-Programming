package codeforce.practice;

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
 * @see <a href="https://codeforces.com/contest/180/problem/C" target="_top">https://codeforces.com/contest/180/problem/C</a>
 * @since 17/01/22 6:48 PM
 */
public class p180_C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        char[] s = sc.next().toCharArray();
        int[] ps = new int[s.length];
        int cs = 0;
        for (int i = 0; i < s.length; i++) {
          cs += Character.isUpperCase(s[i]) ? 1 : 0;
          ps[i] = cs;
        }

        int cost = sum(ps, 0, s.length - 1);
        for (int i = 0; i < s.length; i++) {
          //we will have upper case till ith index and then all lower case;
          cost = Math.min(cost, i + 1 - sum(ps, 0, i) + sum(ps, i + 1, s.length - 1));
        }
        System.out.println(cost);
      }
    }
  }

  static int sum(int[] ps, int l, int r) {
    return l == 0 ? ps[r] : ps[r] - ps[l - 1];
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
/*
mnAkOBuKxaiJwXhKnlcCvjxYXGXDoIqfUYkiLrdSYWhMemgWFzsgpoKOtHqooxbLYFuABWQSXuHdbyPVWyrkeEfqOsnEBikiqhfu

 */