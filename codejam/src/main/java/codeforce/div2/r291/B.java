package codeforce.div2.r291;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Objects;
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

import static java.lang.System.gc;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/problemset/problem/514/B" target="_top">https://codeforces.com/problemset/problem/514/B</a>
 * @since 16/05/22 1:58 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[][] points = new int[n][2];
        Set<Fraction> slopes = new HashSet<>();
        for (int i = 0; i < n; i++) {
          points[i][0] = sc.nextInt();
          points[i][1] = sc.nextInt();
          Fraction slope = new Fraction(y - points[i][1], x - points[i][0]);
          System.err.println("slope = " + slope);
          slopes.add(slope);
        }
        System.out.println(slopes.size());
      }
    }
  }

  static class Fraction {
    int a, b; // represents a a/b fraction

    Fraction(int a, int b) {
      this.a = a / gcd(a, b);
      this.b = b / gcd(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Fraction fraction = (Fraction) o;
      return a == fraction.a && b == fraction.b;
    }

    @Override
    public int hashCode() {
      return Objects.hash(a, b);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Fraction.class.getSimpleName() + "[", "]")
        .add("a=" + a)
        .add("b=" + b)
        .toString();
    }

    private int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
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