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
 * @see <a href="https://cses.fi/problemset/task/2417" target="_top">https://cses.fi/problemset/task/2417</a>
 * @since 29/06/21 11:51 AM
 */
public class CountingCoprimePairs {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    List<Integer> primes = new ArrayList<>();
    boolean[] isPrime = new boolean[(int) 1e6 + 1];
    Arrays.fill(isPrime, true);
    for (int i = 2; i < isPrime.length; i++) {
      if (isPrime[i]) {
        primes.add(i);
        if ((long) i * i < isPrime.length) {
          for (int j = i * i; j < isPrime.length; j += i)
            isPrime[j] = false;
        }
      }
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Set<Integer> nums = new HashSet<>();
        int max = -1;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          nums.add(arr[i]);
          max = Math.max(max, arr[i]);
        }
        Arrays.sort(arr);
        int coprimecnt = 0;
        for (int prime : primes) {
          int cnt = 0;
          for (int i = 0; i < arr.length; i++) {
            if (arr[i] % prime == 0) cnt++;
          }
          
        }
        System.out.println(coprimecnt);
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