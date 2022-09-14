package codeforce.div4.r790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contests/1676/problem/D" target="_top">https://codeforces.com/contests/1676/problem/D</a>
 * @since 10/05/22 8:00 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  static int[] dx = {-1, -1, 1, 1};
  static int[] dy = {-1, 1, 1, -1};

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++)
          for (int j = 0; j < m; j++)
            board[i][j] = sc.nextInt();

        int max = 0;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            max = Math.max(max, calc(board, i, j));
          }
        }
        System.out.println(max);
      }
    }
  }

  private static int calc(int[][] board, int i, int j) {
    int sum = board[i][j];
    int ii = i;
    int jj = j;
    for (int dir = 0; dir < 4; dir++) {
      i = ii;
      j = jj;
      while (true) {
        int tx = i + dx[dir];
        int ty = j + dy[dir];
        if (tx >= 0 && ty >= 0 && tx < board.length && ty < board[0].length) {
          sum += board[tx][ty];
          i = tx;
          j = ty;
        } else {
          break;
        }
      }
    }
    return sum;
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