package codeforce.div3.r734;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
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

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://m1.codeforces.com/contest/1551/problem/B2" target="_top">https://m1.codeforces.com/contest/1551/problem/B2</a>
 * @since 23/07/21 8:26 PM
 */
public class B2 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          map.putIfAbsent(arr[i], new ArrayList<>());
          if (map.get(arr[i]).size() < k)
            map.get(arr[i]).add(i);
        }

        List<Integer> indexes = map.values().stream().flatMap(Collection::stream).collect(toList());

        indexes.sort((i, j) -> arr[i] - arr[j]);

        int[] ans = new int[n];
        int maxSize = indexes.size() / k * k;
        for (int i = 0; i < maxSize; i++) {
          ans[indexes.get(i)] = i % k + 1;
        }

        StringBuilder str = new StringBuilder();
        for (int num : ans) str.append(num).append(" ");
        System.out.println(str);
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