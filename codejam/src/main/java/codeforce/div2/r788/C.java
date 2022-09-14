package codeforce.div2.r788;

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

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1670/problem/C" target="_top">https://codeforces.com/contest/1670/problem/C</a>
 * @since 06/05/22 9:30 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static int mod = (int) (1e9 + 7);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] valA = new int[n + 1];
        Arrays.fill(valA, -1);
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
          valA[a[i]] = i;
        }
        int[] b = new int[n];
        int[] valB = new int[n + 1];
        Arrays.fill(valB, -1);
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
          valB[b[i]] = i;
        }
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
          d[i] = sc.nextInt();
        }

        //handle same value case
        for (int i = 0; i < n; i++) {
          if (a[i] == b[i]) {
            d[i] = a[i];
            valA[d[i]] = valB[d[i]] = i;
          }
        }

        //find out cycles
        int cycle = 0;
        Set<Integer> cycleStart = new HashSet<>();
        for (int i = 0; i < n; i++)
          if (d[i] == 0)
            cycleStart.add(i);
        // we have possible cycle start locations.
        while (!cycleStart.isEmpty()) {
          Set<Integer> poss = new HashSet<>();
          int cur = cycleStart.iterator().next();
          int nxt = a[b[cur]];
          poss.add(cur);
          poss.add(nxt);
          boolean flag = true;
          while (nxt != cur) {
            if (flag) {
              nxt = valA[b[nxt]];
            } else {
              nxt = valB[a[nxt]];
            }
            poss.add(nxt);
            flag = !flag;
          }
          cycleStart.removeAll(poss);
          cycle++;
        }
        System.out.println(power(2, cycle));
        
/*
1 2 3 4 5 6 7
2 3 1 7 6 5 4
2 3 1 0 0 0 0

1 5 2 4 6 3
6 5 3 1 4 2
6 0 0 1 4 0


1 6 4 7 2 3 8 5
3 2 8 1 4 5 6 7
1 0 0 7 0 3 0 5
 */
      }
    }
  }

  private static int power(long base, int power) {
    long f = 1;
    for (int i = 0; i < power; i++) {
      f = f * base % mod;
    }
    return (int) f;
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