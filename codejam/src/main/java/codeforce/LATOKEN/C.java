package codeforce.LATOKEN;

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
 * @see <a href="https://codeforces.com/contest/1534/problem/C" target="_top">https://codeforces.com/contest/1534/problem/C</a>
 * @since 13/06/21 11:15 PM
 */
public class C {

  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] map1 = new int[n + 1];
        int[] map2 = new int[n + 1];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
          map1[a[i]] = i;
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
          map2[b[i]] = i;
        }
        boolean[] done = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
          if (!done[i]) {
            cnt++;
            //find all cycles related to this cell
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            while (!queue.isEmpty()) {
              int idx = queue.removeFirst();
              if (!done[idx]) {
                done[idx] = true;
                queue.addFirst(map2[a[idx]]);
                queue.addFirst(map1[b[idx]]);
              }
            }
          }
        }
        System.out.println(fastExpo(2, cnt));
      }
    }
  }

  static final int mod = 1000000007;

  static long fastExpo(int a, int b) {
    if (b == 0)
      return 1;
    long ans1 = fastExpo(a, b / 2);
    ans1 %= mod;
    ans1 = (ans1 * ans1) % mod;
    return (b % 2 == 0) ? ans1 : (a * ans1) % mod;
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