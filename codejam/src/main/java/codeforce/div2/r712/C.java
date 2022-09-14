package codeforce.div2.r712;

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
 * @see <a href="https://codeforces.com/problemset/problem/1503/A" target="_top">https://codeforces.com/problemset/problem/1503/A</a>
 * @since 15/12/21 2:25 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        int cnt1 = 0, cnt0 = 0;
        for (char ch : str.toCharArray())
          if (ch == '1')
            cnt1++;
          else
            cnt0++;
        if (cnt1 % 2 == 1 || cnt0 % 2 == 1 || str.charAt(0) == '0' || str.charAt(n - 1) == '0') {
          System.out.println("NO");
        } else {
          System.out.println("YES");
          char[] one = new char[n];
          char[] two = new char[n];
          int onec = 0;
          char[] brackets = {'(', ')'};
          for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '1') {
              one[i] = two[i] = onec < cnt1 / 2 ? '(' : ')';
              onec++;
            } else {
              int zeroc = i - onec;
              one[i] = brackets[zeroc % 2];
              two[i] = brackets[1 - zeroc % 2];
            }
          }
          System.out.println(String.valueOf(one));
          System.out.println(String.valueOf(two));
        }
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