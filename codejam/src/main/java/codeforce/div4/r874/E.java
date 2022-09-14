package codeforce.div4.r874;

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
 * @see <a href="https://codeforces.com/contest/1669/problem/E" target="_top">https://codeforces.com/contest/1669/problem/E</a>
 * @since 25/04/22 2:17 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long cnt = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
          String word = sc.next();
          //first different, second same
          for (char c = 'a'; c <= 'k'; c++) {
            if (c != word.charAt(0)) {
              String look = String.valueOf(c) + word.charAt(1);
              //System.out.printf("", look);
              cnt += map.getOrDefault(look, 0);
            }
          }
          //first same, second different
          for (char c = 'a'; c <= 'k'; c++) {
            if (c != word.charAt(1)) {
              String look = word.charAt(0) + String.valueOf(c);
              //System.out.println(look);
              cnt += map.getOrDefault(look, 0);
            }
          }
          map.put(word, map.getOrDefault(word, 0) + 1);
        }
        System.out.println(cnt);
      }
    }
  }
  //jf

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
aa
bb
cc
ac
ca
bb
aa
 */
