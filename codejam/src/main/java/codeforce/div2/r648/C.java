package codeforce.div2.r648;

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
 * @see <a href="https://codeforces.com/problemset/problem/1365/C" target="_top">https://codeforces.com/problemset/problem/1365/C</a>
 * @since 16/05/22 1:33 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
        }
        int[] b = new int[n];
        int[] fr = new int[n + 1];
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
          fr[b[i]] = i;
        }
        int[] shift = new int[n]; // how many shift needed for each number to match its companion in array b, we only
        // consider shift right operations, not shift left operations.
        for (int i = 0; i < n; i++) {
          int j = fr[a[i]];
          if (j > i)
            shift[i] = j - i;
          else // 0 1 2 3 4 5 6
            shift[i] = n - i + j;
        }
        //now find a number in shift with max frequency
        int[] frShift = new int[n + 1];
        for (int num : shift)
          frShift[num]++;
        System.err.println(Arrays.toString(frShift));
        System.err.println(Arrays.toString(shift));
        int max = 0;
        for (int num : frShift)
          max = Math.max(max, num);
        System.out.println(max);

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