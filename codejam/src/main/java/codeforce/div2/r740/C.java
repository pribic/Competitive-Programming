package codeforce.div2.r740;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Path;
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
 * @see <a href="https://codeforces.com/contest/1561/problem/C" target="_top">https://codeforces.com/contest/1561/problem/C</a>
 * @since 24/08/21 8:28 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] caves = new int[n][]; // 0 max value, 1 count
        for (int i = 0; i < n; i++) {
          int k = sc.nextInt();
          caves[i] = new int[k];
          for (int j = 0; j < k; j++) {
            caves[i][j] = sc.nextInt();
          }
        }
        
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
          pairs[i] = new Pair(calcMinPower(caves[i]), caves[i]);
        }
        Arrays.sort(pairs, (a, b) -> {
          if (a.minPower == b.minPower)
            return b.caves.length - a.caves.length;
          return a.minPower - b.minPower;
        });
        //System.out.println(Arrays.toString(minPower));
        int l = 0;
        int r = Integer.MAX_VALUE;
        while (r > l + 1) {
          int mid = l + (r - l) / 2;
          if (check(mid, pairs))
            r = mid;
          else
            l = mid;
        }
        System.out.println(r);
      }
    }
  }

  static class Pair {
    int minPower;
    int[] caves;

    public Pair(int minPower, int[] caves) {
      this.minPower = minPower;
      this.caves = caves;
    }
  }

  private static int calcMinPower(int[] cave) {
    int extra = -1;
    for (int i = 0; i < cave.length; i++) {
      extra = Math.max(extra, cave[i] + 1 - i);
    }
    return extra;
  }

  private static boolean check(int mid, Pair[] caves) {
    int cur = mid;
    boolean valid = true;
    for (int i = 0; i < caves.length; i++) {
      for (int j = 0; j < caves[i].caves.length; j++) {
        if (cur > caves[i].caves[j]) {
          cur++;
        } else {
          valid = false;
        }
      }
    }
    return valid;
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