package codeforce.div2.deltix.Autumn2021;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1609/problem/C" target="_top">https://codeforces.com/contest/1609/problem/C</a>
 * @since 28/11/21 8:39 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static boolean[] isPrime;

  public static void main(String[] args) {
    prePrime();
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int e = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        List<Integer> primeIdx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          if (isPrime[arr[i]])
            primeIdx.add(i);
        }
        long cnt = 0;
        for (int pIDx : primeIdx) {
          long mul = 1;
          long right = 1;
          while (pIDx + mul * e < n && arr[pIDx + (int) mul * e] == 1) {
            right++;
            mul++;
          }
          long left = 1;
          mul = 1;
          while (pIDx - mul * e >= 0 && arr[pIDx - (int) mul * e] == 1) {
            left++;
            mul++;
          }
          cnt += left * right - 1;

        }
        System.out.println(cnt);
      }
    }
  }

  private static void prePrime() {
    isPrime = new boolean[1000000 + 1];
    Arrays.fill(isPrime, true);
    isPrime[1] = false;
    for (int i = 2; i < isPrime.length; i++) {
      if (isPrime[i]) {
        for (int j = 2 * i; j < isPrime.length; j += i)
          isPrime[j] = false;
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