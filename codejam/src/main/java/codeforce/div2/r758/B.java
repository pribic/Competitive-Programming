package codeforce.div2.r758;

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
 * @see <a href="https://codeforces.com/contest/1608/problem/B" target="_top">https://codeforces.com/contest/1608/problem/B</a>
 * @since 11/12/21 3:45 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        if (Math.abs(a - b) > 1 || (n - a - b) < 2)
          System.out.println(-1);
        else if (a == 0 && b == 0) {
          for (int i = 1; i <= n; i++)
            System.out.print(i + " ");
          System.out.println();
        } else if (a == 0 && b == 1) {
          System.out.print(n + " ");
          for (int i = 1; i <= n - 1; i++)
            System.out.print(i + " ");
          System.out.println();

        } else if (a == 1 && b == 0) {
          System.out.print("1 ");
          for (int i = n; i > 1; i--)
            System.out.print(i + " ");
          System.out.println();
        } else {
          int[] ans = new int[n];
          int idx = 1;
          int first = 0;
          int second = 0;
          int diff1 = 0;
          int diff2 = 0;
          int diff3 = 0;
          int st = 0;
          if (a > b) {
            first = n;
            second = 1;
            diff1 = -1;
            diff2 = 1;
            st = n - a;
            diff3 = -1;

          } else {
            first = 1;
            second = n;
            diff1 = 1;
            diff2 = -1;
            st = b + 1;
            diff3 = 1;
          }
          if (a == b) {
            diff3 = -1;
            st = n - a;
          }
          for (int i = 0; i < Math.max(a, b); i++, idx += 2, first += diff1)
            ans[idx] = first;
          idx = 2;
          for (int i = 0; i < Math.min(a, b); i++, idx += 2, second += diff2)
            ans[idx] = second;
          idx = a + b + 1;
          while (idx < n) {
            ans[idx++] = st;
            st += diff3;
          }
          idx = 0;
          while (ans[idx] == 0) {
            ans[idx++] = st;
            st += diff3;
          }
          StringBuilder sb = new StringBuilder();
          for (int num : ans)
            sb.append(num).append(" ");
          System.out.println(sb);
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