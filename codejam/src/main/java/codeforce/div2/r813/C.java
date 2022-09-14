package codeforce.div2.r813;

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
 * @see <a href="https://codeforces.com/contest/1712/problem/C" target="_top">https://codeforces.com/contest/1712/problem/C</a>
 * @since 13/08/22 8:47 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] first = new int[n + 1];
        Arrays.fill(first, n + 1);
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          first[arr[i]] = Math.min(first[arr[i]], i);
        }
        if (n == 1) {
          System.out.println(0);
          continue;
        }
        int idx = -1;
        for (int i = n - 1; i >= 0; ) {
          int x = i - 1;//x represents the previous non equal element
          while (x >= 0 && arr[x] == arr[x + 1])
            x--;
          
          int y = x + 1; //y represents the current element we are looking for
          //if we have this element present before this, then make everything from here as 0
          if (first[arr[y]] < y) {
            idx = y;
            break;
          }
          if ((x >= 0 && arr[x] > arr[i])) {
            idx = y - 1;
            break;
          }
          i = x;
        }
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i <= idx; i++)
          unique.add(arr[i]);
        System.out.println(unique.size());
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