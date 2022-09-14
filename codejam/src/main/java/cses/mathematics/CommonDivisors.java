package cses.mathematics;

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
import java.util.TreeMap;
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
 * @see <a href="https://cses.fi/problemset/task/1081" target="_top">https://cses.fi/problemset/task/1081</a>
 * @since 05/06/21 3:47 PM
 */
public class CommonDivisors {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      outer:
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[2 * 1000000 + 1];
        int max = -1;
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          arr[val]++;
          max = Math.max(max, val);
        }
        for (int i = max; i >= 1; i--) {
          int cnt = 0;
          for (int j = i; j <= max; j += i) {
            if(arr[j] > 0)
              cnt += arr[j];
            if (cnt > 1) {
              System.out.println(i);
              continue outer;
            }
          }
        }
    }
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