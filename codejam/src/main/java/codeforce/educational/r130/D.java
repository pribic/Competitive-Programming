package codeforce.educational.r130;

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
 * @see <a href="https://codeforces.com/contest/1697/problem/D" target="_top">https://codeforces.com/contest/1697/problem/D</a>
 * @since 13/06/22 1:58 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        char[] str = new char[n];
        str[0] = queryOne(1);
        int diffc = 1;
        int[] lastC = new int[26];
        lastC[str[0] - 'a'] = 0;
        Arrays.fill(lastC, -1);
        for (int i = 1; i < n; i++) {
          // is this a new char or one of the previous one
          int dist = queryTwo(1, i + 1);
          if (dist == diffc) {
            //this is one of the previously seen character
            
          } else { // this means it is a new one
            str[i] = queryOne(i + 1);
            lastC[str[i] - 'a'] = i;
          }
        }

        answer(String.valueOf(str));
      }
    }
  }

  static char queryOne(int i) {
    out.println("? 1 " + i);
    return sc.next().charAt(0);
  }

  static int queryTwo(int l, int r) {
    out.println("? 2 " + l + " " + r);
    return sc.nextInt();
  }

  static void answer(String str) {
    out.println("! " + str);
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