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
 * @see <a href="https://codeforces.com/contest/459/problem/D" target="_top">https://codeforces.com/contest/459/problem/D</a>
 * @since 23/10/21 12:16 PM
 */
public class p459D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 10;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> freq = new HashMap<>();
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
          left[i] = freq.get(arr[i]);
        }
        int[] right = new int[n];
        Map<Integer, Integer> reverseFreq = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
          reverseFreq.put(arr[i], reverseFreq.getOrDefault(arr[i], 0) + 1);
          right[i] = reverseFreq.get(arr[i]);
        }
        long total = 0;
        BIT bit = new BIT(1000000);
        for (int i = n - 1; i >= 0; i--) {
          total += bit.get(left[i] - 1);
          bit.set(right[i], 1);
        }
        System.out.println(total);
      }
    }
  }
  
  

  static class BIT {
    int[] data;

    BIT(int n) {
      data = new int[n + 1];
    }

    void set(int idx, int val) {
      while (idx < data.length) {
        data[idx] += val;
        idx += idx & -idx;
      }
    }

    int get(int idx) {
      int sum = 0;
      while (idx > 0) {
        sum += data[idx];
        idx -= idx & -idx;
      }
      return sum;
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