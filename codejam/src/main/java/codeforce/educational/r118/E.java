package codeforce.educational.r118;

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
import java.util.function.BiPredicate;
import java.util.function.Predicate;
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
 * @see <a href="https://codeforces.com/contest/1613/problem/E" target="_top">https://codeforces.com/contest/1613/problem/E</a>
 * @since 01/12/21 9:17 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);
  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  public static void main(String[] args) {

    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        char[][] ag = new char[n][m];
        for (int i = 0; i < n; i++) {
          grid[i] = sc.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            ag[i][j] = grid[i][j];
          }
        }
        int lx = 0, ly = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>(); // our contract is whatever is in queue will have a clear single route to reach lab.
        // so we start with lab and see which all neighbors can have it , if yes then only we put them in queue.
        
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] == 'L') {
              q.add(new int[]{i, j});
              lx = i;
              ly = j;
            }
          }
        }

        BiPredicate<Integer, Integer> validIndex = (x, y) -> x >= 0 && y >= 0 && x < n && y < m;
        BiPredicate<Integer, Integer> check = (x, y) -> {
          int dotc = 0;
          for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (validIndex.test(tx, ty) && grid[tx][ty] == '.')
              dotc++;
          }
          return dotc <= 1;
        };
        while (!q.isEmpty()) {
          int[] cell = q.removeFirst();
          grid[cell[0]][cell[1]] = '+';
          for (int i = 0; i < 4; i++) {
            int tx = cell[0] + dx[i];
            int ty = cell[1] + dy[i];
            if (validIndex.test(tx, ty) && grid[tx][ty] == '.' && check.test(tx, ty)) {
              q.add(new int[]{tx, ty});
            }
          }
        }

        grid[lx][ly] = 'L';

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            ans.append(grid[i][j]);
          }
          ans.append("\n");
        }
        System.out.print(ans);
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
/*
4 5
#....
#.##L
#..#.
.#...

 */