package codeforce.div2.r726;

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
 * @see <a href="https://codeforces.com/contest/1537/problem/B" target="_top">https://codeforces.com/contest/1537/problem/B</a>
 * @since 18/06/21 8:20 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextInt();
        long m = sc.nextInt();
        long x = sc.nextInt();
        long y = sc.nextInt();
        long[][] corners = new long[][]{{1, 1}, {1, m}, {n, m}, {n, 1},};
        int idxMax = -1;
        long maxDistance = -1;
        for (int i = 0; i < 4; i++) {
          long dist = Math.abs(x - corners[i][0]) + Math.abs(y - corners[i][1]);
          if (dist > maxDistance) {
            maxDistance = dist;
            idxMax = i;
          }
        }
        int another = (idxMax + 2) % 4;
        System.out.println(corners[idxMax][0] + " " + corners[idxMax][1] + " " + corners[another][0] + " " + corners[another][1]);
      }
    }
  }

  /*
  
1 2 2 3
4 1 4 4
3 1 1 5
5 1 1 1
1 1 2 1
1 1 1 1
50 1 1 1000000000
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