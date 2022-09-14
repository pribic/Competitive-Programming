package codeforce.div3.r731;

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
 * @see <a href="https://codeforces.com/contest/1547/problem/B" target="_top">https://codeforces.com/contest/1547/problem/B</a>
 * @since 10/07/21 8:17 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String s = sc.next();
        int[] cnt = new int[26];
        for (char c : s.toCharArray())
          cnt[c - 'a']++;
        boolean valid = true;
        for (int i = 0; i < 26; i++) {
          if (cnt[i] > 1) valid = false;
          if (i > 0 && cnt[i] > 0 && cnt[i - 1] == 0) valid = false;
        }
        if (cnt[0] == 0) valid = false;
        if (!valid) {
          System.out.println("NO");
        } else {
          int idx = s.indexOf('a');
          int l = idx;
          int r = idx;
          int times = 1;
          while (times < s.length() && valid) {
            if (l - 1 >= 0 && (s.charAt(l - 1) - 'a') == times) {
              l = l - 1;
            } else if ((r + 1 < s.length() && (s.charAt(r + 1) - 'a') == times)) {
              r = r + 1;
            } else {
              valid = false;
            }
            times++;
          }
          System.out.println(valid ? "YES" : "NO");
        }
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