package codeforce.div2.r791;

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
 * @see <a href="https://codeforces.com/contest/1679/problem/C" target="_top">https://codeforces.com/contest/1679/problem/C</a>
 * @since 14/05/22 3:58 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        BIT row = new BIT(n); // 0 means that row is impact free, 1 means that row has impact
        BIT col = new BIT(n);
        int[] rr = new int[n + 1];
        int[] cc = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
          int type = sc.nextInt();
          if (type == 1) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            rr[x]++;
            cc[y]++;
            if (rr[x] == 1) {
              //new row got impacted
              row.update(x, 1);
            }
            if (cc[y] == 1) {
              //new column got impacted
              col.update(y, 1);
            }
          } else if (type == 2) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            rr[x]--;
            cc[y]--;
            if (rr[x] == 0) {
              //this row got out of impact
              row.update(x, -1);
            }
            if (cc[y] == 0) {
              //this col has no impact now
              col.update(y, -1);
            }
          } else {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int diffRow = x2 - x1 + 1;
            int diffCol = y2 - y1 + 1;
            int impactedRow = row.get(x1, x2);
            int impactedCol = col.get(y1, y2);
            sb.append(impactedRow == diffRow || impactedCol == diffCol ? "Yes" : "No").append("\n");
          }
        }
        System.out.print(sb);
      }
    }
  }

  static class BIT {
    int[] val;

    BIT(int n) {
      val = new int[n + 1];
    }

    void update(int i, int x) {
      while (i < val.length) {
        val[i] += x;
        i += i & -i;
      }
    }

    int get(int i) {
      int sum = 0;
      while (i > 0) {
        sum += val[i];
        i -= i & -i;
      }
      return sum;
    }

    int get(int l, int r) {// sum of [l , r]
      return get(r) - get(l - 1);
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