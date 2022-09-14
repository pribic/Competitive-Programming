package codeforce.practice;

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
 * @see <a href="https://codeforces.com/contest/846/problem/F" target="_top">https://codeforces.com/contest/846/problem/F</a>
 * @since 02/09/21 6:38 PM
 */
public class p846F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] prev = new int[n];
        int[] nxt = new int[n];
        prev[0] = -1;
        nxt[n - 1] = n;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(arr[0], 0);
        for (int i = 1; i < n; i++) {
          if (map.containsKey(arr[i])) {
            prev[i] = map.get(arr[i]);
            map.put(arr[i], i);
          } else {
            prev[i] = -1;
            map.put(arr[i], i);
          }
        }
        map = new HashMap<>();
        map.put(arr[n - 1], n - 1);
        for (int i = n - 2; i >= 0; i--) {
          if (map.containsKey(arr[i])) {
            nxt[i] = map.get(arr[i]);
            map.put(arr[i], i);
          } else {
            nxt[i] = n;
            map.put(arr[i], i);
          }
        }
        System.out.println(Arrays.toString(prev));
        System.out.println(Arrays.toString(nxt));
        long ans = 0;
        for (int i = 0; i < n; i++) {
          int left = nxt[i] - i;
          int right = i - prev[i];
          ans += 2L * left * right - 1;
        }
        System.out.println((float) ans / Math.pow(n, 2));
      }
    }
  }// [1,1] [1, 2] || [2,2]
// 12 -> 3

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