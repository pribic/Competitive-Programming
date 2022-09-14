package codeforce.goodbye2021;

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
 * @see <a href="https://codeforces.com/contest/1616/problem/B" target="_top">https://codeforces.com/contest/1616/problem/B</a>
 * @since 29/12/21 9:13 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        boolean done = false; // ddccb ddc
        if (allsame(str) || str.charAt(0) == str.charAt(1)) { // ypqb , yppy
          System.out.println(str.charAt(0) + "" + str.charAt(0));
          continue;
        }
        // dccc
        String str1 = "";
        for (int i = 0; i < n - 1; i++) {
          if (str.charAt(i) < str.charAt(i + 1)) {
            StringBuilder sb = new StringBuilder(str.substring(0, i + 1));
            str1 = sb.toString() + new StringBuilder(sb).reverse();
            done = true;
            break;
          }
        }
        boolean done2 = false;
        String str2 = "";
        for (int i = 0; i < n - 1; i++) {
          if (str.charAt(i) <= str.charAt(i + 1)) {
            StringBuilder sb = new StringBuilder(str.substring(0, i + 1));
            str2 = sb.toString() + new StringBuilder(sb).reverse();
            done2 = true;
            break;
          }
        }
        if(done || done2) {
          if(done && done2) {
            System.out.println(str1.compareTo(str2) < 0 ? str1 : str2);
          } else if(done) {
            System.out.println(str1);
          } else{
            System.out.println(str2);
          }
        }
        //bbaa
        //baa
        //baa
        if (!done && !done2) {
          System.out.println(str);
        }
      }
    }
  }

  private static boolean allsame(String str) {
    for (int i = 1; i < str.length(); i++)
      if (str.charAt(i) != str.charAt(0))
        return false;
    return true;
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