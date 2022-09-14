package codeforce.div2.r730;

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
 * @see <a href="aa" target="_top">aa</a>
 * @since 08/07/21 3:56 PM
 */
public class aa {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        List<List<String>> var = new Solution().solveNQueens(n);
        for (List<String> v : var)
          System.out.println(v);
        System.out.println(var);
      }
    }
  }

  static class Solution {
    public List<List<String>> solveNQueens(int n) {
      boolean[][] board = new boolean[n][n];
      List<List<String>> ans = new ArrayList<>();
      for (int i = 0; i < n; i++)
        generate(board, ans, 0, 0, i);
      return ans;
    }

    void generate(boolean[][] board, List<List<String>> ans, int cnt, int x, int y) {
      //out.println(cnt + " " + x + " " + y);
      if (x == board.length && y > 0 || !valid(board)) return;
      if (cnt == board.length) {
        ans.add(convert(board));
        return;
      }
      board[x][y] = true;
      for (int i = 0; i < board.length; i++) {
        generate(board, ans, cnt + 1, x + 1, i);
      }
      board[x][y] = false;
    }

    List<String> convert(boolean[][] board) {
      List<String> ans = new ArrayList<>();

      for (int i = 0; i < board.length; i++) {
        String c = new String();
        for (int j = 0; j < board.length; j++) {
          if (board[i][j]) c += 'Q';
          else c += '.';
        }
        ans.add(c);
      }
      return ans;
    }

    boolean valid(boolean[][] board) {
      boolean[] row = new boolean[board.length];
      boolean[] col = new boolean[board.length];
      int[] diag1 = new int[2 * board.length - 1];
      int[] diag2 = new int[2 * board.length - 1];
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board.length; j++) {
          if (board[i][j]) {
            if (row[i] || col[j])
              return false;
            row[i] = true;
            col[j] = true;
            diag1[j - i + board.length - 1]++;
            diag2[i + j]++;
          }
        }
      }
      for (int cnt : diag1) {
        if (cnt > 1) return false;
      }
      for (int cnt : diag2) {
        if (cnt > 1) return false;
      }
      return true;
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