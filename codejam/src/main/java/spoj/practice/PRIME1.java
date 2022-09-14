package spoj.practice;

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
 * @see <a href="https://www.spoj.com/problems/PRIME1/" target="_top">https://www.spoj.com/problems/PRIME1/</a>
 * @since 05/06/21 6:54 PM
 */
public class PRIME1 {
  static FastScanner sc = new FastScanner(System.in);

  static int lim = 32000;
  static boolean[] b = new boolean[lim];
  static List<Integer> prime = new ArrayList<>();
  public static void main(String[] args) {
    Arrays.fill(b, true);
    for(int i = 2; i < lim; i++) {
      if(b[i]) {
        prime.add(i);
        for(int j = i * i; j < lim; j +=i)
          b[j] = false;
      }
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long l = sc.nextLong();
        long r = sc.nextLong();
        primeInRange(l, r);
      }
    }
  }

  private static void primeInRange(long l, long r) {
    boolean[] isPrime = new boolean[(int) (r - l + 1)];
    Arrays.fill(isPrime, true);
    for(int p : prime) {
      if(p * p > r)
        break;
      int num = (int) ((l + p - 1) / p * p);
      for(int j = Math.max(p*p, num); j < lim; j += p)
        isPrime[(int) (j - l)] = false;
    }
    for(int i = 0; i < isPrime.length; i++) {
      out.println(i + l);
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