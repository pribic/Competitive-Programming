package codeforce.div3.r760;

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
 * @see <a href="https://codeforces.com/contest/1618/problem/D" target="_top">https://codeforces.com/contest/1618/problem/D</a>
 * @since 14/12/21 9:03 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long sum = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          sum += arr[i];
          add(map, arr[i]);
        }
        long extScore = 0;
        for (int i = 0; i < k; i++) {
          int num1 = 0;
          int num2 = 0;
          if (map.size() == 1) {
            num1 = num2 = map.firstKey();
          } else {
            boolean foundone = false;
            for (int key : map.descendingKeySet()) {
              if (!foundone) {
                num1 = key;
              } else {
                num2 = key;
                break;
              }
              foundone = true;
            }
          }
          remove(map, num1);
          remove(map, num2);
          extScore += num2 / num1;
          sum -= num1 + num2;
        }
        System.out.println(sum + extScore);
      }
    }
  }

  static void add(TreeMap<Integer, Integer> map, int num) {
    map.put(num, map.getOrDefault(num, 0) + 1);
  }

  static void remove(TreeMap<Integer, Integer> map, int num) {
    if (map.get(num) == 1)
      map.remove(num);
    else
      map.put(num, map.get(num) - 1);
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