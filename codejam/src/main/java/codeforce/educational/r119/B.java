package codeforce.educational.r119;

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

import static java.lang.Math.max;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1620/problem/B" target="_top">https://codeforces.com/contest/1620/problem/B</a>
 * @since 18/12/21 9:27 PM
 */
public class B {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long w = sc.nextInt();
        long h = sc.nextInt();
        int k1 = sc.nextInt();
        int[] k1arr = new int[k1];
        for (int i = 0; i < k1; i++) {
          k1arr[i] = sc.nextInt();
        }
        int k2 = sc.nextInt();
        int[] k2arr = new int[k2];
        for (int i = 0; i < k2; i++) {
          k2arr[i] = sc.nextInt();
        }
        int k3 = sc.nextInt();
        int[] k3arr = new int[k3];
        for (int i = 0; i < k3; i++) {
          k3arr[i] = sc.nextInt();
        }
        int k4 = sc.nextInt();
        int[] k4arr = new int[k4];
        for (int i = 0; i < k4; i++) {
          k4arr[i] = sc.nextInt();
        }
        System.out.println(max(h * max(k1arr[k1 - 1] - k1arr[0], k2arr[k2 - 1] - k2arr[0]), w * max(k3arr[k3 - 1] - k3arr[0], k4arr[k4 - 1] - k4arr[0])));

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