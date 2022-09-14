package codeforce.div2.r729;

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
 * @see <a href="https://codeforces.com/contest/1542/problem/C" target="_top">https://codeforces.com/contest/1542/problem/C</a>
 * @since 03/07/21 7:13 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
   /* long limit = (long)1e17;
    long fact = 1;
    long mul = 2;
    while(fact < limit) {
      fact = fact * mul;
      mul++;
      out.println(fact);
    }
    fact = 1;
    for(int i = 2; i <= 20; i++) {
      fact *= i;
    }
    out.println(fact);
    out.println("10000000000000000");
    out.println(mul);*/
    int mod = (int) 1e9 + 7;
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        //for each number, ans is <= 21,
        // we need to find frequency of each of them
        long n = sc.nextLong();
        if (n == 1)
          System.out.println(2);
        else {
          int ans = 0;
          long fact = 1;
          for (int i = 2; i <= 20; i++) {
            ans += (n + 1) / 2;
          }
        }
      }
    }
  }
  /*
  
  f(1) = 2
  f(2) = 2 + 3
  f(3) = f(2) + 2
  4 -> 3
  5 -> 2
  // for all odd numbers, 2 is the ans, so 2 * (n + 1) / 2
  2 -> 3
  4 -> 3
  6 -> 
  
  f(10) = 2 + 3 + 2 + 3 + 2 + 4 + 2 + 3 + 2 + 3
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