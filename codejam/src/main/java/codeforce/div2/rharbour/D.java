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
 * @see <a href="https://codeforces.com/contest/1553/problem/D" target="_top">https://codeforces.com/contest/1553/problem/D</a>
 * @since 22/07/21 9:43 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        char[] a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();
        boolean flag = solve(a, b, 0);
        flag = flag | solve(a, b, 1);
        System.out.println(flag ? "YES" : "NO");
      }
    }
  }

  /*
  
  
  observation 1
  
  type b type b type b == type type type b b b 
  
  a b c d e f g h i j
  
  a b c f
  
  
  a b
  
  - - - a - - - a - - - b - b
  
  
  odd even odd even odd even
  
  even odd even odd even odd
   */
  static boolean solve(char[] a, char[] b, int parity) {
    int idx = 0;
    for (int i = 0; i < a.length; i++) {
      if (i % 2 == parity) {
        if (a[i] == b[idx]) {
          parity = 1 - parity;
          idx++;
        }
      }
      if (idx == b.length) {
        return (a.length - i - 1) % 2 == 0;
      }
    }
    return false;
  }
  /*





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