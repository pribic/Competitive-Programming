package codeforce.div2.r795;

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
 * @see <a href="https://codeforces.com/contest/1691/problem/B" target="_top">https://codeforces.com/contest/1691/problem/B</a>
 * @since 31/05/22 8:09 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n + 2];
        for (int i = 1; i <= n; i++) {
          arr[i] = sc.nextInt();
        }
        arr[0] = -1;
        arr[n + 1] = -1;
        //we shall not have a unique element
        boolean valid = true;
        int st = 1;
        int end = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; i++) {
          if (arr[i] != arr[i - 1] && arr[i] != arr[i + 1])
            valid = false;
          else if (i > 1 && arr[i] != arr[i - 1]) {
            generateAns(st, end, ans);
            st = i;
            end = i;
          } else { // this is extension of previous section
            end++;
          }
        }
        generateAns(st, end, ans);
        if (valid)
          System.out.println(ans);
        else
          System.out.println(-1);
      }
    }
  }

  private static void generateAns(int st, int end, StringBuilder ans) {
    // beginning of new section.
    for (int j = st + 1; j <= end; j++)
      ans.append(j).append(" ");
    ans.append(st).append(" ");
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