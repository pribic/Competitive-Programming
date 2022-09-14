package codeforce.div2.r785;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1673/problem/C" target="_top">https://codeforces.com/contest/1673/problem/C</a>
 * @since 30/04/22 9:33 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);
  static List<Integer> palNumbers = new ArrayList<>();
  static long[][] dp;
  static int max = 4 * 10000;
  static int mod = (int) (1e9 + 7);

  public static void main(String[] args) {
    preBuild();
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        System.out.println(dp[n][palNumbers.size() - 1]);
      }
    }
  }

  private static int floor(int n) {
    for (int i = palNumbers.size() - 1; i >= 0; i--)
      if (palNumbers.get(i) <= n)
        return i;
    return -1; // should never happen now
  }

  private static void preBuild() {
    for (int i = 1; i <= max; i++) {
      if (isPal(i)) {
        palNumbers.add(i);
      }
    }
  }

  private static boolean isPal(int n) {
    List<Integer> digits = new ArrayList<>();
    while (n > 0) {
      digits.add(n % 10);
      n /= 10;
    }
    List<Integer> dd = new ArrayList<>(digits);
    Collections.reverse(dd);
    return digits.equals(dd);
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