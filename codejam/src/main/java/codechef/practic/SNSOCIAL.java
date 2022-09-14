package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
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
 * @see <a href="https://www.codechef.com/problems/SNSOCIAL" target="_top">https://www.codechef.com/problems/SNSOCIAL</a>
 * @since 21/09/21 6:18 PM
 */
public class SNSOCIAL {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        int maxCap = -1;
        for (int i = 0; i < n; i++) {
          grid[i] = new int[m];
          for (int j = 0; j < m; j++) {
            grid[i][j] = sc.nextInt();
            maxCap = Math.max(maxCap, grid[i][j]);
          }
        }
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] == maxCap) {
              queue.add(new int[]{i, j});
              dist[i][j] = 0;
              visited[i][j] = true;
            }
          }
        }
        int maxDist = 0;
        while (!queue.isEmpty()) {
          int[] cur = queue.pop();
          for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
              int x = cur[0];
              int tx = x + i;
              int y = cur[1];
              int ty = y + j;
              if (tx >= 0 && ty >= 0 && tx < n && ty < m && !visited[tx][ty]) {
                dist[tx][ty] = 1 + dist[x][y];
                maxDist = Math.max(maxDist, dist[tx][ty]);
                queue.add(new int[]{tx, ty});
                visited[tx][ty] = true;
              }
            }
          }
        }
        System.out.println(maxDist);
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