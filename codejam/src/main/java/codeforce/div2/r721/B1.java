package codeforce.div2.r721;

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


//non palindromic remain same if we reverse

/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1527/problem/B1" target="_top">https://codeforces.com/contest/1527/problem/B1</a>
 * @since 20/05/21 8:26 PM
 */
public class B1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        int cntZero = 0;
        for (char c : str.toCharArray()) {
          if (c == '0')
            cntZero++;
        }
        /*B_Inversions_2 B_Inversions_2 D D B_Inversions_2 B_Inversions_2 D D
        1 2 3 4 5 6 7 8
        1 1 2 2 2 3 3 4
        A 1 1 1
        B_Inversions_2 1 1
        10101
        
        0   1   2   3
        1   3   5   7
        A B_Inversions_2 B_Inversions_2 A A B_Inversions_2 B_Inversions_2
        B_Inversions_2 D A D B_Inversions_2 D A
        */
        if (cntZero > 1 && cntZero % 2 == 1) {
          System.out.println("ALICE");
        } else {
          System.out.println("BOB");
        }
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
      br = new BufferedReader(new InputStreamReader(f));
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