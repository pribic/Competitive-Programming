package codeforce.div3.r725;

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
 * @see <a href="https://codeforces.com/contest/1538/problem/D" target="_top">https://codeforces.com/contest/1538/problem/D</a>
 * @since 10/06/21 9:24 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    int limit = 1000000000 + 1;
    boolean[] isPrime = new boolean[(int) (Math.sqrt(limit) + 1)];
    List<Integer> primes = new ArrayList<>();
    Arrays.fill(isPrime, true);
    for (int i = 2; i * i <= limit; i++) {
      if (isPrime[i]) {
        primes.add(i);
        for (int j = i * i; j < isPrime.length; j += i)
          isPrime[j] = false;
      }
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int a = sc.nextInt();
        int aa = a;
        int b = sc.nextInt();
        int bb = b;
        int k = sc.nextInt();
        Map<Integer, Integer> prime1 = new HashMap<>();
        Map<Integer, Integer> prime2 = new HashMap<>();
        int idx = 0;
        int totalFact = 0;
        while (a > 1 && idx < primes.size()) {
          int cnt = 0;
          while (a % primes.get(idx) == 0) {
            cnt++;
            a /= primes.get(idx);
          }
          if (cnt > 0) {
            prime1.put(primes.get(idx), cnt);
          }
          totalFact += cnt;
          idx++;
        }
        if (a > 1) {
          prime1.put(a, 1);
          totalFact++;
        }
        idx = 0;
        while (b > 1 && idx < primes.size()) {
          int cnt = 0;
          while (b % primes.get(idx) == 0) {
            cnt++;
            b /= primes.get(idx);
          }
          if (cnt > 0) {
            prime2.put(primes.get(idx), cnt);
          }
          totalFact += cnt;
          idx++;
        } //15485867 32452867 1
        if (b > 1) {
          prime2.put(b, 1);
          totalFact++;
        }
        a = aa;
        b = bb;
        if (totalFact < k) {
          System.out.println("NO");
          continue;
        }
        if (a == b) {
          if (k == totalFact || k != 1)
            System.out.println("YES");
          else
            System.out.println("NO");
        } else {
          int extra1 = 0;
          int extra2 = 0;
          for (int key : prime1.keySet()) {
            if (!prime2.containsKey(key))
              extra1 += prime1.get(key);
            else if (prime1.get(key) > prime2.get(key))
              extra1 += prime1.get(key) - prime2.get(key);
          }
          for (int key : prime2.keySet()) {
            if (!prime1.containsKey(key))
              extra2 += prime2.get(key);
            else if (prime2.get(key) > prime1.get(key))
              extra2 += prime2.get(key) - prime1.get(key);
          }
          if (extra1 == 0 || extra2 == 0) {
            if (k >= 1)
              System.out.println("YES");
            else
              System.out.println("NO");
          } else {
            if (k == 1)
              System.out.println("NO");
            else {
              System.out.println("YES");
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