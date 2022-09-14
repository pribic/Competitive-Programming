package codeforce.educational.r121;

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
 * @see <a href="https://codeforces.com/contest/1626/problem/B" target="_top">https://codeforces.com/contest/1626/problem/B</a>
 * @since 16/01/22 8:15 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      outer:
      for (int tt = 1; tt <= T; tt++) {
        char[] str = sc.next().toCharArray();
        boolean found = false;
        for (int i = 0; i < str.length - 1; i++) {
          int replaceSum = str[i] - '0' + str[i + 1] - '0';
          int curnum = 10 * (str[i] - '0') + str[i + 1] - '0';
          if (replaceSum > 10 && replaceSum > curnum) {
            str[i] = (char) (replaceSum / 10 + '0');
            str[i + 1] = (char) (replaceSum % 10 + '0');
            found = true;
            break;
          }
        }
        for (int i = str.length - 2; i >= 0; i--) {
          int replaceSum = str[i] - '0' + str[i + 1] - '0';
          if (replaceSum >= 10) {
            str[i] = (char) (replaceSum / 10 + '0');
            str[i + 1] = (char) (replaceSum % 10 + '0');
            found = true;
            break;
          }
        }
        int n = str.length;
        if (!found) { // now we will definitely have a less len
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < n - 1; i++) {
            int sum = str[i] - '0' + str[i + 1] - '0';
            if (sum > str[i + 1] - '0') {
              for (int j = 0; j < i; j++)
                sb.append(str[j]);
              sb.append((char) (sum + '0'));
              for (int j = i + 2; j < n; j++)
                sb.append(str[j]);
              System.out.println(sb);
              continue outer;
            }
          }
          for (int i = 0; i < n - 2; i++)
            sb.append(str[i]);
          sb.append((char) (str[n - 1] - '0' + str[n - 2]));
          System.out.println(sb);
        } else {
          print(str, n);
        }
      }
    }
  }

  private static void print(char[] str, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++)
      sb.append(str[i]);
    System.out.println(sb);
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
/*
5108135

 */