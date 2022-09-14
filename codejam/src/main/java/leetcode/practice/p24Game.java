package leetcode.practice;

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
 * @see <a href="https://leetcode.com/problems/24-game/" target="_top">https://leetcode.com/problems/24-game/</a>
 * @since 09/07/21 7:47 PM
 */
public class p24Game {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = 4;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        System.out.println(new Solution().judgePoint24(arr));
      }
    }
  }

  static class Solution {
    boolean ans = false;

    public boolean judgePoint24(int[] cards) {
      int n = cards.length;
      List<Fraction> fractions = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        fractions.add(new Fraction(cards[i], 1));
      }
      solve(fractions);
      return ans;
    }

    void solve(List<Fraction> fractions) {
      int n = fractions.size();
      if (n == 1) {
        Fraction f = fractions.get(0);
        if (f.p == 24 && f.q == 1)
          ans = true;
        return;
      }

      for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
          Fraction first = fractions.get(i);
          Fraction second = fractions.get(j);
          List<Fraction> possible = new ArrayList<>();
          possible.add(first.add(second));
          possible.add(first.sub(second));
          possible.add(second.sub(first));
          possible.add(first.mul(second));
          if (second.p != 0)
            possible.add(first.div(second));
          if (first.p != 0)
            possible.add(second.div(first));

          for (Fraction f : possible) {
            List<Fraction> newFractions = new ArrayList<>();
            newFractions.add(f);
            for (int k = 0; k < fractions.size(); k++) {
              if (k != i && k != j)
                newFractions.add(fractions.get(k));
            }
            solve(newFractions);
          }
        }
      }
    }


    static class Fraction {
      int p, q;

      public Fraction(int p, int q) {
        int g = gcd(p, q);
        this.p = p / g;
        this.q = q / g;
      }

      private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
      }

      public Fraction add(Fraction other) {
        return new Fraction(this.p * other.q + this.q * other.p, this.q * other.q);
      }

      public Fraction sub(Fraction other) {
        return new Fraction(this.p * other.q - this.q * other.p, this.q * other.q);
      }

      public Fraction mul(Fraction other) {
        return new Fraction(this.p * other.p, this.q * other.q);
      }

      public Fraction div(Fraction other) {
        return mul(new Fraction(other.q, other.p));
      }

      @Override
      public String toString() {
        return new StringJoiner(", ", "[", "]")
          .add("p=" + p)
          .add("q=" + q)
          .toString();
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