package codeforce.div3.r786;

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
 * @see <a href="https://codeforces.com/contest/1674/problem/E" target="_top">https://codeforces.com/contest/1674/problem/E</a>
 * @since 02/05/22 9:54 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          nums.add(arr[i]);
        }
        int min1 = Integer.MAX_VALUE - 1;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
          if (arr[i] < min1) {
            min2 = min1;
            min1 = arr[i];
          } else if (arr[i] < min2) {
            min2 = arr[i];
          }
        }
        int count = (min1 + 1) / 2 + (min2 + 1) / 2;

        //now try adjacent
        for (int i = 0; i < n - 1; i++) {
          int a = arr[i];
          int b = arr[i + 1];
          int min = Math.min(a, b);
          int times = (min + 1) / 2;
          times += (Math.max(a, b) - times + 1) / 2;
          count = Math.min(count, times);
        }
        System.out.println(count);
      }
    }
  }

  private static boolean check(int rounds, int[] arr) {
    return false;
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