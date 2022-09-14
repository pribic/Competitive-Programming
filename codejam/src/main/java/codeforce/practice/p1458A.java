package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/1458/A" target="_top">https://codeforces.com/problemset/problem/1458/A</a>
 * @since 19/06/21 9:59 PM
 */
public class p1458A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextLong();
        }
        long[] b = new long[m];
        for (int i = 0; i < m; i++) {
          b[i] = sc.nextLong();
        }
        long gcd = 0;
        for (int i = 2; i < n; i++)
          gcd = gcd(gcd, Math.abs(a[i] - a[0]));
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++)
          ans.append(gcd(a[0] + b[i], gcd)).append(" ");
        System.out.println(ans);
        /*
        a1 a2 a3...an
        b1 b2 b3...bn
        a1 + b1, a2 + b1, a3 + b1 .. an + b1 -> 
        a1 + b1, a2 - a1, a3 - a1 .. an - a1
        //goal is to reuse the gcd again for subsequent operations 
        a1 + b2, a2 + b2, a3 + b2 .. an + b2
        a1 + b2, a2 - a1, a3 - a1 .. an - a1 
         */
      }
    }
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
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