package codeforce.div3.r739;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1560/problem/F2" target="_top">https://codeforces.com/contest/1560/problem/F2</a>
 * @since 18/08/21 9:24 PM
 */
public class F2 {
  static FastScanner sc = new FastScanner(System.in);
  static long min = Long.MAX_VALUE;

  public static void main(String[] args) {

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        int k = sc.nextInt();
        // 724533
        // 7
        min = Long.MAX_VALUE;
        generate(str, k, 0, "", k, true);
        generate(str, k, 0, "", k, false);
        System.out.println(min);
      }
    }
  }

  static boolean generate(String str, int k, int idx, String cur, int kk, boolean any) {
    if (!valid(cur, kk))
      return false;
    if (cur.length() >= str.length()) {
      if (cur.compareTo(str) >= 0) {
        min = Long.parseLong(cur);
        return true;
      } else
        return false;
    } else {
      if (!any) {
        boolean ans = generate(str, k, idx + 1, cur + str.charAt(idx), kk, any);
        if (ans)
          return ans;
      } else {
        for (char c = any ? '0' : ((char) (str.charAt(idx) + 1)); c <= '9'; c++)
          if (generate(str, k - 1, idx + 1, cur + c, kk, true))
            return true;
      }
    }
    return false;
  }

  private static boolean valid(String cur, int kk) {
    Set<Character> chars = new HashSet<>();
    for (char c : cur.toCharArray())
      chars.add(c);
    return chars.size() <= kk;
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