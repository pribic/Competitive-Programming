package codeforce.educational.r111;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
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
 * @see <a href="https://codeforces.com/contest/1550/problem/C" target="_top">https://codeforces.com/contest/1550/problem/C</a>
 * @since 14/07/21 8:52 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long max = 2L * n - 1;
        for (int len = 3; len <= 4; len++) {
          for (int st = 0; st + len <= n; st++) {
            if (!invalid(arr, st, len))
              max++;
          }
        }
        System.out.println(max);
      }
    }
  }

  private static boolean invalid(int[] arr, int st, int len) { // [st, st + end)
    for (int i = st; i < st + len; i++) {
      for (int j = i + 1; j < st + len; j++) {
        for (int k = j + 1; k < st + len; k++)
          if (bad(arr, i, j, k))
            return true;
      }
    }
    return false;
  }

  private static boolean bad(int[] arr, int i, int j, int k) {
    boolean b = (arr[i] <= arr[j] && arr[j] <= arr[k])
      || (arr[i] >= arr[j] && arr[j] >= arr[k]);
    return b;
  }

  static boolean good(int[] arr, int st, int end) {
    for (int i = st; i <= end; i++) {
      for (int j = i + 1; j <= end; j++) {
        for (int k = j + 1; k <= end; k++) {
          if (arr[i] <= arr[j] && arr[j] <= arr[k])
            return false;
          if (arr[i] >= arr[j] && arr[j] >= arr[k])
            return false;
        }
      }
    }
    return true;
  }

  private static void removeI(int[] arr, TreeMap<Integer, ArrayDeque<Integer>> nums, int i) {
    if (nums.get(arr[i]).size() == 1)
      nums.remove(arr[i]);
    else
      nums.get(arr[i]).removeFirst();
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