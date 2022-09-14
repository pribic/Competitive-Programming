package codeforce.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringJoiner;
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
 * @see <a href="https://codeforces.com/problemset/problem/1180/A" target="_top">https://codeforces.com/problemset/problem/1180/A</a>
 * @since 02/11/21 11:19 AM
 */
public class p1180_A_Alex_and_a_Rhombus {
  static FastScanner sc = new FastScanner(System.in);
  static int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        Matrix base = new Matrix(new long[][]{
          {1, 4, 0},
          {0, 1, 1},
          {0, 0, 1}
        });
        System.out.println(base.fastExpo(n).mul(new Matrix(new long[][]{
          {1},
          {0},
          {1}
        })).val[0][0]);
      }
    }
  }


  static class Matrix {
    int n, m;
    long[][] val;

    Matrix(long[][] val) {
      n = val.length;
      m = val[0].length;
      this.val = val;
    }

    Matrix(int n) {
      val = new long[n][n];
      for (int i = 0; i < n; i++) val[i][i] = 1;
      this.n = n;
      this.m = n;
    }

    Matrix mul(Matrix other) {
      int m1 = n;
      int m2 = m;
      int m3 = other.m;
      long[][] ans = new long[m1][m3];
      for (int i = 0; i < m1; i++) {
        for (int k = 0; k < m2; k++)
          for (int j = 0; j < m3; j++) {
            long val = this.val[i][k] * other.val[k][j];
            if (val >= mod)
              val %= mod;
            ans[i][j] += val;
            if (ans[i][j] >= mod)
              ans[i][j] %= mod;
          }
      }
      return new Matrix(ans);
    }

    Matrix fastExpo(long pow) {
      if (pow == 0) {
        return new Matrix(this.n);
      }
      if (pow % 2 == 0) {
        Matrix half = fastExpo(pow / 2);
        return half.mul(half);
      } else {
        return this.mul(fastExpo(pow - 1));
      }
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (long[] row : val)
        sb.append(Arrays.toString(row)).append("\n");
      return new StringJoiner(", ", "[", "]")
        .add("val=" + sb)
        .toString();
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