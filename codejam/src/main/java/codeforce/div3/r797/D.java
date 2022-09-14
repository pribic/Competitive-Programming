package codeforce.div3.r797;

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
 * @see <a href="https://codeforces.com/contest/1690/problem/D" target="_top">https://codeforces.com/contest/1690/problem/D</a>
 * @since 07/06/22 9:17 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.next();
        int[] arr = new int[str.length()];
        for (int i = 0; i < arr.length; i++)
          arr[i] = str.charAt(i) == 'W' ? 0 : 1;
        // 0001110011
        long[] ps = prefixSum(arr);
        long minsteps = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < n; i++) {
          long sum = rangeSum(ps, i, i + k - 1);
          minsteps = Math.min(minsteps, k - sum);
        }
        System.out.println(minsteps);
      }
    }
  }


  private static long rangeSum(long[] arr, int l, int r) {
    return l > 0 ? arr[r] - arr[l - 1] : arr[r];
  }


  private static long[] prefixSum(int[] arr) {
    long[] ps = new long[arr.length];
    long cs = 0;
    for (int i = 0; i < arr.length; i++) {
      cs += arr[i];
      ps[i] = cs;
    }
    return ps;
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