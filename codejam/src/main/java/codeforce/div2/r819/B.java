package codeforce.div2.r819;

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
 * @see <a href="https://codeforces.com/contest/1726/problem/B" target="_top">https://codeforces.com/contest/1726/problem/B</a>
 * @since 06/09/22 8:28 pm
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        StringBuilder ans = new StringBuilder();
        if (m < n) {
          ans.append("No");
        } else if (n == m) {
          ans.append("Yes").append("\n");
          for (int i = 0; i < n; i++) {
            ans.append("1 ");
          }
        } else { // m > n
          if (n % 2 == 1) {
            ans.append("Yes").append("\n");
            // 1 1 1 1 1 1 // odd times, last one will be m - (n - 1)
            for (int i = 0; i < n - 1; i++) {
              ans.append("1 ");
            }
            ans.append(m - (n - 1));
          } else {
            // 1 1 1 1 1 1 // even times
            int remaining = m - (n - 2);
            if (remaining % 2 == 1) {
              ans.append("No");
            } else {
              ans.append("Yes").append("\n");
              for (int i = 0; i < n - 2; i++)
                ans.append("1 ");
              ans.append(remaining / 2).append(" ").append(remaining / 2);
            }
          }
        }
        System.out.println(ans);
      }
    }
  }
  /*
  6 12
  1 1 1 1 
   */

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