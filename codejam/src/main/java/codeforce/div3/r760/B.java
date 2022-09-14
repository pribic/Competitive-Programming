package codeforce.div3.r760;

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
 * @see <a href="https://codeforces.com/contest/1618/problem/B" target="_top">https://codeforces.com/contest/1618/problem/B</a>
 * @since 14/12/21 8:07 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String[] bi = new String[n - 2];
        for (int i = 0; i < n - 2; i++) {
          bi[i] = sc.next();
        }
        //abbaaba
        //
        StringBuilder prefix = new StringBuilder(bi[0]);
        int i = 1;
        for (; i < n - 2; i++) {
          if (bi[i - 1].charAt(1) == bi[i].charAt(0))
            prefix.append(bi[i].charAt(1));
          else
            break;
        }
        StringBuilder suffix = new StringBuilder();
        if (i < n - 2)
          suffix.append(bi[i++]);

        for (; i < n - 2; i++) {
          if (bi[i - 1].charAt(1) == bi[i].charAt(0))
            suffix.append(bi[i].charAt(1));
          else
            break;
        }
        if (prefix.length() + suffix.length() < n)
          prefix.append('a').append(suffix);
        else
          prefix.append(suffix);
        System.out.println(prefix);
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