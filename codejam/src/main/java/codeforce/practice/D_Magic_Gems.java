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
 * @see <a href="https://codeforces.com/contest/1117/problem/D" target="_top">https://codeforces.com/contest/1117/problem/D</a>
 * @since 28/10/21 10:57 PM
 */
public class D_Magic_Gems {
  static FastScanner sc = new FastScanner(System.in);
  static int mod = 1000000007;

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        int m = sc.nextInt();
        if (n < m) {
          System.out.println(1);
          continue;
        }
        long[][] multiplier = new long[m][m];
        multiplier[0][0] = multiplier[0][m - 1] = 1;
        for (int row = 1; row < m; row++)
          multiplier[row][row - 1] = 1;
        Matrix multiplierMatrix = new Matrix(multiplier);
        long[][] base = new long[m][1];
        for (int i = 1; i < m; i++)
          base[i][0] = 1;
        base[0][0] = 2;
        Matrix baseMatrix = new Matrix(base);
        Matrix finalMatrix = multiplierMatrix.fastExpo(n - m).mul(baseMatrix);
        System.out.println(finalMatrix.val[0][0]);
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