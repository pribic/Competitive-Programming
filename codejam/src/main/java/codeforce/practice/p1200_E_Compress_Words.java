package codeforce.practice;

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
 * @see <a href="https://codeforces.com/problemset/problem/1200/E" target="_top">https://codeforces.com/problemset/problem/1200/E</a>
 * @since 05/12/21 3:24 PM
 */
public class p1200_E_Compress_Words {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String[] words = new String[n];
        for (int i = 0; i < n; i++)
          words[i] = sc.next();
        StringBuilder cur = new StringBuilder(words[0]);
        for (int i = 1; i < n; i++) {
          int[] prefix = StringUtil.prefix_function(words[i] + "#" + cur.substring(Math.max(0, cur.length() - words[i].length())));
          int pl = prefix[prefix.length - 1];
          cur.append(words[i].substring(pl));
        }
        System.out.println(cur);
      }
    }
  }

  static class StringUtil {
    static int[] prefix_function(String s) {
      return prefix_function(s, 0, s.length());
    }

    // s[st..end)
    static int[] prefix_function(String s, int st, int end) {
      //out.println("s = " + s);
      int n = end - st;
      int[] pi = new int[n];
      for (int i = 1; i < n; i++) {
        int j = pi[i - 1];
        while (j > 0 && s.charAt(st + i) != s.charAt(st + j))
          j = pi[j - 1];
        if (s.charAt(st + i) == s.charAt(st + j))
          j++;
        pi[i] = j;
      }
      return pi;
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