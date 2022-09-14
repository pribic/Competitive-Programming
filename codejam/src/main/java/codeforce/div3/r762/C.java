package codeforce.div3.r762;

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
 * @see <a href="https://codeforces.com/contest/1619/problem/C" target="_top">https://codeforces.com/contest/1619/problem/C</a>
 * @since 20/12/21 8:24 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String a = sc.next();
        String s = sc.next();
        int ai = a.length() - 1;
        int si = s.length() - 1;
        StringBuilder ans = new StringBuilder();
        while (ai >= 0 && si >= 0) {
          if (si < ai) {
            ans = new StringBuilder("");
            break;
          }
          char ach = a.charAt(ai);
          char sch = s.charAt(si);
          if (ach == sch) {
            ans.append('0');
            ai--;
            si--;
          } else if (ach < sch) { // 2 6
            ans.append((char) (sch - ach + '0'));
            ai--;
            si--;
          } else { // 4 + 7 = 11 , ach > sch
            if (si == 0 || s.charAt(si - 1) != '1') {
              ans = new StringBuilder("");
              break;
            }
            ans.append((char) ((10 + (sch - '0')) - (ach - '0')) % 10); // 8  4 = 2 
            si -= 2;
            ai--;
          }
        }
        if (ai >= 0)
          ans = new StringBuilder();
        if (ai == -1 && si >= 0)
          ans.append(new StringBuilder(s.substring(0, si + 1)).reverse());
        while (ans.length() > 0 && ans.charAt(ans.length() - 1) == '0')
          ans.deleteCharAt(ans.length() - 1);
        System.out.println(ans.length() == 0 || !valid(a, ans.reverse().toString(), s) ? -1 : ans);
      }
    }
  }

  private static boolean valid(String a, String b, String s) {
    while (a.length() < b.length())
      a = '0' + a;
    while (b.length() < a.length())
      b = '0' + b;
    StringBuilder sb = new StringBuilder();
    for (int i = a.length() - 1; i >= 0; i--) {
      sb.insert(0, (((a.charAt(i) - '0') + (b.charAt(i) - '0')) + ""));
    }
    return sb.toString().equals(s);
  }
/*
1
10


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
/*

599 +  322991= 322141810


 */