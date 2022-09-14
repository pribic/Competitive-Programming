package codeforce.div2.r726;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
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
 * @see <a href="https://codeforces.com/contest/1537/problem/C" target="_top">https://codeforces.com/contest/1537/problem/C</a>
 * @since 18/06/21 8:41 PM
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
        sort(arr);
        if (n == 2) {
          System.out.println(arr[0] + " " + arr[1]);
        } else {
          int minDiff = Integer.MAX_VALUE;
          int minI = Integer.MAX_VALUE;
          for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] - arr[i] < minDiff) {
              minDiff = arr[i + 1] - arr[i];
              minI = i;
            }
          }
          //if(arr[n - 1] - arr[0] == minDiff)
          if (Arrays.stream(arr, 1, n).noneMatch(i -> i != arr[0]))
            print(arr);
          else {
            for (int i = minI + 1; i < n; i++)
              System.out.print(arr[i] + " ");
            for (int i = 0; i < minI + 1; i++)
              System.out.print(arr[i] + " ");
            System.out.println();
          }
        }
      }
    }

  }

  private static int find(int[] arr) {
    int cnt = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] <= arr[i + 1])
        cnt++;
    }
    return cnt;
  }

  private static void print(int[] ans1clone) {
    for (int num : ans1clone) out.print(num + " ");
    out.println();
  }

  private static void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  private static void sort(int[] arr, int st, int end) {
    List<Integer> list = new ArrayList<>();
    for (int i = st; i <= end; i++)
      list.add(arr[i]);
    Collections.sort(list);
    int idx = 0;
    for (int i = st; i <= end; i++)
      arr[i] = list.get(idx++);
  }

  private static void sort(int[] arr) {
    sort(arr, 0, arr.length - 1);
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