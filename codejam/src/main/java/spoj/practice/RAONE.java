package spoj.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.spoj.com/problems/RAONE/" target="_top">https://www.spoj.com/problems/RAONE/</a>
 * @since 17/11/21 5:39 PM
 */
public class RAONE {
  static FastScanner sc = new FastScanner(System.in);
  static int[][][] dp;

  public static void main(String[] args) {
    Map<Point, Integer> m = new HashMap<>();
    m.put(new Point(1,1), 1);
    m.put(new Point(1,1), 1);
    System.out.println(m);
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String l = (sc.nextInt() - 1) + "";
        String r = sc.next();
        System.out.println(solve(r) - solve(l));
      }
    }
  }

  //we add even indices, we subtract odd indices
  // in the end -1 or 1 diff is our ans
  private static int solve(String n) {
    dp = new int[n.length()][200][2];
    for (int dig = 0; dig < get(n, 0); dig++)
      dp[0][100 + dig][0]++;
    dp[0][100 + get(n, 0)][1]++;
    for (int i = 1; i < n.length(); i++) {
      for (int j = 0; j < 90; j++) {
        for (int dig = 0; dig < 10; dig++) {
          if (i % 2 == 0) { //add
            dp[i][j][0] += dp[i][100 + j - dig][0];
            if (dig < get(n, i))
              dp[i][j][0] += dp[i][100 + j - dig][1];
          } else { // subtract
            dp[i][j][0] += dp[i][100 + j + dig][0];
            dp[i][j][0] += dp[i][100 + j + dig][1];
          }
        }
        if (i % 2 == 0)
          dp[i][j][1] += dp[i][100 + j - get(n, i)][1];
        else
          dp[i][j][1] += dp[i][100 + j + get(n, i)][1];
      }
    }
    //now we know all the buckets
    System.out.println(n + " = " + dp[n.length() - 1][100 + 1][0]);
    return dp[n.length() - 1][100 + 1][0];
  }

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Point.class.getSimpleName() + "[", "]")
        .add("x=" + x)
        .add("y=" + y)
        .toString();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
  // 2 3 5 7 11 12 14 16
  static int get(String s, int i) {
    return s.charAt(i) - '0';
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