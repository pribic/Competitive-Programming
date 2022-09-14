package codeforce.div3.r765;

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
 * @see <a href="https://codeforces.com/contest/1625/problem/B" target="_top">https://codeforces.com/contest/1625/problem/B</a>
 * @since 12/01/22 6:00 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        ArrayList<Integer>[] indexes = new ArrayList[150000 + 1];
        for (int i = 0; i < n; i++) {
          if (indexes[arr[i]] == null)
            indexes[arr[i]] = new ArrayList<>();
          indexes[arr[i]].add(i);
        }
        int max = -1;
        for (int i = 0; i < indexes.length; i++) {
          ArrayList<Integer> idx = indexes[i];
          if (idx == null || idx.size() < 2)
            continue;
          for (int j = 0; j < idx.size() - 1; j++) {
            int first = idx.get(j);
            int second = idx.get(j + 1);
            max = Math.max(max, n - second + first);
          }
        }
        System.out.println(max);
      }
    }
  } //1 2 5 5 3  , 2 5 5 3 4

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
100
6
5 1 2 3 5 5
6
1 2 3 4 5 5
6
5 5 1 2 3 4
6
1 2 5 5 3 4
 */