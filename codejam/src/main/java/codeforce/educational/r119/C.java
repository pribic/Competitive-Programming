package codeforce.educational.r119;

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
 * @see <a href="https://codeforces.com/contest/1620/problem/C" target="_top">https://codeforces.com/contest/1620/problem/C</a>
 * @since 18/12/21 9:44 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        long x = sc.nextLong();
        String str = sc.next();
        StringBuilder ans = new StringBuilder();
        int li = str.lastIndexOf('a');
        if (li == -1) {
          append(ans, x);
        } else if ((str.length() - li) * k >= x) {
          append(ans, x-1);
          for (int i = li; i >= 0; i--)
            if (str.charAt(i) == 'a')
              ans.append('a');
        } else {
          long cnt = (str.length() - li - 1) * k;
          append(ans, cnt);
          x -= cnt;
          if (li < str.length() - 1)
            x--;
          for (int i = li; i >= 0; i--) {
            if (str.charAt(i) == '*') {
              int times = 0;
              while (times < k && x > 1) {
                ans.append('b');
                x -= cnt;
                times++;
              }
              cnt++;
            } else {
              ans.append('a');
            }
          }
        }
        System.out.println(ans.reverse());
      }
    }
  }

  private static void append(StringBuilder ans, long cnt) {
    for (int i = 0; i < cnt; i++)
      ans.append('b');
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