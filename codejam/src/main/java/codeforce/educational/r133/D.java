package codeforce.educational.r133;

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
 * @see <a href="https://codeforces.com/contest/1716/problem/D" target="_top">https://codeforces.com/contest/1716/problem/D</a>
 * @since 04/08/22 8:48 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);
  static int mod = 998244353;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] ways = new long[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        ways[0] = 1;
        while (!q.isEmpty()) {
          int sz = q.size();
          for (int i = 0; i < sz; i++) {
            int p = q.removeFirst(); // this is where we are. from here, we can make jump which is in multiple of k
            int jp = k;
            while (p + jp <= n) {
              q.addLast(p + jp);
              //jp represents the multiple of k. 
              // we will jump to p + jp point
              ways[p + jp]++;// = ways[p + jp] + ways[p];
              ways[p + jp] %= mod;
              jp += k; //next multiple of k
            }
          }
          /*StringBuilder sb = new StringBuilder();
          for (int i = 1; i <= n; i++)
            sb.append(ways[i]).append(" ");
          System.out.println(sb);
          System.out.println("k = " + k);*/
          k++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
          sb.append(ways[i]).append(" ");
        System.out.println(sb);
      }
    }
  }
  /*
  0
  1 0 - 1 - 3/5/7
  2 0 - 2
  3 0 - 3
  4 0 - 4
  5 0 - 5
  6 0 - 6
  7 0 - 7
  8 0 - 8
   */

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