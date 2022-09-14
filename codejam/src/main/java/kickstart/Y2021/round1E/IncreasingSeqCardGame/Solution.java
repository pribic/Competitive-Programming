package kickstart.Y2021.round1E.IncreasingSeqCardGame;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/000000000085a709" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/000000000043585c/000000000085a709</a>
 * @since 22/08/21 11:21 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        double totalScore = 1;
        for (int score = 2; score < n; score++) {
          int fixed = n - score + 1;
          int eventsWithThisScore = nCr(n - 1, fixed - 1) * fact(n - fixed);
          //System.out.println("eventsWithThisScore = " + eventsWithThisScore);
          totalScore += (double) eventsWithThisScore * eventsWithThisScore / fact(n);
        }
        if (n > 1)
          totalScore += 1f / fact(n);
        System.out.println(totalScore);
      }
    }
  }

  private static int nCr(int n, int r) {
    if (r == 0)
      return 1;
    return fact(n) / (fact(n - r) * fact(r));
  }

  private static int nPr(int n, int r) {
    if (r == 0)
      return 1;
    return fact(n) / fact(n - r);
  }

  private static int fact(int n) {
    int f = 1;
    for (int i = 2; i <= n; i++)
      f *= i;
    return f;
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