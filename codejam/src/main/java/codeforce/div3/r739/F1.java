package codeforce.div3.r739;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
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
import static java.util.stream.Collectors.toCollection;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1560/problem/F1" target="_top">https://codeforces.com/contest/1560/problem/F1</a>
 * @since 18/08/21 9:24 PM
 */
public class F1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    //
    TreeSet<Long> oneBeautiful = new TreeSet<>();
    for (char c = '1'; c <= '9'; c++) {
      StringBuilder str = new StringBuilder(c + "");
      for (int times = 0; times < 11; times++) {
        oneBeautiful.add(Long.valueOf(str.toString()));
        str.append(c);
      }
    }
    TreeSet<Long> twoBeautiful = new TreeSet<>();
    for (char c = '0'; c <= '9'; c++) {
      for (char d = '0'; d <= '9'; d++) {
        for (int len = 1; len <= 11; len++)
          twoBeautiful.addAll(generate(c, d, len));
      }
    }

    twoBeautiful.addAll(oneBeautiful);
    /*
    out.println(oneBeautiful);
    out.println("===");
    out.println(twoBeautiful);
    out.println("====");
    for (long num : twoBeautiful) {
      int[] digits = new int[10];
      while (num > 0) {
        digits[(int) (num % 10)] = 1;
        num /= 10;
      }
      if(IntStream.of(digits).sum() > 2) {
        out.println("some issue " + num);
      }
    }*/
    
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        int k = sc.nextInt();
        if (k == 1)
          System.out.println(oneBeautiful.ceiling(n));
        else
          System.out.println(twoBeautiful.ceiling(n));
      }
    }
  }

  private static List<Long> generate(char c, char d, int len) {
    //we want to make all possible combinations of length len, using c and d
    List<Long> nums = new ArrayList<>();
    for (int i = 0; i < (1 << (len)); i++) {//
      StringBuilder str = new StringBuilder();
      for (int j = 0; j < len; j++) {
        if (((i >> j) & 1) == 1)
          str.append(c);
        else
          str.append(d);
      }
      nums.add(Long.valueOf(str.toString()));
    }
    return nums;
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