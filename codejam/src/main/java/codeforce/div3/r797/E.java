package codeforce.div3.r797;

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
 * @see <a href="https://codeforces.com/contest/1690/problem/E" target="_top">https://codeforces.com/contest/1690/problem/E</a>
 * @since 07/06/22 9:23 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] fr = new int[1000];
        long cost = 0;
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          cost += val / k;
          int reminder = val % k;
          fr[reminder]++;
        }
        for (int num1 = 1; num1 < fr.length; num1++) {
          if (fr[num1] > 0) {
            int remaining = k - num1;
            for (int num2 = remaining; fr[num1] > 0 && num2 < fr.length; num2++) {
              if ((num1 != num2 && fr[num2] > 0) || (num1 == num2 && fr[num2] > 1)) {
                //we can use num1 + num2 as a pair
                int min = Math.min(fr[num1], fr[num2]);
                if (num1 == num2) {
                  cost += min / 2;
                  fr[num1] = min % 2;
                } else {
                  cost += min;
                  fr[num2] -= min;
                  fr[num1] -= min;
                }
                num2--;
              }
            }
          }
        }
        System.out.println(cost);
      }
    }
  }

  /*
 
 2 4 3 3 4 1
 
   */
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