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
import static java.lang.System.setProperties;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.spoj.com/problems/SEQ/" target="_top">https://www.spoj.com/problems/SEQ/</a>
 * @since 03/11/21 3:22 PM
 */
public class SEQ {
  static FastScanner sc = new FastScanner(System.in);

  /*
   ai = bi (for i <= k)
   ai = c1ai-1 + c2ai-2 + ... + ckai-k (for i > k)
  
   */
  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int k = sc.nextInt();
        int[] bArr = new int[k];
        for (int i = 0; i < k; i++) {
          bArr[i] = sc.nextInt();
        }
        int[] cArr = new int[k];
        for (int i = 0; i < k; i++) {
          cArr[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        if (n <= k) {
          System.out.println(bArr[n - 1]);
        } else {
          long[][] constant = new long[k][k];
          for (int col = 0; col < k; col++)
            constant[0][col] = cArr[col];
          for (int row = 1; row < k; row++)
            constant[row][row - 1] = 1;
          long[][] base = new long[k][1];
          for (int i = 0; i < k; i++)
            base[i][0] = bArr[k - 1 - i];
          Matrix constantMat = new Matrix(constant);
          Matrix baseMat = new Matrix(base);
          System.out.println(constantMat.fastExpo(n - k).mul(baseMat).val[0][0]);
        }
      }
    }
  }

  static class Matrix {
    int mod = (int) 1e9;

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