package codeforce.div2.r745;

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
 * @see <a href="https://codeforces.com/contest/1581/problem/B" target="_top">https://codeforces.com/contest/1581/problem/B</a>
 * @since 30/09/21 4:00 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextInt();
        long m = sc.nextInt();
        long k = sc.nextInt();
        if (m > (n * (n - 1) / 2)) // more than max edges
          System.out.println("NO");
        else if (m < n - 1) {
          System.out.println("NO");
        } else if (k == 0 || k == 1) {
          if (m == 0)
            System.out.println("YES");
          else
            System.out.println("NO");
        } else if (k == 2) {
          // k - 1 = 1 , means diameter has to be 0
          if (m == 0)
            System.out.println("YES");
          else
            System.out.println("NO");
        } else if (m == 0 && n == 1) {
          System.out.println("YES");
        } else if (m == 0 && n > 1) {
          System.out.println("NO");
        } else if (k > 3) //we claim that if we build a star graph, max diameter will be 2
          System.out.println("YES");
        else { // k == 3
          // k - 1 = 2, max diameter has to be 1
          if ((n * (n - 1) / 2 == m))
            System.out.println("YES");
          else
            System.out.println("NO");
        }
      }
    }
  }

  static int findS(int s) {
    int l = 1, r = (s / 2) + 1;

    while (l <= r) {
      int mid = (l + r) / 2;
      int sum = mid * (mid + 1) / 2;
      if (sum == s)
        return mid;
      else if (sum > s)
        r = mid - 1;
      else
        l = mid + 1;
    }
    return -1;
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