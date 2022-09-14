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
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/problems/CLLEXO" target="_top">https://www.codechef.com/problems/CLLEXO</a>
 * @since 25/09/21 7:49 PM
 */
public class CLLEXO {
  static FastScanner sc = new FastScanner(System.in);
  static int[] dx = {0, 1};
  static int[] dy = {1, 0};

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
          String s = sc.next();
          for (int j = 0; j < s.length(); j++) {
            grid[i][j] = s.charAt(j) - 'a';
          }
        }
        //check if we can reach n - 1, m - 1 from i, j
        boolean[][] reachable = new boolean[n][m];
        for (int row = n - 1; row >= 0; row--) {
          for (int col = m - 1; col >= 0; col--) {
            if (row == n - 1 && col == m - 1) {
              reachable[row][col] = true;
            } else if ((char) (grid[row][col] + 'a') == '#') {
              reachable[row][col] = false;
            } else if (row == n - 1) {
              reachable[row][col] = reachable[row][col + 1];
            } else if (col == m - 1) {
              reachable[row][col] = reachable[row + 1][col];
            } else {
              reachable[row][col] = reachable[row + 1][col] || reachable[row][col + 1];
            }
          }
        }
        boolean[][] visited = new boolean[n][m];
        ArrayDeque<int[]> nodes = new ArrayDeque<>();
        nodes.add(new int[]{0, 0});
        StringBuilder path = new StringBuilder();
        path.append(conv(grid[0][0]));

        visited[0][0] = true;
        while (!nodes.isEmpty()) {
          int sz = nodes.size();
          // System.out.println("sz = " + sz);
          List<int[]> nextLevel = new ArrayList<>();
          int min = Integer.MAX_VALUE;
          for (int i = 0; i < sz; i++) {
            int[] cur = nodes.pop();
            for (int j = 0; j < dx.length; j++) {
              int[] next = {cur[0] + dx[j], cur[1] + dy[j]};
              if (valid(next, n, m, reachable, visited)) {
                visited[next[0]][next[1]] = true;
                nextLevel.add(next);
                min = Math.min(min, grid[next[0]][next[1]]);
              }
            }
          }
          if (!nextLevel.isEmpty()) {
            boolean inside = false;
            for (int[] val : nextLevel) {
              if (grid[val[0]][val[1]] == min) {
                nodes.add(val);

                if (!inside)
                  path.append(conv(grid[val[0]][val[1]]));
                inside = true;
              }
            }
          }
        }
        System.out.println(path);
      }
    }
  }

  private static Boolean valid(int[] cell, int n, int m, boolean[][] reachable, boolean[][] visited) {
    if (cell[0] < 0 || cell[1] < 0 || cell[0] >= n || cell[1] >= m || !reachable[cell[0]][cell[1]] || visited[cell[0]][cell[1]])
      return false;
    return true;
  }

  static char conv(int num) {
    return (char) (num + 'a');
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