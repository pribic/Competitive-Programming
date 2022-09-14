package codeforce.globalround.y2021.r18;

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

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1615/problem/C" target="_top">https://codeforces.com/contest/1615/problem/C</a>
 * @since 25/12/21 12:45 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        char[][] both = new char[n][2];
        String a = sc.next();
        String b = sc.next();
        for (int i = 0; i < n; i++) {
          both[i][0] = a.charAt(i);
          both[i][1] = b.charAt(i);
        }
        Arrays.sort(both, (b1, b2) -> b1[1] - b2[1]);
        int totalZero = 0;
        for (int i = 0; i < n; i++)
          if (both[i][1] == '0')
            totalZero++;
        int one = n - totalZero;
        int firsthz = 0;
        int firstho = 0;
        int secondz = 0;
        int secondo = 0;
        for (int i = 0; i < totalZero; i++) {
          if (both[i][0] == '0')
            firsthz++;
          else
            firstho++;
        }
        for (int i = totalZero; i < n; i++) {
          if (both[i][0] == '0')
            secondz++;
          else
            secondo++;
        }
        if (firstho == secondz) {
          System.out.println(2 * firsthz);
        } else {
          if (firstho > 0) {
            int firstho1 = firsthz;
            int firsthz1 = firstho - 1;
            int secondho1 = secondz;
            int secondhz1 = secondo;
            if (firstho1 == secondhz1) {
              System.out.println(1 + 2 * firstho1);
            } else {
              System.out.println(-1);
            }
          }
          
        }
      }
    }
  }
  
  /*
101
101
   */

  private static void change(char[] a, int i) {
    for (int j = 0; j < a.length; j++) {
      if (j != i) {
        if (a[j] == '1')
          a[j] = '0';
        else
          a[j] = '1';
      }
    }
  }

  //return how many 10 or 01 pair exist
  static int count(char[] a, char[] b) {
    int cnt = 0;
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i])
        cnt++;
    }
    return cnt;
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