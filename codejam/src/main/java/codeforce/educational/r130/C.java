package codeforce.educational.r130;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
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
 * @see <a href="https://codeforces.com/contest/1697/problem/C" target="_top">https://codeforces.com/contest/1697/problem/C</a>
 * @since 13/06/22 1:46 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String s = sc.next();
        String t = sc.next();

        StringBuilder sb = build(s);
        StringBuilder tb = build(t);
        if (!sb.toString().equals(tb.toString()))
          System.out.println("NO");
        else {
          ArrayDeque<Integer>[] so = new ArrayDeque[3];
          ArrayDeque<Integer>[] to = new ArrayDeque[3];
          for (int i = 0; i < 3; i++) {
            so[i] = new ArrayDeque<>();
            to[i] = new ArrayDeque<>();
          }
          for (int i = 0; i < n; i++) {
            so[s.charAt(i) - 'a'].addLast(i);
            to[t.charAt(i) - 'a'].addLast(i);
          }
          boolean valid = compare(so, to, 0, (a, b) -> a > b) && compare(so, to, 2, (a, b) -> b > a);

          System.out.println(valid ? "YES" : "NO");

        }
      }
    }
  }

  private static boolean compare(ArrayDeque<Integer>[] so, ArrayDeque<Integer>[] to, int i, BiPredicate<Integer, Integer> funct) {
    while (!so[i].isEmpty()) {
      if (funct.test(so[i].peek(), to[i].peek()))
        return false;
      so[i].removeFirst();
      to[i].removeFirst();
    }
    return true;
  }

  private static StringBuilder build(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray())
      if (c != 'b')
        sb.append(c);
    return sb;
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