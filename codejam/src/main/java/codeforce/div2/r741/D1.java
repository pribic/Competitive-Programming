package codeforce.div2.r741;

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
import java.util.StringTokenizer;
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
 * @see <a href="https://codeforces.com/contest/1562/problem/D1" target="_top">https://codeforces.com/contest/1562/problem/D1</a>
 * @since 26/08/21 9:50 PM
 */
public class D1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        //+--++---++-++-
        // 10011000110110

        // case 1

        // 11001101100011
        // 00110010011100
        //

        int n = sc.nextInt();
        int q = sc.nextInt();

        char[] inputStr = sc.next().toCharArray();
        int[][] evenodd = new int[2][n];
        for (int parity = 0; parity < 2; parity++)
          for (int i = 0; i < n; i++) {
            if (i % 2 == parity)
              evenodd[parity][i] = inputStr[i] == '+' ? 1 : 0;
            else
              evenodd[parity][i] = inputStr[i] == '+' ? 0 : 1;
          }
        for (int[] row : evenodd)
          System.out.println(Arrays.toString(row));
        while (q-- > 0) {
          int l = sc.nextInt() - 1;
          int r = sc.nextInt() - 1;
          // 0 1 2 3 4 5
          int diff = 0;
          for (int i = l; i <= r; i++)
            diff += evenodd[l % 2][i] == 1 ? 1 : -1;
          System.out.println(Math.abs(diff));
        }
      }
    }
  }
  //  10 21 43

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