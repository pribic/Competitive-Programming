package codeforce.div2.r419;

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
 * @see <a href="https://codeforces.com/problemset/problem/816/B" target="_top">https://codeforces.com/problemset/problem/816/B</a>
 * @since 16/05/22 4:05 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);
  static int max = 200000;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int q = sc.nextInt();
        int[] raw = new int[max + 2];
        for (int i = 0; i < n; i++) {
          int l = sc.nextInt();
          int r = sc.nextInt();
          raw[l]++;
          raw[r + 1]--;
        }
        int cs = 0;
        for (int i = 0; i < raw.length; i++) {
          cs += raw[i];
          raw[i] = cs;
        }
        for (int i = 0; i < raw.length; i++) {
          if (raw[i] >= k)
            raw[i] = 1;
          else
            raw[i] = 0;
        }
        cs = 0;
        for (int i = 0; i < raw.length; i++) {
          cs += raw[i];
          raw[i] = cs;
        }
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
          int l = sc.nextInt();
          int r = sc.nextInt();
          sb.append(raw[r] - raw[l - 1]).append("\n");
        }
        System.out.print(sb);
      }
    }
  }

  static class BIT {
    long[] vals;

    BIT(int size) {
      vals = new long[size + 1];
    }

    void update(int idx, int x) { // add +x to idx index
      while (idx < vals.length) {
        vals[idx] += x;
        idx += idx & -idx;
      }
    }

    long query(int idx) {
      long s = 0;
      while (idx > 0) {
        s += vals[idx];
        idx -= idx & -idx;
      }
      return s;
    }

    long query(int l, int r) {
      return query(r) - query(l - 1);
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