package codejam.Y2021.rount3;

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
import java.util.TreeSet;
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
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/0000000000436142/0000000000813aa8" target="_top">https://codingcompetitions.withgoogle.com/codejam/round/0000000000436142/0000000000813aa8</a>
 * @since 06/06/21 6:37 AM
 */
public class BuildAPair {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        int[] digits = new int[str.length()];
        for (int i = 0; i < digits.length; i++)
          digits[i] = str.charAt(i) - '0';
        int sz1 = digits.length / 2;
        int sz2 = digits.length - sz1;
        Set<Long> first = new HashSet<>();
        for (int i = 1; i < 1 << sz1; i++) {
          long num = 0;
          for (int j = 0; j < sz1; j++) {
            if ((i >> j & 1) == 1) {
              num = num * 10 + digits[j];
            }
          }
          first.add(num);
        }
        System.out.println("first.toString() = " + first.toString());
        TreeSet<Long> second = new TreeSet<>();
        for (int i = 1; i < 1 << sz2; i++) {
          long num = 0;
          for (int j = 0; j < sz2; j++) {
            if ((i >> j & 1) == 1) {
              num = num * 10 + digits[sz1 + j];
            }
          }
          second.add(num);
        }
        System.out.println("second = " + second);
        long minDiff = Long.MAX_VALUE;
        for (long one : first) {
          Long candidate1 = second.lower(one);
          Long candidate2 = second.higher(one);
          if (candidate1 != null)
            minDiff = Math.min(minDiff, Math.abs(one - candidate1));
          if (candidate2 != null)
            minDiff = Math.min(minDiff, Math.abs(one - candidate2));
        }
        System.out.println(minDiff);
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