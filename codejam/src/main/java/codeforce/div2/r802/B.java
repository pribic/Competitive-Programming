package codeforce.div2.r802;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
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
 * @see <a href="https://codeforces.com/contest/1700/problem/B" target="_top">https://codeforces.com/contest/1700/problem/B</a>
 * @since 19/06/22 2:56 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        if (str.startsWith("9")) {
          BigInteger given = new BigInteger(str);
          StringBuilder target = new StringBuilder();
          for (int i = 0; i <= n; i++)
            target.append("1");
          BigInteger bg2 = new BigInteger(target.toString());
          System.out.println(bg2.subtract(given));
        } else {
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < n; i++) {
            sb.append((char) (('9' - str.charAt(i)) + '0'));
          }
          System.out.println(sb);
        }
      }
    }
  }
  /*
  
  1023
  8976
     9
   */

  private static StringBuilder can(String str, int first, int carry) {
    StringBuilder sb = new StringBuilder();
    int tr = 9 + carry + first; //we will build this in target
    String rev = new StringBuilder(tr + "").reverse().toString();
    
    //    0
    return sb;
  }
  
  /*
  1023
  
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