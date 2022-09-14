package codeforce.div2.r791;

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
 * @see <a href="https://codeforces.com/contest/1679/problem/A" target="_top">https://codeforces.com/contest/1679/problem/A</a>
 * @since 14/05/22 3:06 PM
 */
public class A {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long n = sc.nextLong();
        if (n < 4 || n % 2 == 1) {
          System.out.println(-1);
        } else {
          // 4a + 6b = n
          // we want to minimize and maximize a + b
          if (n == 4 || n == 6) {
            System.out.println("1 1");
          } else if (n == 8) {
            System.out.println("2 2");
          } else {
            //first consume using 6, then using 4 
            if (n % 4 == 0 && n % 6 == 0) {
              System.out.println(n / 6 + " " + n / 4);
            } else if (n % 4 == 0) {
              // consume using 4 to maximize
              // for minimize, use 6 as much as possible
              // 16 -> 6 + 6 + 4
              if (n % 6 == 2) // n == 20
                System.out.println(((n - 8) / 6 + 2) + " " + n / 4);
              else { // n % 6 == 4 , n == 16
                System.out.println(((n - 4) / 6 + 1) + " " + n / 4);
              }
            } else if (n % 6 == 0) {
              // n % 6 == 0, so use all of them for min
              // use 4 as much as we can to maximize the number
              // so for maximum, use 1 6 and then all other 4
              System.out.println(n / 6 + " " + ((n - 6) / 4 + 1));
            } else {
              // not by 4 and 6
              // such as 10 14 22 etc
              // 20 -> 4 4 6 6 || 4 4 4 4 4
              // 10 -> 4 6
              // 14 -> 4 4 6
              // 22 -> 4 4 4 4 6 || 4 6 6 6
              // 26 -> 4 4 4 4 4 6 || 4 4 6 6 6
              // 34 -> 4 4 4 4 4 4 6 || 6 6 6 6 6 
              // 38 -> 4 4 4 4 4 4 4 4 6 || 4 4 6 6 6 6 6
              if (n % 6 == 2) {
                System.out.println(((n - 8) / 6 + 2) + " " + ((n - 6) / 4 + 1));
              } else { // n % 6 == 4
                System.out.println(((n - 4) / 6 + 1) + " " + ((n - 6) / 4 + 1));
              }

            }
          }
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