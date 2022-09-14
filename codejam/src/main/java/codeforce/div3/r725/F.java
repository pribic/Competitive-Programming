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
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1538/problem/F" target="_top">https://codeforces.com/contest/1538/problem/F</a>
 * @since 11/06/21 2:23 PM
 */
public class F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    long[] dp1 = new long[11];
    dp1[0] = 0;
    dp1[1] = 9;
    for (int i = 2; i < dp1.length; i++)
      dp1[i] = dp1[i - 1] + (i + dp1[i - 1]) * 9;
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int l = sc.nextInt();
        int r = sc.nextInt();
        System.out.println(find(digits(r), 0, dp1) - find(digits(l), 0, dp1));

      }
    }
  }

  private static List<Integer> digits(int num) {
    List<Integer> digits = new ArrayList<>();
    while (num > 0) {
      digits.add(num % 10);
      num /= 10;
    }
    for (int l = 0, r = digits.size() - 1; l < r; l++, r--) {
      int t = digits.get(l);
      digits.set(l, digits.get(r));
      digits.set(r, t);
    }
    return digits;
  }

  private static long find(List<Integer> digits, int idx, long[] dp1) {
    if (idx == digits.size())
      return 0;
    return digits.get(idx) * dp1[digits.size() - idx - 1] + digits.get(idx) * (digits.size() - idx) + find(digits, idx + 1, dp1);
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
