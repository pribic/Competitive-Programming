package codeforce.practice;

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
 * @see <a href="c" target="_top">c</a>
 * @since 25/11/21 11:36 AM
 */
public class temp {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    new Solution().solve(new int[][]{{1, 0, 2}, {3, 4, 5}, {6, 7, 8}});
  }


  static class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    Map<Integer, Integer> memo;

    public int solve(int[][] board) {
      memo = new HashMap<>();
      int x = 0;
      int y = 0;
      for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++) {
          if (board[i][j] == 0) {
            x = i;
            y = j;
          }

        }
      return dfs(board, x, y, 0);
    }

    int dfs(int[][] board, int x, int y, int step) {
      Integer rep = convert(board);
      System.out.println(rep);
      if (memo.containsKey(rep))
        return memo.get(rep);
      if (set(board)) {
        memo.put(rep, step);
        return step;
      }
      for (int i = 0; i < 4; i++) {
        int tx = x + dx[i];
        int ty = y + dy[i];
        if (valid(tx, ty)) {
          swap(board, x, y, tx, ty);
          int a = dfs(board, tx, ty, step + 1);
          if (a != -1) {
            memo.put(rep, a + 1);
            return a + 1;
          }
          swap(board, x, y, tx, ty);
        }

      }
      memo.put(rep, -1);
      return -1;
    }

    void swap(int[][] b, int x, int y, int tx, int ty) {
      int t = b[x][y];
      b[x][y] = b[tx][ty];
      b[tx][ty] = t;
    }

    boolean set(int[][] board) {
      int x = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (board[i][j] != x)
            return false;
          x++;
        }
      }
      return true;
    }

    int convert(int[][] board) {
      int x = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          x = x * 10 + board[i][j];
        }
      }
      return x;
    }

    boolean valid(int x, int y) {
      return x >= 0 && y >= 0 && x < 3 && y < 3;
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