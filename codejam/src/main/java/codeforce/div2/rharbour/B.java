package codeforce.div2.rharbour;

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


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1553/problem/B" target="_top">https://codeforces.com/contest/1553/problem/B</a>
 * @since 22/07/21 8:26 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String a = sc.next();
        String b = sc.next();
        if (a.equals(b)) {
          System.out.println("YES");
          continue;
        }
        boolean flag = false;
        for (int i = 0; i < a.length(); i++) {
          if (a.charAt(i) == b.charAt(0)) {
            flag = generateAll(a, i, b);
          }
          if (flag) break;
        }
        // System.out.println(strings);
        System.out.println(flag ? "YES" : "NO");
      }
    }
  }

  //1000101001
  /*
  ab cde dc e
  
  
  cdedce
  cdedcb
   */


  private static boolean generateAll(String a, int i, String b) {
    for (int j = i; j < a.length(); j++) {
      //j is ending index while moving to right
      String right = a.substring(i, j + 1);
      if (!b.startsWith(right)) continue;
      if (right.equals(b)) return true;
      StringBuilder left = new StringBuilder();
      for (int k = j - 1; k >= 0; k--) {
        if ((right + left).equals(b))
          return true;
        left.append(a.charAt(k));
      }
      if ((right + left).equals(b))
        return true;
    }
    return false;
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