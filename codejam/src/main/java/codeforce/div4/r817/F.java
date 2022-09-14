package codeforce.div4.r817;

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
 * @see <a href="https://codeforces.com/contest/1722/problem/F" target="_top">https://codeforces.com/contest/1722/problem/F</a>
 * @since 30/08/22 9:04 PM
 */
public class F {
  static FastScanner sc = new FastScanner(System.in);

  static int[] dx = {-1, 0, 1, 0};
  static int[] dy = {0, 1, 0, -1};

  static int[] dx8 = {-1, -1, 0, 1, 1, 1, 0, -1};
  static int[] dy8 = {0, 1, 1, 1, 0, -1, -1, -1};

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
          grid[i] = sc.next().toCharArray();
        }
        boolean valid = true;
        int[][] idGrid = new int[n][m];
        outer:
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] == '*') {
              for (int d = 0; d < 4; d++) {
                int dir1 = d;
                int dir2 = (d + 1) % 4;
                int tx1 = i + dx[dir1];
                int ty1 = j + dy[dir1];
                int tx2 = i + dx[dir2];
                int ty2 = j + dy[dir2];
                if (valid(n, m, tx1, ty1) && valid(n, m, tx2, ty2) && grid[tx1][ty1] == '*' && grid[tx2][ty2] == '*') {
                  //found an L shape
                  int[][] points = new int[3][2];
                  points[0][0] = i;
                  points[0][1] = j;
                  points[1][0] = tx1;
                  points[1][1] = ty1;
                  points[2][0] = tx2;
                  points[2][1] = ty2;
                  //
                  Set<Integer> pointsId = new HashSet<>();
                  for (int[] p : points) {
                    pointsId.add(id(p[0], p[1], n));
                    idGrid[p[0]][p[1]]++;
                  }
                  //these 3 are valid points
                  for (int[] p : points) {
                    for (int d8 = 0; d8 < dx8.length; d8++) {
                      //we iterate over all 8 neighbors of p
                      int tx = p[0] + dx8[d8];
                      int ty = p[1] + dy8[d8];
                      if (valid(n, m, tx, ty) && !pointsId.contains(id(tx, ty, n)) && grid[tx][ty] == '*')
                        valid = false;
                    }
                  }
                }
              }
            }
          }
        }
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (grid[i][j] == '*' && idGrid[i][j] != 1)
              valid = false;
          }
        }
        System.out.println(valid ? "YES" : "NO");
      }
    }
  }

  private static int id(int i, int j, int r) {
    return i * r + j;
  }

  private static boolean valid(int n, int m, int i, int j) {
    return i >= 0 && j >= 0 && i < n && j < m;
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