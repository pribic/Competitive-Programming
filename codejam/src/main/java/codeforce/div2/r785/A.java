package codeforce.div2.r785;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1673/problem/A" target="_top">https://codeforces.com/contest/1673/problem/A</a>
 * @since 30/04/22 8:11 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        if (str.length() == 1) {
          System.out.println("Bob " + score(str));
        } else if (str.length() % 2 == 0) {
          System.out.println("Alice " + score(str));
        } else {// len >= 3 
          long alice1 = score(str.substring(0, str.length() - 1));
          long bob1 = score(str.substring(str.length() - 1));
          long alice2 = score(str.substring(1));
          long bob2 = score(str.substring(0, 1));
          System.out.println("Alice " + Math.max(alice1 - bob1, alice2 - bob2));
        }
      }
    }
  }
  // aaaz

  private static long score(String s) {
    long cnt = 0;
    for (char c : s.toCharArray())
      cnt += (c - 'a') + 1;
    return cnt;
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