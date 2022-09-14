package codeforce.educational.r129;

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
 * @see <a href="https://codeforces.com/contest/1681/problem/C" target="_top">https://codeforces.com/contest/1681/problem/C</a>
 * @since 23/05/22 8:19 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
          a[i] = sc.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
          b[i] = sc.nextInt();
        }
        List<int[]> op = new ArrayList<>();
        //bubble sort
        for (int i = 0; i < n - 1; i++) {
          //max n-1 pass needed
          for (int j = 0; j < n - 1 - i; j++) {
            //check for a[i] with a[j]
            if (a[j] > a[j + 1] || (a[j] == a[j + 1] && b[j] > b[j + 1])) {
              swap(a, j, j + 1);
              swap(b, j, j + 1);
              op.add(new int[]{j + 1, j + 2});
            }
          }
        }
        //if b is sorted then yes
        boolean sorted = isSorted(b);
        if (sorted) {
          System.out.println(op.size());
          for (int[] aa : op)
            System.out.println(aa[0] + " " + aa[1]);
        } else {
          System.out.println(-1);
        }
      }
    }
  }
/*
1 2 2 3
2 2 3 3
 */


  private static boolean isSorted(int[] b) {
    for (int i = 0; i < b.length - 1; i++)
      if (b[i] > b[i + 1])
        return false;
    return true;
  }

  private static void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
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