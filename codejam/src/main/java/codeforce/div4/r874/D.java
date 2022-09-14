package codeforce.div4.r874;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
 * @see <a href="https://codeforces.com/contest/1669/problem/D" target="_top">https://codeforces.com/contest/1669/problem/D</a>
 * @since 21/04/22 9:13 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
          if (str.charAt(i) == 'W') {
            //check with current sb
            if (!valid(sb))
              valid = false;
            sb = new StringBuilder();
          } else {
            sb.append(str.charAt(i));
          }
        }
        if (!valid(sb))
          valid = false;
        System.out.println(valid ? "YES" : "NO");
      }
    }
  }

  private static boolean valid(StringBuilder sb) {
    if (sb.length() == 0)
      return true;
    if (sb.length() == 1)
      return false;
    if (sb.length() == 2)
      return sb.toString().equals("BR") || sb.toString().equals("RB");
    boolean allsame = true;
    for (int i = 1; i < sb.length(); i++)
      if (sb.charAt(i) != sb.charAt(0))
        allsame = false;
    return !allsame;
  }

  //BB BR WW RRR W BR
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