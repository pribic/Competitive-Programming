package Q2021.round2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
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
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/0000000000435915/00000000007dc51c" target="_top">https://codingcompetitions.withgoogle.com/codejam/round/0000000000435915/00000000007dc51c</a>
 * @since 16/05/21 8:58 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);
  static final int mxn = 1000000;

  public static void main(String[] args) {
    preprocessing();
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      out.print("Case #" + tt + ": ");
      int n = sc.nextInt();
      int ans = 1;
      for (int factor : factors[n]) {
        //consider each factor as base
        if (factor > 2)
          ans = Math.max(ans, 1 + find(n / factor - 1));
      }
      out.println(ans);
    }
  }

  private static int find(int num) {
    return memo[num];
/*
    if (num == 2 || num == 3 || num == 4)
      return memo[num] = 1;
    if (num < 2)
      return 0;
    int ans = 1;
    for (int factor : factors[num])
      ans = Math.max(ans, 1 + find(num / factor - 1));
    return memo[num] = ans; */

  }

  static int[] memo = new int[mxn + 1];

  static List<Integer>[] factors = new ArrayList[mxn + 1];

  private static void preprocessing() {
    for (int i = 0; i < factors.length; i++) factors[i] = new ArrayList<>();
    for (int i = 2; i <= mxn; i++) {
      for (int j = i; j <= mxn; j += i)
        factors[j].add(i);
    }

    memo[2] = memo[3] = memo[4] = 1;

    for (int ii = 5; ii <= mxn; ii++) {
      for (int factor : factors[ii]) {
        if (ii - factor >= 2)
          memo[ii] = Math.max(memo[ii], 1 + memo[ii / factor - 1]);
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
      br = new BufferedReader(new InputStreamReader(f));
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